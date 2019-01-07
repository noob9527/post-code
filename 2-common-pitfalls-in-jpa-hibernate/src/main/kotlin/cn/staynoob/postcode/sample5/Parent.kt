package cn.staynoob.postcode.sample5

import org.springframework.data.jpa.domain.AbstractPersistable
import javax.persistence.CascadeType
import javax.persistence.Entity
import javax.persistence.OneToMany
import javax.persistence.Table

@Entity
@Table(name = "parent5")
class Parent : AbstractPersistable<Int>() {

    @OneToMany(mappedBy = "parent", cascade = [CascadeType.PERSIST])
    val children: MutableSet<Child> = mutableSetOf()

    fun addChild(child: Child) {
        children.add(child)
    }

    fun removeChild(child: Child) {
        children.remove(child)
    }
}
