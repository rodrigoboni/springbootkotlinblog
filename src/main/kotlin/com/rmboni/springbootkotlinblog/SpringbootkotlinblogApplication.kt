package com.rmboni.springbootkotlinblog

import com.rmboni.springbootkotlinblog.configuration.BlogProperties
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableConfigurationProperties(BlogProperties::class) // enable support for configuration properties beans
class SpringbootkotlinblogApplication // class without brackets - include it if is needed to write some @Bean for instance

fun main(args: Array<String>) {
	// runApplication is the kotlin idiomatic alternative to springapplication.run(....)
	// this idiomatic expresion can be used to customize the app, adding brackets
	/* runApplication<BlogApplication>(*args) {
         setBannerMode(Banner.Mode.OFF)
       } */
	runApplication<SpringbootkotlinblogApplication>(*args)

	// notice de lack of semicolons, it's not required in kotlin
}
