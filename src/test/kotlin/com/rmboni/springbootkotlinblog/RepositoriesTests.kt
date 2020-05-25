package com.rmboni.springbootkotlinblog

import com.rmboni.springbootkotlinblog.model.Article
import com.rmboni.springbootkotlinblog.model.User
import com.rmboni.springbootkotlinblog.repository.ArticleRepository
import com.rmboni.springbootkotlinblog.repository.UserRepository
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import org.springframework.data.repository.findByIdOrNull

@DataJpaTest
class RepositoriesTests @Autowired constructor(
    val entityManager: TestEntityManager,
    val userRepository: UserRepository,
    val articleRepository: ArticleRepository) {

    @Test
    fun `When findByIdOrNull then return article`() {
        val zeMockinho = User("ze_mockinho", "ze", "mockinho", "Caro zé mockinho...lorem ipsum")
        entityManager.persist(zeMockinho)

        val article = Article("Zé mockinho fica doidão", "lorem ipsum", "bla bla bla", zeMockinho)
        entityManager.persist(article)

        entityManager.flush()

        // findByIdOrNull is a variant of findById, which returns null if no result is found, instead of Optional interface
        val found = articleRepository.findByIdOrNull(article.id!!) // !! operator converts any value to a non-null type and throws an exception if value is null
        assertThat(found).isEqualTo(article)
    }

    @Test
    fun `When findByLogin then return user`() {
        val zeMockinho = User("ze_mockinho", "ze", "mockinho", "Caro zé mockinho...lorem ipsum")
        entityManager.persist(zeMockinho)
        entityManager.flush()

        val user = userRepository.findByLogin(zeMockinho.login)
        assertThat(zeMockinho).isEqualTo(zeMockinho)
    }
}
