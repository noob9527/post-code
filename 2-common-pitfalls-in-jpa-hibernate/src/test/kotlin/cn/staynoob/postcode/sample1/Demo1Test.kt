package cn.staynoob.postcode.sample1

import cn.staynoob.postcode.support.base.TestBase
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import javax.persistence.EntityManager

@DataJpaTest
class Demo1Test : TestBase() {
    @Autowired
    private lateinit var entityManager: EntityManager

    @Test
    fun test() {
        val demo = Demo1()
        val set = hashSetOf(demo)

        assertThat(set.contains(demo)).isTrue()

        entityManager.persist(demo)
        entityManager.flush()

        assertThat(set.contains(demo)).isFalse()
    }
}