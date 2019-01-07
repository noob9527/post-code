package cn.staynoob.postcode.service

import cn.staynoob.postcode.entity.DemoEntity
import cn.staynoob.postcode.support.base.TestBase
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

class PrivateMethodServiceTest : TestBase() {

    @Autowired
    private lateinit var privateMethodService: PrivateMethodService

    @Autowired
    private lateinit var demoRepo: DemoRepo

    @Test
    @DisplayName("@Transactional should not have effect")
    fun test() {
        val demo = DemoEntity("demo")
        assertThatThrownBy { privateMethodService.persist(demo) }
        val res = demoRepo.findById(demo.id!!)
        assertThat(res).isPresent
    }
}