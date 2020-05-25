package com.rmboni.springbootkotlinblog.model

import toSlug
import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.ManyToOne

/*
Data classes is not used becausa JPA is not designed to work with immutable classes or the methods generated automatically by data classes.
With Spring Data MongoDB, Spring Data JDBC etc data classes can be used without any issues
 */

@Entity
class Article( // using primary constructor to declare at the same time properties and constructor parameters
        var title: String,
        var headline: String,
        var content: String,
        @ManyToOne var author: User,
        var slug: String = title.toSlug(), //using string extension
        var addedAt: LocalDateTime = LocalDateTime.now(),
        @Id @GeneratedValue var id: Long? = null)

// notice that in Kotlin is not unusual to group concise class declarations in the same file

@Entity
class User(
        var login: String,
        var firstname: String,
        var lastname: String,
        var description: String? = null, // its a good convention to put optional arguments (with default values) in the last position, to make possible to omit them when using positional arguments
        @Id @GeneratedValue var id: Long? = null)
