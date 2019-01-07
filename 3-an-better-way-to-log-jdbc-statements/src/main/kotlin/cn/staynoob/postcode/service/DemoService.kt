package cn.staynoob.postcode.service

import cn.staynoob.postcode.entity.DemoEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Service


interface DemoRepo : JpaRepository<DemoEntity, Int>

@Service
class DemoService(
        private val demoRepo: DemoRepo
) : DemoRepo by demoRepo