plugins {
    kotlin("jvm") version "1.9.23"
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

group = "kr.eme.plugin"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.20.2-R0.1-SNAPSHOT")
    implementation(kotlin("stdlib-jdk8"))
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}

//tasks.jar {
//    archiveFileName = "${project.name}-${project.version}.jar"
//    destinationDirectory = file("D:\\minecraft\\1. 버킷 관련\\1.20.2 paper_dev2\\plugins")
//    manifest {
//        attributes["main-class"] = "kr.eme.plugin.SemiMoney"
//    }
//}

tasks.shadowJar {
    archiveFileName = "${project.name}-${project.version}.jar"
    destinationDirectory = file("D:\\minecraft\\1. 버킷 관련\\1.20.2 paper_dev2\\plugins")
    manifest {
        attributes["main-class"] = "kr.eme.plugin.SemiMoney"
    }
}