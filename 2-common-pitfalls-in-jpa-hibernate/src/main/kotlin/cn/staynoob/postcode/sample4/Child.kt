package cn.staynoob.postcode.sample4

import org.springframework.data.jpa.domain.AbstractPersistable
import javax.persistence.Entity
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table

@Entity
@Table(name = "child4")
class Child(
        var name: String,

        @ManyToOne
        @JoinColumn
        val parent: Parent
) : AbstractPersistable<Int>()