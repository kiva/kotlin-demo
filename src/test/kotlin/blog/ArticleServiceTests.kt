package blog

import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.boot.test.context.SpringBootTest
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.junit.jupiter.api.Assertions.assertEquals

@ExtendWith(SpringExtension::class)
@SpringBootTest
class ArticleServiceTests(@Autowired val service: ArticleService) {

    @Test
    fun `Assert getArticle(id) returns magic string`() {
        val value = service.getArticle(1)
        assertEquals("This is a great article.", value)
    }

}