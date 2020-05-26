package com.rmboni.springbootkotlinblog.configuration

import com.rmboni.springbootkotlinblog.model.Article
import com.rmboni.springbootkotlinblog.model.User
import com.rmboni.springbootkotlinblog.repository.ArticleRepository
import com.rmboni.springbootkotlinblog.repository.UserRepository
import org.springframework.boot.ApplicationRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class BlogConfiguration {

    @Bean
    // function which returns an ApplicationRunner (executed in application initialization)
    fun databaseInitializer(userRepository: UserRepository,
                            articleRepository: ArticleRepository) = ApplicationRunner {

        val user = userRepository.save(User("ze_mockinho", "Zé", "Mockinho"))

        // notice the usage of named parameters to make code more readable
        articleRepository.save(
            Article(
            title = "Reactor Bismuth is out",
            headline = "Lorem ipsum",
            content = "dolor sit amet",
            author = user
        )
        )
        articleRepository.save(Article(
            title = "Reactor Aluminium has landed",
            headline = "Lorem ipsum",
            content = "dolor sit amet",
            author = user
        ))
    }
}
