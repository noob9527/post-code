package cn.staynoob.postcode.service

import cn.staynoob.postcode.entity.DemoEntity
import cn.staynoob.postcode.support.base.TestBase
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.transaction.UnexpectedRollbackException

class UnexpectedRollbackServiceTest : TestBase() {

    @Autowired
    private lateinit var unexpectedRollbackService: UnexpectedRollbackService

    @Autowired
    protected lateinit var demoRepo: DemoRepo

    @Test
    @DisplayName("transaction should still be rolled back after exception is caught and handled")
    fun persist() {
        val demo = DemoEntity("demo")

        assertThatThrownBy { unexpectedRollbackService.persist(demo) }
                .isInstanceOf(UnexpectedRollbackException::class.java)

        assertThat(demoRepo.findById(demo.id!!)).isNotPresent
    }
}