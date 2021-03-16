plugins {
    kotlin("jvm") version "1.4.0"
    application
    id("org.openjfx.javafxplugin") version "0.0.8"
}

javafx {
    version = "11.0.2"
    modules("javafx.controls", "javafx.graphics", "javafx.swing")
}

group = "com.example"
version = "1.0-SNAPSHOT"

val tornadofx_version: String by rootProject

repositories {
    mavenCentral()
}

application {
    mainClassName = "app.MainKt"
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation("no.tornado:tornadofx:$tornadofx_version")
    testImplementation(kotlin("test-junit"))
}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
}