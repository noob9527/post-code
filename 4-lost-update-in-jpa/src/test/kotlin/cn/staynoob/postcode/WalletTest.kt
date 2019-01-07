package cn.staynoob.postcode

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.TransactionDefinition
import org.springframework.transaction.support.TransactionTemplate
import javax.persistence.EntityManager
import javax.persistence.LockModeType

@SpringBootTest
class WalletTest {

    @Autowired
    private lateinit var entityManager: EntityManager

    @Autowired
    private lateinit var transactionManager: PlatformTransactionManager


    @Test
    @DisplayName("lost update phenomenon")
    fun test100() {
        val transactionTemplate = TransactionTemplate(transactionManager)
        val wallet = Wallet("lostUpdate")

        transactionTemplate.execute {
            entityManager.persist(wallet)
            entityManager.flush()
        }

        val thread1 = TestThread {
            transactionTemplate.execute {
                val entity = entityManager.find(Wallet::class.java, wallet.id)
                entity.balance += 1000
                Thread.sleep(2000)
            }
        }

        val thread2 = TestThread {
            transactionTemplate.execute {
                val entity = entityManager.find(Wallet::class.java, wallet.id)
                entity.balance += 1000
            }
        }

        thread1.start()

        thread2.start()
        thread2.join()

        thread1.join()
    }

    @Test
    @DisplayName("isolation level")
    fun test200() {
        val transactionTemplate = TransactionTemplate(transactionManager)
        transactionTemplate.isolationLevel = TransactionDefinition.ISOLATION_SERIALIZABLE

        val wallet = Wallet("serializable")

        transactionTemplate.execute {

            entityManager.persist(wallet)
            entityManager.flush()
        }

        val thread1 = TestThread {
            transactionTemplate.execute {
                val entity = entityManager.find(Wallet::class.java, wallet.id)
                entity.balance += 1000
                Thread.sleep(2000)
            }
        }

        val thread2 = TestThread {
            transactionTemplate.execute {
                val entity = entityManager.find(Wallet::class.java, wallet.id)
                entity.balance += 1000
            }
        }

        thread1.start()

        thread2.start()
        thread2.join()

        thread1.join()
    }

    @Test
    @DisplayName("pessimistic lock")
    fun test300() {
        val transactionTemplate = TransactionTemplate(transactionManager)

        val wallet = Wallet("pessimisticLock")

        transactionTemplate.execute {

            entityManager.persist(wallet)
            entityManager.flush()
        }

        val thread1 = TestThread {
            transactionTemplate.execute {
                val entity = entityManager.find(Wallet::class.java, wallet.id, LockModeType.PESSIMISTIC_WRITE)
                entity.balance += 1000
                Thread.sleep(2000)
            }
        }

        val thread2 = TestThread {
            transactionTemplate.execute {
                val entity = entityManager.find(Wallet::class.java, wallet.id, LockModeType.PESSIMISTIC_WRITE)
                entity.balance += 1000
            }
        }

        thread1.start()

        thread2.start()
        thread2.join()

        thread1.join()
    }

    @Test
    @DisplayName("optimistic lock")
    fun tes4300() {
        val transactionTemplate = TransactionTemplate(transactionManager)
        val wallet = WalletOptmisticLock("optimisticLock")

        transactionTemplate.execute {
            entityManager.persist(wallet)
            entityManager.flush()
        }

        val thread1 = TestThread {
            transactionTemplate.execute {
                val entity = entityManager.find(WalletOptmisticLock::class.java, wallet.id)
                entity.balance += 1000
                Thread.sleep(2000)
            }
        }

        val thread2 = TestThread {
            transactionTemplate.execute {
                val entity = entityManager.find(WalletOptmisticLock::class.java, wallet.id)
                entity.balance += 1000
            }
        }

        thread1.start()

        thread2.start()
        thread2.join()

        thread1.join()
    }
}