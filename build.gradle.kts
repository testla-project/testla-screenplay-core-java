plugins {
    kotlin("jvm") version "1.5.31"
}

group = "de.p7s1.testla"
version = "1.0.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))

    testImplementation("io.kotest:kotest-runner-junit5:5.3.1")
    testImplementation("io.kotest:kotest-assertions-core:5.3.1")
}

tasks.withType<Test> {
    useJUnitPlatform()
}
