package com.mits4u.transactionsdemo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.net.MalformedURLException;
import java.net.URL;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ApplicationTests {


    @Value("${local.server.port}")
    private int port;

    private URL base;
    private TestRestTemplate template;

    @BeforeEach
    public void setUp() {
        template = new TestRestTemplate();
    }


    @Test
    void test__HappyPath() throws MalformedURLException {

        base = new URL("http://localhost:" + port + "/demo/success?name=aait");
        ResponseEntity<String> response = template.getForEntity(base.toString(), String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

    }

    @Test
    void test__failCheckedException() throws MalformedURLException {

        base = new URL("http://localhost:" + port + "/demo/cExc?name=baddy");
        ResponseEntity<String> response = template.getForEntity(base.toString(), String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);

    }

    @Test
    void test__failRuntimeException() throws MalformedURLException {

        base = new URL("http://localhost:" + port + "/demo/rExc?name=baddy");
        ResponseEntity<String> response = template.getForEntity(base.toString(), String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);

    }

}
