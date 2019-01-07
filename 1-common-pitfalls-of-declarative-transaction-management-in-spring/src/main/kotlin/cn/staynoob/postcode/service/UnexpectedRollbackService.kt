package cn.staynoob.postcode.service

import cn.staynoob.postcode.entity.DemoEntity
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityManager

/**
 * refer: https://stackoverflow.com/questions/19302196/transaction-marked-as-rollback-only-how-do-i-find-the-cause
 */
@Service
class UnexpectedRollbackService(
        private val demoRepo: DemoRepo,
        private val entityManager: EntityManager
) {

    @Autowired
    private lateinit var self: UnexpectedRollbackService

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    fun persist(demo: DemoEntity) {
        demoRepo.save(demo)
        entityManager.flush()

        try {
            self.anotherMethod()
        } catch (e: Exception) {
        }
    }

    @Transactional
    fun anotherMethod() {
        throw RuntimeException("Oops!")
    }
}