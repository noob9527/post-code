package cn.staynoob.postcode.support.base

import cn.staynoob.postcode.PostcodeApplication
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.ComponentScan
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit.jupiter.SpringExtension

//@SpringBootTest
@DataJpaTest
@ComponentScan(basePackageClasses = [PostcodeApplication::class])
@ExtendWith(SpringExtension::class)
@ActiveProfiles("test")
abstract class TestBase


