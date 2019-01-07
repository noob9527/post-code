package cn.staynoob.postcode.aop

import net.ttddyy.dsproxy.QueryCountHolder
import org.aspectj.lang.JoinPoint
import org.aspectj.lang.annotation.*
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes
import java.time.Instant
import java.util.*

@Aspect
@Component
class ApiLogAspect {
    private val log = LoggerFactory.getLogger(ApiLogAspect::class.java)

    private val threadLocal = ThreadLocal<ApiLog>()

    @Pointcut("execution(* cn.staynoob.postcode.api..*.*(..))")
    fun isController() {
    }

    @Before("cn.staynoob.postcode.aop.ApiLogAspect.isController()")
    fun doBefore(joinPoint: JoinPoint) {
        val startTime = Instant.now()
        val attributes = RequestContextHolder.getRequestAttributes() as ServletRequestAttributes
        val request = attributes.request

        val apiLog = ApiLog(
                ip = request.remoteAddr,
                url = request.requestURL.toString(),
                method = request.method,
                classMethod = "${joinPoint.signature.declaringTypeName}.${joinPoint.signature.name}",
                startTime = startTime
        )
        threadLocal.set(apiLog)

        log.info(String.format("START TIME_PATTERN: %d", startTime.toEpochMilli()))
        log.info("IP: ${apiLog.ip}")
        log.info("URL: ${apiLog.url}, METHOD: ${apiLog.method}")
        log.info("CLASS METHOD: ${apiLog.classMethod}")
        log.info("ARGUMENTS: " + Arrays.toString(joinPoint.args))

        QueryCountHolder.clear()
    }

    @AfterReturning(returning = "ret", pointcut = "cn.staynoob.postcode.aop.ApiLogAspect.isController()")
    fun doAfterReturning(ret: Any?) {
        if (log.isDebugEnabled) log.debug("RESPONSE : $ret")
        end(true, ret?.let { it::class.qualifiedName })
    }

    @AfterThrowing(throwing = "e", pointcut = "cn.staynoob.postcode.aop.ApiLogAspect.isController()")
    fun doAfterThrowing(e: Throwable?) {
        if (log.isDebugEnabled) log.debug("EXCEPTION : $e")
        end(false, e?.let { it::class.qualifiedName })
    }

    private fun end(
            success: Boolean,
            resultClass: String?
    ) {
        val apiLog = threadLocal.get()
        with(apiLog) {
            setEndTime(Instant.now())
            this.success = success
            this.resultClass = resultClass
        }

        val queryCount = QueryCountHolder.getGrandTotal()
        apiLog.sqlStatementCount = queryCount.total.toInt()

        // log sql statement count
        if (queryCount.total <= 10) {
            if (log.isDebugEnabled) {
                log.debug("SQL statement: select count: ${queryCount.select}")
                log.debug("SQL statement: insert count: ${queryCount.insert}")
                log.debug("SQL statement: update count: ${queryCount.update}")
                log.debug("SQL statement: delete count: ${queryCount.delete}")
                log.debug("SQL statement: total count: ${queryCount.total}")
            }
        } else {
            if (log.isWarnEnabled) {
                log.warn("SQL statement: select count: ${queryCount.select}")
                log.warn("SQL statement: insert count: ${queryCount.insert}")
                log.warn("SQL statement: update count: ${queryCount.update}")
                log.warn("SQL statement: delete count: ${queryCount.delete}")
                log.warn("SQL statement: total count: ${queryCount.total}")
            }
        }
        QueryCountHolder.clear()

        log.info(String.format("END TIME_PATTERN: %d", apiLog.getEndTime()!!.toEpochMilli()))
        log.info(String.format("SPEND TIME_PATTERN: %dms", apiLog.duration))
        log.info(apiLog.toString())
    }
}