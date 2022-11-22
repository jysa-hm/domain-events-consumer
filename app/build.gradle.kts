plugins {
	id ("java")
	id("org.springframework.boot") version ("2.6.4")
	id("io.spring.dependency-management") version ("1.0.11.RELEASE")
	id("au.com.dius.pact") version "4.3.2"
	id("com.github.davidmc24.gradle.plugin.avro") version ("1.2.0")
	id("io.freefair.lombok") version "5.3.0"
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
			password = "eyJ2ZXIiOjEsImlzdSI6MTY2ODgwMzU0NCwiZW5jIjoiQTEyOEdDTSIsInRhZyI6InUwLTUtMHVjSHJDNTJ5MzVMdHROUFEiLCJleHAiOjE2Njg4NDY3NDQsImFsZyI6IkExMjhHQ01LVyIsIml2IjoicmkzeVN6LUpIbmpoR3JTTiJ9.efOegM7hLytWKF6GJglh1g.BzeYvyX1Jtk4ihEM.ibjXZwN64SUbtcgiq-PpVKXdbJu3xZaRVN4gyKfJ2hVm8xuhIwB1hMWyQ9sok2AeadfBFg6w7CrL5wREON3mWw27_o0mAyg8nYuMmGdRD4vYZjHqBSf8stHDQEU7PpkBLrRnkKWCbgGBdZ5Sd7pJvJMWlDR7c1aSc3-JH-QG48Z3QlCESPgSBXZPlL4qfhbBdMRChT7mnUGmfN8ra28za-E_4kbzBrGdi-mxg6aaNtRlRM9iW7xJkfSZgv_TtYT0vLSZhs0NIWMoQlvSq9BzKD-XwkQgRPR0W6vzDsPw4kNTHgRtZG8CAmqtyxpe3feAO22d12oxV--7stg0qWnmOLJu-ygb7TftRFFBF7LXLzRH5VO0a0A7oPbVc4J6MGr7IF0dE8dxaNod1TJbT5MVP2iPF11IUo8o8i4vwuWv-a0ajjfQv2fMETOIgxXAJ3CQPUKrF5qE-GtyHZJBUsHpdtOBy9wLb13YR8VxfFR4D86Yg4tbchBCl43FEzvFPVWu5KEjmEAxwFiEIOxNhfV-_7z43PDLLW9E7k_0ClBeANmiNIM8gka4yKYeVB8nnFhZ-DoBKmu-rnM5rUgWXwQOpmBDxeT4Al08zXptdBj-NhcSxpL71rR0shsoH278En_SxvNqFlgpoKzK6coRyODgF8LU9QtdXt8Xgj7_ocPC3njcU-4ci42k6Cz6G9ZS3zD1Tz-DWWQO7O5K9JOTA_fbFVUshZkciJLcPqeXPuay2qqwyX7jj9Yuoz-DTCU8kVo1D3gFVyBFTsQXw6o3uDYDcfhRkVpYSTECAR9bVsM5WGRmP_H1RQPuqE-sU6Wb7POJywNQ-Npr_s4KjoiJo8ZCi6mZOsb6piRzM9rhIgeLjxuxq_qCXr234yGsM5wYNmu9jkRI4gQg-zZhZjTjRxkXWm912ELqWKreoKqH6J68a4n6Ofw1DwQu7AtileNXECYqdFPilvs9s8cSXpnel9zyWpQ2rT828vL15YJR6y2naX0lOeINW93W2hDeH6t8AMptSWBRUCHX9mLzAkSyMtyZtszWnntmWmEfPbirQjfYbPSNe0mo-HUCFxRmCS2YlszQF5ZcHyti94zQ5p1Vco3QAf-BxcX95fYuxSSO0kYopV9SYXpqmB3YdHu6jhH3Eym-9zVWhDsqcjmFaER2kQOzBtG_Lu5qu1jaDfJbm-3jLTUcNyR6XXKzssVHB9szgg4jem34YrfIji9sH1AU_80WhJOqZZMKfsRgEUfqYW0q-IO9i_Uz2GVkUrgZ83cyJ0wXZ0Hhox4TP2SBhz8cgMbm5jK8mT1f.RfODOVdaijjWzj7JhO50Kw"
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
	annotationProcessor("org.projectlombok:lombok-mapstruct-binding:0.2.0")
	implementation("org.mapstruct:mapstruct:1.4.2.Final")

	testImplementation("au.com.dius.pact.provider:junit5spring:4.3.12")
	testImplementation("au.com.dius.pact.consumer:junit5:4.3.12")
//	testImplementation(files("/Users/jysa/Documents/poc/domain-events-consumer/app/src/test/resources/app-plain.jar"))
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
		tags = listOf("domain-events-consumer-release")
		consumerVersion = "0.0.2-SNAPSHOT"
	}
}
