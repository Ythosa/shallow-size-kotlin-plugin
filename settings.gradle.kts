rootProject.name = "shallowSize"

include("shallowSizePlugin")

pluginManagement {
    val kotlinVersion: String by settings
    plugins {
        kotlin("jvm") version kotlinVersion
    }
}
include("shallowSizePluginTest")
