package blog

import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.junit.jupiter.api.Assertions.assertEquals

@ExtendWith(SpringExtension::class)
@SpringBootTest
class BlogApplicationTests(@Autowired val app: BlogApplication) {

	@Test
	fun `Assert getAwesomeValue() returns 1`() {
		val value = app.getAwesomeValue()
		assertEquals(1, value)
	}

}
