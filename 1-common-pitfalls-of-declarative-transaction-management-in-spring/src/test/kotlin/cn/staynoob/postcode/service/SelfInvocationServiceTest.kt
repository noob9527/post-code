package cn.staynoob.postcode.service

import cn.staynoob.postcode.entity.DemoEntity
import cn.staynoob.postcode.support.base.TestBase
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

class SelfInvocationServiceTest : TestBase() {

    @Autowired
    protected lateinit var selfInvocationService: SelfInvocationService

    @Autowired
    protected lateinit var demoRepo: DemoRepo

    @Test
    @DisplayName("@Transaction should not affect self-invocation")
    fun test1() {
        val demo = DemoEntity("demo")
        assertThatThrownBy { selfInvocationService.persist(demo) }
        assertThat(demoRepo.findById(demo.id!!)).isPresent
    }

    @Test
    @DisplayName("@Transaction should affect external invocation")
    fun test2() {
        val demo = DemoEntity("demo")
        assertThatThrownBy { selfInvocationService.annotatedMethod(demo) }
        assertThat(demoRepo.findById(demo.id!!)).isNotPresent
    }
}