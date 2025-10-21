plugins {
    `maven-publish`
}

publishing {
    repositories {
        maven {
            name = "combimagnetron"
            url = uri("http://repo.tikite.ch/releases/")
            credentials(PasswordCredentials::class)
            authentication {
                create<BasicAuthentication>("basic")
            }
            isAllowInsecureProtocol = true
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