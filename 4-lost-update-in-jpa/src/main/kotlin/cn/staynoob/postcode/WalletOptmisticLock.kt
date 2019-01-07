package cn.staynoob.postcode

import org.springframework.data.jpa.domain.AbstractPersistable
import javax.persistence.Entity
import javax.persistence.Version

@Entity
class WalletOptmisticLock(
        val name: String
) : AbstractPersistable<Int>() {
    var balance = 0

    @Version
    var version: Long = 0
}