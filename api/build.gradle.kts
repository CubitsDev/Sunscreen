plugins {
    java
    `maven-publish`
}

repositories {
    maven("https://repo.tikite.ch/releases")
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
    maven("https://jitpack.io")
    maven("https://repo.codemc.io/repository/maven-releases/")
    maven("https://repo.codemc.io/repository/maven-snapshots/")
    maven("https://repo.aikar.co/content/groups/aikar/")
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.junit.platform:junit-platform-suite:1.13.4")
    testImplementation(libs.bundles.utils)
}

tasks.test {
    useJUnitPlatform()
}
