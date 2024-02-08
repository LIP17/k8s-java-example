plugins {
    id("java")
}

group = "lip17"
version = "1.0-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
    mavenCentral()
    mavenLocal()
}

dependencies {

    implementation("io.kubernetes:client-java:20.0.0")
    implementation("org.springframework.boot:spring-boot-starter-web:3.2.2")


    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}