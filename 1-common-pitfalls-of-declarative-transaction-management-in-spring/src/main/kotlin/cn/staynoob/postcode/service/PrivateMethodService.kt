package cn.staynoob.postcode.service

import cn.staynoob.postcode.entity.DemoEntity
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityManager

@Service
class PrivateMethodService(
        private val demoRepo: DemoRepo,
        private val entityManager: EntityManager
) {

    fun persist(demo: DemoEntity) {
        annotatedMethod(demo)
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    private fun annotatedMethod(demo: DemoEntity) {
        demoRepo.save(demo)
        entityManager.flush()
        throw RuntimeException("Oops!")
    }
}