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
    fun `Assert getArticle(id) returns null on nonexistent ID`() {
        val nonExistentId = 3141592654
        val value = service.getArticle(nonExistentId)
        assertEquals(null, value)
    }

    @Test
    fun `Assert saveArticle() saves an article`() {
        val title = "Snappy Title"
        val content = "This is a really great article"
        val saved = service.saveArticle(title, content)
        assertEquals(title, saved.title)
        assertEquals(content, saved.content)
    }
}