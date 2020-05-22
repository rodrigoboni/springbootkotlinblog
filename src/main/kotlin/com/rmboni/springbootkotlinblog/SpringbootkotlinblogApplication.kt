package com.rmboni.springbootkotlinblog

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
// class without brackets - include it if is needed to write some @Bean for instance
class SpringbootkotlinblogApplication

fun main(args: Array<String>) {
	// runApplication is the kotlin idiomatic alternative to springapplication.run(....)
	// this idiomatic expresion can be used to customize the app, adding brackets
	/* runApplication<BlogApplication>(*args) {
         setBannerMode(Banner.Mode.OFF)
       } */
	runApplication<SpringbootkotlinblogApplication>(*args)
}