package cn.staynoob.postcode.sample3

import cn.staynoob.postcode.support.base.TestBase
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.hibernate.exception.ConstraintViolationException
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import javax.persistence.EntityManager

@DataJpaTest
class FlushOrderTest : TestBase() {

    @Autowired
    private lateinit var parentService3: ParentService3

    @Autowired
    private lateinit var entityManager: EntityManager

    @Test
    fun test300() {
        val parent = Parent()
        val child3 = Child("test", parent)
        val child2 = Child("test", parent)

        parent.addChild(child3)
        parentService3.save(parent)
        entityManager.flush()

        parent.children.clear()
        parent.addChild(child2)
        parentService3.save(parent)

        assertThatThrownBy { entityManager.flush() }
                .hasCauseInstanceOf(ConstraintViolationException::class.java)
    }
}