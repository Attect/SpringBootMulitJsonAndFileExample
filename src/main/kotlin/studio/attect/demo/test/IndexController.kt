package studio.attect.demo.test

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import studio.attect.demo.dataModel.UserLogin

@Controller
class IndexController {

    @RequestMapping("index")
    fun index():String{
        return "/index.html"
    }

}