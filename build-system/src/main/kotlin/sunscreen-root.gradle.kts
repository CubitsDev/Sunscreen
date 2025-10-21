import org.gradle.api.artifacts.VersionCatalogsExtension

plugins {
    `java-library`
    id("com.github.hierynomus.license")
}

val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

//license {
//    include("**/*.java")
//}

dependencies {
    implementation(libs.findLibrary("build-system-license").get())
}
