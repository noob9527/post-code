package cn.staynoob.postcode.sample6

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.MapperFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import org.junit.jupiter.api.Test

class SuperUserTest {
    val objectMapper = ObjectMapper()
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
            .disable(SerializationFeature.FAIL_ON_EMPTY_BEANS)
            .disable(MapperFeature.DEFAULT_VIEW_INCLUSION)
            .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
            .registerKotlinModule()

    val debugMapper = objectMapper.writerWithDefaultPrettyPrinter()

    @Test
    fun test100() {
        val user = User("foo")
        val superUser = SuperUser(user)

        val res = debugMapper.writeValueAsString(superUser)
        println(res)
    }
}