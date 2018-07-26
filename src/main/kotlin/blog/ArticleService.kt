package blog

import org.springframework.stereotype.Service

@Service
class ArticleService(private val repository: ArticleRepository, private val userRepository: UserRepository) {

    fun getArticle(id: Long): Article? {
        return repository.findById(id).orElse(null)
    }

    fun saveArticle(title: String, content: String): Article {
        val author = User("testusername", "Firstname", "Surname")
        userRepository.save(author)
        val article = Article(title, content, author)
        return repository.save(article)
    }
}
