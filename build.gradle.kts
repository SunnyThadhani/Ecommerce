plugins {
	java
	id("org.springframework.boot") version "2.7.6"
	id("io.spring.dependency-management") version "1.0.15.RELEASE"
}

group = "com.order"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-web")

	//JunitMockito
	testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.0")
	testImplementation("org.junit.jupiter:junit-jupiter-engine:5.9.0")
	testImplementation("org.junit.platform:junit-platform-runner:1.9.0")
	testImplementation("org.mockito:mockito-inline:4.8.0")
	testImplementation("org.assertj:assertj-core:3.23.1")

	//Using Springdoc instead of springfox
	implementation("org.springdoc:springdoc-openapi-ui:1.6.11")

	implementation("org.postgresql:postgresql:42.5.0")

	//mapstruct
	implementation("org.mapstruct:mapstruct:1.4.2.Final")
	annotationProcessor("org.mapstruct:mapstruct-processor:1.4.2.Final")

	testCompileOnly("org.projectlombok:lombok:1.18.24")
	testAnnotationProcessor("org.projectlombok:lombok:1.18.24")

	annotationProcessor("org.projectlombok:lombok")
	compileOnly("org.projectlombok:lombok")

	testImplementation("org.springframework.boot:spring-boot-starter-test")

	//EmbeddedDB for tests
	testImplementation("io.zonky.test:embedded-postgres:2.0.0")
	testImplementation("io.zonky.test:embedded-database-spring-test:2.1.2")
}

tasks.withType<Test> {
	useJUnitPlatform()
}
