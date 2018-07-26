package blog

import javax.persistence.Entity
import javax.persistence.ManyToOne
import javax.persistence.JoinColumn
import javax.persistence.Id
import javax.persistence.GeneratedValue
import java.time.LocalDateTime

@Entity
data class Article(
        val title: String,
        val content: String,
        @ManyToOne @JoinColumn val author: User,
        @Id @GeneratedValue val id: Long? = null,
        val addedAt: LocalDateTime = LocalDateTime.now())

@Entity
data class User(
        @Id val login: String,
        val firstname: String,
        val lastname: String,
        val description: String? = null)
