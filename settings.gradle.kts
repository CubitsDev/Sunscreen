pluginManagement {
    repositories {
        mavenCentral()
        gradlePluginPortal()
        maven("https://repo.papermc.io/repository/maven-public/")
    }
}

dependencyResolutionManagement {
    includeBuild("build-system")
    repositories.gradlePluginPortal()
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.5.0"
}

rootProject.name = "Sunscreen"
include("api")
include("spigot")
include("minestom")
include("common")
include("build-system")