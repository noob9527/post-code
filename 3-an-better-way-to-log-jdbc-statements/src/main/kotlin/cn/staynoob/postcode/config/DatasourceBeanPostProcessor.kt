package cn.staynoob.postcode.config

import com.p6spy.engine.spy.P6DataSource
import net.ttddyy.dsproxy.listener.logging.SLF4JLogLevel
import net.ttddyy.dsproxy.support.ProxyDataSourceBuilder
import org.springframework.beans.factory.config.BeanPostProcessor
import org.springframework.context.annotation.Configuration
import java.util.concurrent.TimeUnit
import javax.sql.DataSource

@Configuration
class DatasourceBeanPostProcessor : BeanPostProcessor {
    override fun postProcessAfterInitialization(bean: Any, beanName: String): Any? {
        if (bean !is DataSource) return bean
        val res = ProxyDataSourceBuilder.create(bean)
                .name("postcode")
                .multiline()
                .countQuery()
                .logQueryBySlf4j(SLF4JLogLevel.DEBUG)
                .logSlowQueryBySlf4j(100, TimeUnit.MILLISECONDS)
                .build()
        return P6DataSource(res)
    }
}