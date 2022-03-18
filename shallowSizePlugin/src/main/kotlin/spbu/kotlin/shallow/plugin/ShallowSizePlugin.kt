package spbu.kotlin.shallow.plugin

import arrow.meta.CliPlugin
import arrow.meta.Meta
import arrow.meta.phases.CompilerContext
import kotlin.contracts.ExperimentalContracts

@ExperimentalContracts
class ShallowSizePlugin : Meta {
    override fun intercept(ctx: CompilerContext): List<CliPlugin> =
        listOf(
            GenerateShallowSize
        )
}
