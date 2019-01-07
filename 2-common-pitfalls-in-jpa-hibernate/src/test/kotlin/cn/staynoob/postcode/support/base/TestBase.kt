package cn.staynoob.postcode.support.base

import cn.staynoob.postcode.PostcodeApplication
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.context.annotation.ComponentScan
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
@ActiveProfiles("test")
@ComponentScan(basePackageClasses = [PostcodeApplication::class])
abstract class TestBase
