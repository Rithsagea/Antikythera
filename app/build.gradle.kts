/*
 * This file was generated by the Gradle 'init' task.
 *
 * This generated file contains a sample Java application project to get you started.
 * For more details on building Java & JVM projects, please refer to https://docs.gradle.org/8.8/userguide/building_java_projects.html in the Gradle documentation.
 */

plugins {
    // Apply the application plugin to add support for building a CLI application in Java.
    application
    // https://github.com/melix/jmh-gradle-plugin
    id("me.champeau.jmh") version "0.7.1"
    id("io.freefair.lombok") version "8.6"
}

repositories {
    // Use Maven Central for resolving dependencies.
    mavenCentral()
}

dependencies {
    // Use JUnit Jupiter for testing.
    testImplementation(libs.junit.jupiter)

    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    // This dependency is used by the application.
    implementation(libs.guava)
}

// Apply a specific Java toolchain to ease working on different environments.
java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

jmh {
    timeUnit.set("ms")
    fork.set(2)

    timeOnIteration.set("5s")
    warmupIterations.set(2)
    iterations.set(3)
}

application {
    // Define the main class for the application.
    mainClass = "moe.lita.antikythera.App"
}

tasks.named<Test>("test") {
    // Use JUnit Platform for unit tests.
    useJUnitPlatform()

    this.testLogging.showStandardStreams = true
}

tasks.getByName<JavaExec>("run") {
    standardInput = System.`in`
}
