plugins {
	val kotlinVersion = "1.7.10"
	val springBootVersion = "3.2.5"
	kotlin("plugin.allopen") version kotlinVersion
	kotlin("plugin.jpa") version kotlinVersion
	id("org.jetbrains.kotlin.jvm") version kotlinVersion
	id("org.jetbrains.kotlin.kapt") version kotlinVersion
	id("org.springframework.boot") version springBootVersion apply false
	id("org.jetbrains.kotlin.plugin.spring") version kotlinVersion apply false
	id("org.sonarqube") version "3.0"
}

repositories {
	mavenCentral()
}

apply(plugin = "kotlin")
apply(plugin = "kotlin-kapt")
apply(plugin = "kotlin-jpa")
apply(plugin = "kotlin-allopen")
apply(plugin = "org.springframework.boot")
apply(plugin = "io.spring.dependency-management")
apply(plugin = "org.jetbrains.kotlin.plugin.spring")

group = "com.moin.api"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_17
	targetCompatibility = JavaVersion.VERSION_17
}

allOpen {
	annotation("javax.persistence.Entity")
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.springframework.boot:spring-boot-starter-logging")
	implementation("org.springframework.security:spring-security-jwt:1.0.10.RELEASE")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("com.querydsl:querydsl-jpa:4.2.1")
	kapt("com.querydsl:querydsl-apt:4.2.1:jpa")

	runtimeOnly("org.springframework.boot:spring-boot-devtools")

	runtimeOnly("com.h2database:h2")

	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.springframework.security:spring-security-test")
	testImplementation("org.junit.jupiter:junit-jupiter:5.9.0")
}

tasks {
	compileKotlin {
		kotlinOptions {
			freeCompilerArgs = listOf("-Xjsr305=strict")
			jvmTarget = "17"
		}
		dependsOn(processResources)
	}

	compileTestKotlin {
		kotlinOptions {
			freeCompilerArgs = listOf("-Xjsr305=strict")
			jvmTarget = "17"
		}
	}

	test{
		useJUnitPlatform()
	}
}