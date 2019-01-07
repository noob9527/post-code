package cn.staynoob.postcode.service

import cn.staynoob.postcode.entity.DemoEntity
import cn.staynoob.postcode.support.base.TestBase
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

class CheckedExceptionServiceTest : TestBase() {

    @Autowired
    protected lateinit var checkedExceptionService: CheckedExceptionService

    @Autowired
    protected lateinit var demoRepo: DemoRepo

    @Test
    @DisplayName("transaction should still be committed after throwing checked exception")
    fun persist() {
        val demo = DemoEntity("demo")
        assertThatThrownBy { checkedExceptionService.persist(demo) }
        val res = demoRepo.findById(demo.id!!)
        assertThat(res).isPresent
    }
}