package cn.staynoob.postcode.sample3

import org.springframework.data.jpa.domain.AbstractPersistable
import javax.persistence.Entity
import javax.persistence.Table
import javax.persistence.UniqueConstraint

@Entity
@Table(uniqueConstraints = [
    UniqueConstraint(columnNames = ["name"])
])
class FlushOrderDemo(
        val name: String
) : AbstractPersistable<Int>()
