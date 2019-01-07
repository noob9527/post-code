package cn.staynoob.postcode.sample5

import org.springframework.data.jpa.domain.AbstractPersistable
import javax.persistence.*

@Entity
@Table(name = "child5")
class Child(
        @Column(unique = true)
        val name: String,

        @ManyToOne
        @JoinColumn
        val parent: Parent
) : AbstractPersistable<Int>() {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Child

        if (name != other.name) return false

        return true
    }

    override fun hashCode(): Int {
        var result = 17
        result = 31 * result + name.hashCode()
        return result
    }
}
