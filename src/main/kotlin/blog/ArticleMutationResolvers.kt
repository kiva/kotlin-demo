package blog

import org.springframework.stereotype.Component
import com.coxautodev.graphql.tools.GraphQLMutationResolver

@Component
class ArticleMutationResolvers(private val service: ArticleService): GraphQLMutationResolver {
    fun saveArticle(title: String?, content: String?): Article {
        return service.saveArticle(title ?: "My Great Article", content ?: "This is a wonderfully written sentence.")
    }
}
