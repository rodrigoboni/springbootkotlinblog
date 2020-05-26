import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.3.0.RELEASE"
	id("io.spring.dependency-management") version "1.0.9.RELEASE"
	kotlin("jvm") version "1.3.72"
	kotlin("plugin.spring") version "1.3.72" // able use of @configuration and @transacional without need of open qualifier in classes
	kotlin("plugin.jpa") version "1.3.72" // required for no-arg constructor for any class annotated with @entity
	kotlin("plugin.allopen") version "1.3.61" // required for using lazy fetching in jpa
}

group = "com.rmboni"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.jetbrains.kotlin:kotlin-reflect") // required by kotlin - is Kotlin reflection library
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8") // required by kotlin - is the Java 8 variant of Kotlin standard library
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin") // required by kotlin - support for serialization/deserialization in data classes
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-mustache")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.springframework.boot:spring-boot-starter-web")
	developmentOnly("org.springframework.boot:spring-boot-devtools")
	runtimeOnly("com.h2database:h2")
	testImplementation("org.springframework.boot:spring-boot-starter-test") {
		exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
		exclude(module = "mockito-core")
	}
	testImplementation("org.junit.jupiter:junit-jupiter-api")
	testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
	testImplementation("com.ninja-squad:springmockk:1.1.3")
}

allOpen {
	annotation("javax.persistence.Entity")
	annotation("javax.persistence.Embeddable")
	annotation("javax.persistence.MappedSuperclass")
}

tasks.withType<Test> {
	useJUnitPlatform()
}

// required by using kotlin with spring
tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict") // enable null-safety in spring framework (build with java)
		jvmTarget = "1.8" // config kotlin compiler for generating java 8 bytecode
	}
}
