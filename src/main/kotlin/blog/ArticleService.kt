package blog

import org.springframework.stereotype.Service

@Service
class ArticleService(private val articles: ArticleRepository, private val users: UserRepository) {

    fun getArticle(id: Long): Article? {
        return articles.findById(id).orElse(null)
    }

    fun saveArticle(title: String, content: String, author: User? = null): Article {
        val article = Article(title, content, author)
        return articles.save(article)
    }
}
