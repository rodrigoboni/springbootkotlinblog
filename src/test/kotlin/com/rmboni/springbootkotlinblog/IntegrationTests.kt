package com.rmboni.springbootkotlinblog

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.getForEntity
import org.springframework.http.HttpStatus

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class IntegrationTests(@Autowired val restTemplate: TestRestTemplate) {

    /*
    The annotations @BeforeAll and @AfterAll are required to be static in Junit
    In Kotlin this translates to Companion object, which is quite verbose and not straight forward
    because test classes are instantiated one time per test
    For chance this behavior, junit is configured in junit-platform.properties file
    This way the annotations could be used direct
     */
    @BeforeAll
    fun setup() {
        println(">> Setup")
    }

    @Test
    fun `Assert blog page title, content and status code`() {
        val entity = restTemplate.getForEntity<String>("/")
        Assertions.assertThat(entity.statusCode).isEqualTo(HttpStatus.OK)
        Assertions.assertThat(entity.body).contains("<h1>Blog</h1>")
    }

    @AfterAll
    fun teardown() {
        println(">> Tear down")
    }
}
