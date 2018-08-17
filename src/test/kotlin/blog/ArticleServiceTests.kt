package blog

import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.boot.test.context.SpringBootTest
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.junit.jupiter.api.Assertions.assertEquals
import org.springframework.boot.test.mock.mockito.MockBean
import org.mockito.BDDMockito.given
import java.util.Optional
import org.mockito.BDDMockito.any
import org.mockito.BDDMockito.verify
import org.mockito.BDDMockito.argThat

@ExtendWith(SpringExtension::class)
@SpringBootTest
class ArticleServiceTests(@Autowired val service: ArticleService) {

    @MockBean
    private lateinit var articleRepository: ArticleRepository

    @MockBean
    private lateinit var userRepository: UserRepository

    @Test
    fun `Assert getArticle(id) returns an article if ID exists`() {
        val testId: Long = 1
        val author = User("testlogin", "First", "Last")
        val testArticle = Article("test title", "test content", author)

        given(articleRepository.findById(testId)).willReturn(Optional.ofNullable(testArticle))

        val fetched = service.getArticle(testId)

        assertEquals(testArticle, fetched)
        verify(articleRepository).findById(testId)
    }

    @Test
    fun `Assert saveArticle() saves an article`() {
        val testTitle = "Snappy Title"
        val testContent = "This is a great article"
        val author = User("testlogin", "First", "Last")
        val testArticle = Article(testTitle, testContent, author)

        given(userRepository.save(any<User>())).willReturn(author)
        given(articleRepository.save(any<Article>())).willReturn(testArticle)

        val saved = service.saveArticle(testTitle, testContent)

        assertEquals(testTitle, saved.title)
        assertEquals(testContent, saved.content)
        verify(articleRepository).save(argThat {article ->
            (article.title == testTitle && article.content == testContent)
        })
    }
}