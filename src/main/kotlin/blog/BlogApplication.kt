package blog

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication


@SpringBootApplication
class BlogApplication {

    fun main(args: Array<String>) {
        runApplication<BlogApplication>(*args)
    }

    fun getAwesomeValue(): Int {
        return 1
    }
}
