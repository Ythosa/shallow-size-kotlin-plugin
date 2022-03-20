rootProject.name = "shalowSize"

include("shallowSizePlugin")

pluginManagement {
    val kotlinVersion: String by settings
    plugins {
        kotlin("jvm") version kotlinVersion
    }
}
include("shallowSizePluginTest")
