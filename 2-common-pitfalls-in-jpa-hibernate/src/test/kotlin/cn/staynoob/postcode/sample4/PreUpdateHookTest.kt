package cn.staynoob.postcode.sample4

import cn.staynoob.postcode.support.base.TestBase
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import javax.persistence.EntityManager

@DataJpaTest
class PreUpdateHookTest : TestBase() {

    @Autowired
    private lateinit var entityManager: EntityManager

    @Test
    fun test100() {
        val parent = Parent()

        entityManager.persist(parent)
        entityManager.flush()

        println(parent.updateTimes) // 0

        parent.children = mutableSetOf(Child("test1", parent))
        entityManager.merge(parent)
        entityManager.flush()

        println(parent.updateTimes) // 1

        parent.children.add(Child("test2", parent))
        entityManager.merge(parent)
        entityManager.flush()

        println(parent.updateTimes) // 1
    }
}