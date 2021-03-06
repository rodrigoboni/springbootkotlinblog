package com.rmboni.springbootkotlinblog.controller

import com.rmboni.springbootkotlinblog.repository.ArticleRepository
import com.rmboni.springbootkotlinblog.repository.UserRepository
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException

/*
notice some details about kotlin:
-the name of the file doesn't needs to correspond to the class name
-one file can hold more then one public class
-in class declaration there is an primary constructor, with @autowired annotation implicity included
-the elvis operator (?:) gives code simplicity and null safety
 */

@RestController
@RequestMapping("/api/article")
class ArticleController(private val repository: ArticleRepository) {

    @GetMapping("/")
    fun findAll() = repository.findAllByOrderByAddedAtDesc()

    @GetMapping("/{slug}")
    fun findOne(@PathVariable slug: String) =
        repository.findBySlug(slug) ?: ResponseStatusException(HttpStatus.NOT_FOUND, "This article does not exist")

}

@RestController
@RequestMapping("/api/user")
class UserController(private val repository: UserRepository) {

    @GetMapping("/")
    fun findAll() = repository.findAll()

    @GetMapping("/{login}")
    fun findOne(@PathVariable login: String) =
        repository.findByLogin(login) ?: ResponseStatusException(HttpStatus.NOT_FOUND, "This user does not exist")
}
