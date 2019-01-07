package cn.staynoob.postcode.sample1

import org.springframework.data.domain.Persistable
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class Demo2 : Persistable<Int> {
    @Id
    @GeneratedValue
    private var id: Int? = null

    override fun getId(): Int? {
        return id
    }

    override fun isNew(): Boolean {
        return id == null
    }
}