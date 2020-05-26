package com.rmboni.springbootkotlinblog.controller

import com.rmboni.springbootkotlinblog.model.Article
import com.rmboni.springbootkotlinblog.model.User
import com.rmboni.springbootkotlinblog.repository.ArticleRepository
import format
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.server.ResponseStatusException

@Controller
// when there is a single and primary constructor, @Autowired is implicity included
class HtmlController(private val repository: ArticleRepository) {

    @GetMapping("/")
    fun blog(model: Model): String {
        // with org.springframework.ui.set extension it's possible to write no model obj directly, instead of model.addAttribute()
        model["title"]="Blog"

        // map runs in each result, referred as "it" (scope function)
        model["articles"] = repository.findAllByOrderByAddedAtDesc().map {
            it.render()
        }

        return "blog"
    }

    @GetMapping("/article/{slug}")
    fun article(@PathVariable slug: String, model: Model): String {
        val article = repository
            .findBySlug(slug)
            // here ? means if not null
            ?.render()
            // ?: = elvis operator - if value is not null use it, otherwise return the right value (or throw an exception in this case)
            ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "This article does not exist")

        model["title"] = article.title
        model["article"] = article

        return "article"
    }

    // extension on Article class
    fun Article.render() = RenderedArticle( // this syntax means this function returns an RenderedArticle
        slug,
        title,
        headline,
        content,
        author,
        addedAt.format() // using localdatetime extension
    )

    data class RenderedArticle(
        val slug: String,
        val title: String,
        val headline: String,
        val content: String,
        val author: User,
        val addedAt: String
    )
}
