package cn.staynoob.postcode.sample3

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Service

interface ParentRepo3 : CrudRepository<Parent, Int>

@Service
class ParentService3(
        private val parentRepo3: ParentRepo3
) : ParentRepo3 by parentRepo3
