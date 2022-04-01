package spbu.kotlin.shallow.plugin

import arrow.meta.CliPlugin
import arrow.meta.Meta
import arrow.meta.invoke
import arrow.meta.quotes.Transform
import arrow.meta.quotes.classDeclaration
import org.jetbrains.kotlin.backend.common.lower.DeclarationIrBuilder
import org.jetbrains.kotlin.ir.builders.irExprBody
import org.jetbrains.kotlin.ir.builders.irInt
import org.jetbrains.kotlin.ir.util.functions
import org.jetbrains.kotlin.ir.util.properties

val Meta.GenerateShallowSize: CliPlugin
    get() = "Generate shallowSize method" {
        meta(
            classDeclaration(this, { element.isData() }) { declaration ->
                Transform.replace(
                    replacing = declaration.element,
                    newDeclaration = ShallowSizeSignature.let { signature ->
                        """
                        |$`@annotations`
                        |$visibility $modality $kind $name $`(typeParameters)` $`(params)` $superTypes {
                        |$body
                        |   fun ${signature.name}(${signature.parameters}): ${signature.returnType} = TODO("
                        |       method body must be provided by shallowSize plugin
                        |   ")
                        |}
                        """.trimIndent().`class`
                    }
                )
            },
            irClass { clazz ->
                if (clazz.isData) {
                    val shallowSize = clazz.functions.find { it.isShallowSizeFunction() }
                        ?: throw ShallowSizePluginInternalError()
                    val builder = DeclarationIrBuilder(pluginContext, shallowSize.symbol)
                    val fieldsSize = clazz.properties.mapNotNull { it.backingField?.type?.byteSize() }.sum()
                    shallowSize.body = builder.irExprBody(builder.irInt(fieldsSize))
                }
                clazz
            }
        )
    }
