package cn.staynoob.postcode.sample5

import cn.staynoob.postcode.support.base.TestBase
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import javax.persistence.EntityManager

@DataJpaTest
class DeletionDiscardedTest : TestBase() {

    @Autowired
    private lateinit var entityManager: EntityManager

    @Test
    fun test100() {
        val parent = Parent()
        val child = Child("test", parent)
        parent.addChild(child)

        entityManager.persist(parent)
        entityManager.flush()

        entityManager.remove(child) // will be discarded silently
        entityManager.flush()

        val res = entityManager.find(Child::class.java, child.id)
        assertThat(res).isNotNull
    }
}