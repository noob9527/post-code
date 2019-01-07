package cn.staynoob.postcode.api

import cn.staynoob.postcode.entity.DemoEntity
import cn.staynoob.postcode.service.DemoService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/demo")
class DemoCtrl(
        private val demoService: DemoService
) {
    @GetMapping
    fun findAll(): List<DemoEntity> {
        return demoService.findAll()
    }

    @PostMapping
    fun create(
            @RequestBody
            demo: DemoEntity
    ): DemoEntity {
        return demoService.save(demo)
    }
}