package com.paperstack.cryptids;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.ComponentScan;

import static io.restassured.RestAssured.given;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@EnableAutoConfiguration
class CryptidsApplicationTests {

	@SpringBootConfiguration
	@ComponentScan(basePackages = "com.paperstack.cryptids")
	public static class CryptidsApplicationTestsConfiguration {
	}

	@LocalServerPort
	private int port;

	@BeforeEach
	public void beforeEach() {
		RestAssured.port = port;
	}

	/**
	 * Simple example that demonstrates that the "encryption" is carried out only on the appropriate fields and
	 * that omitting the {@link com.paperstack.cryptids.annotation.Encrypted} annotation is fine.
	 */
	@Test
	void verifySimplePayload() {
		given()
			.body("{ \"socialSecurityNumber\":\"secret-a\", \"duperSecret\":\"secret-b\", \"notSoSecret\":\"secret-c\" }")
			.contentType(ContentType.JSON)
		.when()
			.post("/example")
		.then()
			.statusCode(200)
			.body(CoreMatchers.equalTo("SECRET-A/-:SECRET-B/-:secret-c"));
	}

	/**
	 * A more complex example ("tricky" is overstating it) that shows that the Jackson property names are honoured
	 * for the {@link com.paperstack.cryptids.annotation.Plaintext} annotation, and that the value of the
	 * {@link com.paperstack.cryptids.annotation.Encrypted} annotation is passed to the {@link Encryption}
	 * interface method call.
	 */
	@Test
	void verifyTrickyPayload() {
		given()
			.body("{ \"socialSecurityNumber\":\"secret-a\", \"duperSecret\":\"secret-b\", \"notSoSecret\":\"secret-c\" }")
			.contentType(ContentType.JSON)
		.when()
			.post("/tricky")
		.then()
			.statusCode(200)
			.body(CoreMatchers.equalTo("SECRET-A/WOW:SECRET-B/WOW:secret-c"));
	}
}
