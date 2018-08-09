package blog

import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.junit.jupiter.api.Assertions.assertEquals

@ExtendWith(SpringExtension::class)
@DataJpaTest
class RepositoriesTests(
    @Autowired val entityManager: TestEntityManager,
    @Autowired val userRepository: UserRepository,
    @Autowired val articleRepository: ArticleRepository
) {

    @Test
    fun `When findById then return Article`() {
        val user = getUser()
        entityManager.persist(user)
        val article = Article("Awesome Title", "Snappy headline", user)
        entityManager.persist(article)
        entityManager.flush()

        val found = articleRepository.findById(article.id!!)

        assertEquals(article, found.get())
    }

    private fun getUser() = User("testusername", "Firstname", "Surname")

    @Test
    fun `When findById then return User`() {
        val user = getUser()
        entityManager.persist(user)
        entityManager.flush()

        val found = userRepository.findById(user.login)

        assertEquals(user, found.get())
    }
}