package cn.staynoob.postcode.service

import cn.staynoob.postcode.entity.DemoEntity
import org.springframework.data.repository.CrudRepository

interface DemoRepo : CrudRepository<DemoEntity, Int>