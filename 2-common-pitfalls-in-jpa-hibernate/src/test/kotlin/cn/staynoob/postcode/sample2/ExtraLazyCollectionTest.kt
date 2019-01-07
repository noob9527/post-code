package cn.staynoob.postcode.sample2

import cn.staynoob.postcode.support.base.TestBase
import org.hibernate.Hibernate
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import javax.persistence.EntityManager

@DataJpaTest
class ExtraLazyCollectionTest : TestBase() {

    @Autowired
    private lateinit var entityManager: EntityManager

    @Test
    fun test100() {
        val tmp = Parent()
                .apply {
                    addChild(Child("test", this))
                }
        entityManager.persist(tmp)
        entityManager.flush()
        entityManager.clear()

        val parent = entityManager.find(Parent::class.java, tmp.id)
        val child = Child("test", tmp)

        println(parent.children.contains(child))    // false
        Hibernate.initialize(parent.children)
        println(parent.children.contains(child))    // true
    }
}