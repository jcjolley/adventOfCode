plugins {
    kotlin("jvm") version "1.6.0"
    id("org.jmailen.kotlinter") version "3.7.0"
}

group = "com.jcjolley"
version = "1.0"

repositories {
    mavenCentral()
}

tasks {
    test {
        useJUnitPlatform()
    }
}

dependencies {
    implementation(kotlin("bom"))
    implementation(kotlin("stdlib"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.0-RC")
    implementation("io.github.rybalkinsd:kohttp:0.12.0")

    testImplementation("io.mockk:mockk:1.12.1")
    testImplementation(kotlin("test-junit5"))
    testImplementation ("org.junit.jupiter:junit-jupiter-api:5.8.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.2")
}