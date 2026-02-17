plugins {
    `maven-publish`
}

publishing {
    repositories {
        maven {
            name = "combimagnetron"
            url = uri("https://repo.combimagnetron.net/releases/")
            credentials(PasswordCredentials::class)
            authentication {
                create<BasicAuthentication>("basic")
            }
        }
    }
    publications {
        create<MavenPublication>("maven") {
            groupId = project.group.toString()
            artifactId = "Sunscreen"
            version = project.version.toString()
            from(components["java"])
        }
    }
}