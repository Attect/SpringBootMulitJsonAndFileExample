package studio.attect.demo.resolver

import com.google.gson.Gson
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.core.MethodParameter
import org.springframework.web.bind.support.WebDataBinderFactory
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.method.support.ModelAndViewContainer
import studio.attect.demo.annotation.MultiRequestJson

//https://blog.csdn.net/w605283073/article/details/82119284
class MultiRequestParamJsonResolver:HandlerMethodArgumentResolver {
    private val logger: Logger = LoggerFactory.getLogger(MultiRequestParamJsonResolver::class.java)


    override fun supportsParameter(methodParameter: MethodParameter): Boolean {
        val annotation = methodParameter.getParameterAnnotation(MultiRequestJson::class.java) as MultiRequestJson

        logger.info("annotation value :" + annotation.value)
        logger.info("parameterTypeClass:${methodParameter.parameterType}")
        return !methodParameter.parameterType.isPrimitive
    }

    override fun resolveArgument(methodParameter: MethodParameter, modelAndViewContainer: ModelAndViewContainer?, nativeWebRequest: NativeWebRequest, webDataBinderFactory: WebDataBinderFactory?): Any? {

        val annotation = methodParameter.getParameterAnnotation(MultiRequestJson::class.java) as MultiRequestJson


        val parameterType = methodParameter.parameterType

        //参数名
        var key = annotation.value

        //没有则用形参名
        if(key.isEmpty()){
            methodParameter.parameterName?.let {
                key = it
            }
        }

        logger.info("from value:$key")
        val valueBody = nativeWebRequest.getParameterValues(key)
        if(valueBody == null){
            logger.warn("${key}没有接收到参数")
            if(parameterType.constructors.isNotEmpty() && parameterType.constructors[0].parameterCount == 0){
                return parameterType.constructors[0].newInstance()
            }else{
                logger.error("对应的Bean[${parameterType.name}]没有空参数的构造方法")
                return Any()
            }
        }

        var parameterStringValue = ""
        valueBody.forEach {
            logger.info("post value:$it")
            parameterStringValue = it
        }

        return gson.fromJson(parameterStringValue,parameterType)
    }

    companion object{
        @JvmStatic
        private val gson = Gson()
    }
}