import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val junitVersion: String by project
val jvmTargetVersion: String by project

dependencies {
    implementation(kotlin("reflect"))
    implementation("junit:junit:4.13.2")
    implementation("org.junit.jupiter:junit-jupiter:5.8.2")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
}

tasks.compileKotlin {
    kotlinOptions {
        jvmTarget = jvmTargetVersion
        freeCompilerArgs = freeCompilerArgs + "-Xplugin=${rootDir}/shallowSizePlugin/build/libs/shallow-size-plugin.jar"
    }
}

tasks.compileTestKotlin {
    kotlinOptions {
        jvmTarget = jvmTargetVersion
        freeCompilerArgs = freeCompilerArgs + "-Xplugin=${rootDir}/shallowSizePlugin/build/libs/shallow-size-plugin.jar"
    }
}


tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    dependsOn(":shallowSizePlugin:assemble")
}
