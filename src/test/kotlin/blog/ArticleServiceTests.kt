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

    @Test
    fun `Assert getArticle(id) returns an article if ID exists`() {
        val testId: Long = 1
        val testAuthor = User("testlogin", "First", "Last")
        val testArticle = Article("test title", "test content", testAuthor)

        // Stub mock ArticleRepository's findById method to return testArticle when testId is passed as an arg
        // The Optional.ofNullable funkiness is coordinate between the underlying Java library's handling of potential
        // nulls vs Kotlin's null handling
        given(articleRepository.findById(testId)).willReturn(Optional.ofNullable(testArticle))

        val fetched = service.getArticle(testId)

        assertEquals(testArticle, fetched)
        // verifies after the fact that the mock ArticleRepository's findById method was called with testId as argument
        // similar to expectations in phpunit, where we'd set up expectations for method calls before those method
        // calls were invoked, and the verification would happen implicitly during teardown
        // here we inspect method calls explicitly, so the call must have already happened
        verify(articleRepository).findById(testId)
    }

    @Test
    fun `Assert saveArticle() with no author saves an article`() {
        val testTitle = "Snappy Title"
        val testContent = "This is a great article"
        val testArticle = Article(testTitle, testContent)

        // Stub mock ArticleRepository's save method to return testArticle when any article is passed as an arg
        given(articleRepository.save(any<Article>())).willReturn(testArticle)

        val saved = service.saveArticle(testTitle, testContent)

        assertEquals(testTitle, saved.title)
        assertEquals(testContent, saved.content)
        // verifies after the fact that the mockArticleRepository's save method was called with an article meeting certain criteria
        // similar to expectations in phpunit, where we'd set up expectations for method calls before those method
        // calls were invoked, and the verification would happen implicitly during teardown
        // here we inspect method calls explicitly, so the call must have already happened
        verify(articleRepository).save(argThat { article ->
            (article.title == testTitle && article.content == testContent)
        })
    }
}
