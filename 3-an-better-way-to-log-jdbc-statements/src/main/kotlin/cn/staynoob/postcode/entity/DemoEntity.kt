package cn.staynoob.postcode.entity

import javax.persistence.Entity

@Entity
class DemoEntity(
        val name: String
) : AbstractEntity()