package com.rmboni.springbootkotlinblog

import com.ninjasquad.springmockk.MockkBean
import com.rmboni.springbootkotlinblog.model.Article
import com.rmboni.springbootkotlinblog.model.User
import com.rmboni.springbootkotlinblog.repository.ArticleRepository
import com.rmboni.springbootkotlinblog.repository.UserRepository
import io.mockk.every
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

@WebMvcTest
class HttpControllerTests(@Autowired val mockMvc: MockMvc) { //

    @MockkBean
    // lateinit fix the requirement of setting property type with nullable(?) - mockito will initialize these properties after compiled
    private lateinit var userRepository: UserRepository

    @MockkBean
    private lateinit var  articleRepository: ArticleRepository

    @Test
    fun `List articles`() {
        val user = User("ze_mockinho", "ze", "mockinho")
        val article1 = Article("Spring framework", "Spring framework is cool", "Ze mockinho viaja na maionese", user)
        val article2 = Article("Serverless", "Serverless + JS + AWS", "Ze mockinho se perde no js", user)

        // mocking repository behavior
        every { articleRepository.findAllByOrderByAddedAtDesc() } returns listOf(article1, article2)

        // performing mocked http request and checking results
        mockMvc.perform(get("/api/article/").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("\$.[0].author.login").value(user.login))
                .andExpect(jsonPath("\$.[0].slug").value(article1.slug))
                .andExpect(jsonPath("\$.[1].author.login").value(user.login))
                .andExpect(jsonPath("\$.[1].slug").value(article2.slug))
    }

    @Test
    fun `List users`() {
        val juergen = User("springjuergen", "Juergen", "Hoeller")
        val smaldini = User("smaldini", "Stéphane", "Maldini")

        every { userRepository.findAll() } returns listOf(juergen, smaldini)

        mockMvc.perform(get("/api/user/").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("\$.[0].login").value(juergen.login))
                .andExpect(jsonPath("\$.[1].login").value(smaldini.login))
    }
}
