package cn.staynoob.postcode.service

import cn.staynoob.postcode.entity.DemoEntity
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityManager

@Service
class CheckedExceptionService(
        private val demoRepo: DemoRepo,
        private val entityManager: EntityManager
) {
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    fun persist(demo: DemoEntity) {
        demoRepo.save(demo)
        entityManager.flush()
        throw Exception("Oops!")
    }
}