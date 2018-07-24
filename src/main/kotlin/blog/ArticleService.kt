package blog

import org.springframework.stereotype.Service

@Service
class ArticleService {

   fun getArticle(id: Int): String? {
       return "This is a great article."
   }
}