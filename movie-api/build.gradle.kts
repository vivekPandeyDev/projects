
plugins {
	java
	id("org.springframework.boot") version "3.2.1"
	id("io.spring.dependency-management") version "1.1.4"
}

group = "com.vivek.pandey"
version = "0.0.1-SNAPSHOT"



java {
	sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
}

val poiVersion = "5.2.5"
val annotationsVersion = "24.1.0"
val modelMapperVersion = "3.2.0"

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("org.springframework.boot:spring-boot-starter-data-redis")
	implementation("org.springframework.boot:spring-boot-starter-batch")
	implementation("org.apache.poi:poi-ooxml:$poiVersion")
	implementation("org.apache.poi:poi:$poiVersion")
	implementation("org.jetbrains:annotations:$annotationsVersion")
	implementation("org.modelmapper:modelmapper:$modelMapperVersion")
	runtimeOnly("com.h2database:h2")
	compileOnly("org.projectlombok:lombok")
	developmentOnly("org.springframework.boot:spring-boot-devtools")
	annotationProcessor("org.projectlombok:lombok")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.springframework.batch:spring-batch-test")
}

tasks.withType<Test> {
	useJUnitPlatform()
}
