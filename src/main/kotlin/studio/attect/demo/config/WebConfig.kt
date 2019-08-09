package studio.attect.demo.config

import org.springframework.context.annotation.Configuration
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import studio.attect.demo.resolver.MultiRequestParamJsonResolver

@Configuration
class WebConfig: WebMvcConfigurer {
    override fun addArgumentResolvers(resolvers: MutableList<HandlerMethodArgumentResolver>) {
        resolvers.add(MultiRequestParamJsonResolver())
    }
}