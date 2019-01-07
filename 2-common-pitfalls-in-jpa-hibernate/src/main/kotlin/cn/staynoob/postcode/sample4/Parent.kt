package cn.staynoob.postcode.sample4

import org.springframework.data.jpa.domain.AbstractPersistable
import javax.persistence.*

@Entity
@Table(name = "parent4")
class Parent : AbstractPersistable<Int>() {

    @OneToMany(mappedBy = "parent", cascade = [CascadeType.ALL])
    var children: MutableSet<Child> = mutableSetOf()

    fun addChild(child: Child) {
        children.add(child)
    }

    fun removeChild(child: Child) {
        children.remove(child)
    }

    var updateTimes: Int = 0
        private set

    @PreUpdate
    fun preUpdate() {
        updateTimes++
    }

}