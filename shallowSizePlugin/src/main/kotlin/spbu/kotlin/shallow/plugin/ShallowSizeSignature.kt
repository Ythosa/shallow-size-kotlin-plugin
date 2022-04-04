package spbu.kotlin.shallow.plugin

import org.jetbrains.kotlin.ir.declarations.IrSimpleFunction

object ShallowSizeSignature {
    const val name = "shallowSize"
    const val parameters = ""
    const val returnType = "Int"
}

fun IrSimpleFunction.isShallowSizeFunction() =
    this.name.asString() == ShallowSizeSignature.name && this.valueParameters.isEmpty()
