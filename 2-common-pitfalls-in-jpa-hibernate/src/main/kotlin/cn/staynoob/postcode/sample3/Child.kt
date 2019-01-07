package cn.staynoob.postcode.sample3

import org.springframework.data.jpa.domain.AbstractPersistable
import javax.persistence.*

@Entity
@Table(
        name="child3",
        uniqueConstraints = [UniqueConstraint(columnNames = ["name", "parent_id"])]
)
class Child(
        val name: String,

        @ManyToOne
        @JoinColumn
        val parent: Parent
) : AbstractPersistable<Int>()