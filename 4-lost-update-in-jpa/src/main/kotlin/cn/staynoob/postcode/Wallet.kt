package cn.staynoob.postcode

import org.springframework.data.jpa.domain.AbstractPersistable
import javax.persistence.Entity

@Entity
class Wallet(
        val name: String
) : AbstractPersistable<Int>() {
    var balance = 0
}