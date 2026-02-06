import org.gradle.accessors.dm.LibrariesForLibs

plugins {
    id("java")
}

fun libs(): LibrariesForLibs {
    return rootProject.libs
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":api"))
    implementation(libs().lamp.common)
    implementation(libs().lamp.paper)
}

