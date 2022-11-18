plugins {
	id ("java")
	id("org.springframework.boot") version ("2.6.4")
	id("io.spring.dependency-management") version ("1.0.11.RELEASE")
	id("au.com.dius.pact") version "4.3.2"
	id("com.github.davidmc24.gradle.plugin.avro") version ("1.2.0")
}

group = "com.happymoney.webhook_domain_events_consumer"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_11
}


repositories {
	mavenLocal()
	mavenCentral()
	maven("https://packages.confluent.io/maven/")
	maven {
		url = uri("https://happymoney-730502903637.d.codeartifact.us-east-1.amazonaws.com/maven/main/")
		credentials {
			username = "aws"
			password = "eyJ2ZXIiOjEsImlzdSI6MTY2ODczMTk2MiwiZW5jIjoiQTEyOEdDTSIsInRhZyI6InhiVThnOWtudFQzQjhnakctcHd3TXciLCJleHAiOjE2Njg3NzUxNjIsImFsZyI6IkExMjhHQ01LVyIsIml2IjoiVVpQdWsxQnJpdGFOTlRCMCJ9.b6wWVJXOFRCq2PNQzOFZIw.Arm-yuFJPSrAeC5G.RVIclSwBuWlulCAR5M7nIhkHmx5zaHSkSFQOUPEGmKGcm2SdKzbJr5QIhQQZTPQmPWjlptYzpsxJv0OneQCcud_OE05UwTAxYfG3Dd8ZH-oVSLHmGUQtPy2V8IR7xteHhdiKqe5Xu2cZZHW0CxSJaShFqmpzW69-GkjuCZstDE4SvUOk6Y5hrbYa33jBnb8HY6EnfP2MJhBLIoRQLyeMuJvXstzl_HNX7TREVJx9owO94sdNL4dSnbEXNblSyPw7_l6pTaBPgE8olq5RIryKU3tdbg8CpMEulVYprlSI4oOHpVjtPlFU4_WcIhP6aJ31DljkYXitzxffN8_vfhCd1nZAo3feKoYG6GDuio6n-TNlRrPQIrjvjwQDBeKWWIRbWC9NYUlGG_hR8Qzh9zguXeNs5jxzPN9xO0_rT5Kg88fr8JNMJYYGOP2cmPz_URW7VO-gm5XKj42UQwbQk_tN0KyLay7V8h2zuHd5bawO-ejVdskiEGYE7yPjfBkmUeUZxLO7PRtzDc71Xg2j5cHH7-PTW2keBcg3bO44ljdJfVgORIi0D5g_TAU5womt7ytEgB-pratPIJVS2yWvKpbp60i4nx8n6Bnr5Pll_gae3Tc8vyfb002eBnBIHjew63t-xUAsIZT7ks7Hkl-kgOdPtm8kX9S2EazaRbgOt1k6kSZsc1yczrhvJQoM_VFwtFvB-8ByQVdPsbCyj9MohZ1Y8k7vgybcyKBJfRNu-Ie9WqMBVqkUSf2_nXnC7b0vGDbSZvum3YzWQe2UZZejCwaYDnKM23EYOWLX690vJuWQk2BuVwMBq9bjky2CtOuvlzcYYEHL17GVXBLrwUEQX_Ny1rpj2duJqTcY_KHth6-938bKhd6YNeJcMLsr2SVXkicae-wI6iVhbs8l-Lbo9pm7hbdO2SdCg6ZMJSe2iL9BUfTrXbxJ86Ui8MyZBBq7zqzfElqgYq-wyUJJSqfvrQPNkY7cpgblUY5ewbABzANbdw01ZY8FiQBQIs9MQO7lQquCUcPcmmoKiBtxocGa-BMQLWJL0wWdw-vyGktaq1sCKEDuBxgCv4DVejNlzZQdDJZb9vKpTbbkCYe0KPcJCd-omJ-1P2IPXwdCevQ-8ERBmIwzLzqvWRc2gYVsMmZpQzVRh6jgqqKIWKSWfUJzrfAYK9kKcENGsC0ZDdvDCuYMk2yizsvXWaFWGtXqN4mKuE2K6a08l_JlPy4ObNQ7QRJa0qy_UiOktjwWVnsZu_v7DfUQ8MXh5pbKctkzoW3tVaR9NczkDDW_3xNrdHVFlR4ztiSTGw.tLOq2IphbDK74CdMw9jPlA"
		}
	}
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter")
	implementation("org.seleniumhq.selenium:selenium-java:3.141.59")
	implementation("org.springframework.kafka:spring-kafka")

	testImplementation("org.junit.jupiter:junit-jupiter:5.8.2")
	testImplementation("org.testcontainers:testcontainers:1.17.3")
	testImplementation("org.testcontainers:junit-jupiter:1.17.3")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.testcontainers:kafka:1.17.3")
	testImplementation("org.springframework.kafka:spring-kafka-test")
	testImplementation("io.github.bonigarcia:webdrivermanager:5.2.3")

	testImplementation("au.com.dius.pact.provider:junit5spring:4.3.12")
	testImplementation("au.com.dius.pact.consumer:junit5:4.3.12")
	testImplementation(files("app/src/test/resources/app-plain.jar"))
	implementation("org.apache.avro:avro:1.10.0")
	implementation("io.confluent:kafka-avro-serializer:6.2.0")
}

configurations {
	all {
		exclude(group = "org.springframework.boot", module = "spring-boot-starter-logging")
	}
}

tasks {
	test{
		useJUnitPlatform()
	}
}

// For canideploy
pact {
	broker {
		pactBrokerUrl = "https://happymoney.pactflow.io"
		pactBrokerToken = System.getenv("PACT_BROKER_TOKEN")
	}
}

// For pactPublish
pact {
	publish {
		pactDirectory = "app/build/pacts"
		pactBrokerUrl = "https://happymoney.pactflow.io"
		pactBrokerToken = System.getenv("PACT_BROKER_TOKEN")
		tags = listOf("domain-events-consumer")
		consumerVersion = "0.0.1-SNAPSHOT"
	}
}
