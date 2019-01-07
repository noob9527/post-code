package cn.staynoob.postcode.sample1

import org.springframework.data.jpa.domain.AbstractPersistable
import javax.persistence.Entity

@Entity
class Demo1 : AbstractPersistable<Int>()