package studio.attect.demo.test

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile
import studio.attect.demo.annotation.MultiRequestJson
import studio.attect.demo.dataModel.UserLogin
import studio.attect.demo.resolver.MultiRequestParamJsonResolver

@RestController
class UserController {

    private val logger: Logger = LoggerFactory.getLogger(this::class.java)

    @RequestMapping("/login")
    fun login(@MultiRequestJson userLogin:UserLogin,file:MultipartFile):Any?{
        logger.info(userLogin.toString())
        logger.info("receiveFile:${file.originalFilename}")
        return null
    }
}