package cn.staynoob.postcode.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import org.springframework.data.jpa.domain.AbstractPersistable
import javax.persistence.MappedSuperclass

@MappedSuperclass
abstract class AbstractEntity : AbstractPersistable<Int>() {
    @JsonIgnore
    override fun isNew(): Boolean {
        return super.isNew()
    }
}
