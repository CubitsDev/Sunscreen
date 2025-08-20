plugins {
    id("java")
}

group = "me.combimagnetro"
version = "unspecified"

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":api"))
}
