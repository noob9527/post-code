package cn.staynoob.postcode.sample3

import cn.staynoob.postcode.support.base.TestBase
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.hibernate.exception.ConstraintViolationException
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import javax.persistence.EntityManager

@DataJpaTest
class FlushOrderTest2 : TestBase() {

    @Autowired
    private lateinit var entityManager: EntityManager

    @Test
    fun test100() {
        val parent1 = FlushOrderDemo("foo")
        val parent2 = FlushOrderDemo("foo")

        entityManager.persist(parent1)
        entityManager.flush()

        entityManager.remove(parent1)
        entityManager.persist(parent2)

        assertThatThrownBy { entityManager.flush() }
                .hasCauseInstanceOf(ConstraintViolationException::class.java)

    }
}