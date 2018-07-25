package blog

import org.springframework.stereotype.Component
import com.coxautodev.graphql.tools.GraphQLQueryResolver

@Component
class Query: GraphQLQueryResolver {
    fun version() = "1.0.0"
}
