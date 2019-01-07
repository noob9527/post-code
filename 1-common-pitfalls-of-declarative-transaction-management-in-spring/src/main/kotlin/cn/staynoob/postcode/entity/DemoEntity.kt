package cn.staynoob.postcode.entity

import org.springframework.data.jpa.domain.AbstractPersistable
import javax.persistence.Entity

@Entity
class DemoEntity(
        val name: String
) : AbstractPersistable<Int>()