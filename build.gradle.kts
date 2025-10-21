import org.gradle.accessors.dm.LibrariesForLibs

plugins {
    java
    application
    id("sunscreen-main")
}

group = "me.combimagnetron"
version = ""

allprojects {

    group = rootProject.group
    version = rootProject.version

    apply(plugin = "sunscreen-main")

    fun libs(): LibrariesForLibs {
        return rootProject.libs
    }

    repositories {
        mavenCentral()
        maven("https://repo.tikite.ch/releases")
        maven("https://jitpack.io")
        maven("https://repo.codemc.io/repository/maven-releases/")
    }

    dependencies {
        implementation(libs().bundles.utils)
        implementation(libs().bundles.minecraft)
        implementation(libs().passport)
    }

}
