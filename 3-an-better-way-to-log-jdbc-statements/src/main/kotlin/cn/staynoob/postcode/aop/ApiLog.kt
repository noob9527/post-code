package cn.staynoob.postcode.aop

import java.time.Duration
import java.time.Instant

data class ApiLog(
        val ip: String,
        val url: String,
        val method: String,
        val classMethod: String,
        val startTime: Instant,
        private var endTime: Instant? = null,
        var duration: Long? = null,
        var sqlStatementCount: Int? = null,
        var success: Boolean? = null,
        var resultClass: String? = null
) {
    fun getEndTime(): Instant? {
        return endTime
    }

    fun setEndTime(value: Instant) {
        endTime = value
        duration = Duration.between(startTime, value).toMillis()
    }
}