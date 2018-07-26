package blog

import org.springframework.stereotype.Component
import com.coxautodev.graphql.tools.GraphQLQueryResolver

@Component
class Query(private val service: ArticleService): GraphQLQueryResolver {
    fun version() = "0.1.2"

    fun getArticleById(id: String): Article? {
       return service.getArticle(id.toLong())
    }
}
