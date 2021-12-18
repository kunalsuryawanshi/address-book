package com.bridgelabz.addressbook.advice;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Purpose: To Define AOP
 *
 * @author : Kunal Suryawanshi
 * @since : 13-12-2021
 */
@Component
@Aspect
public class LoggingAdvice {
    Logger logger = LoggerFactory.getLogger(LoggingAdvice.class);

    @Pointcut(value = "execution(* com.bridgelabz.employeepayrollapp.*.*.*(..) )")
    public void pointcut() {
    }

    /**
     * Purpose : Defining log message with class, method names & parameters used in method.
     *
     * @param proceedingJoinPoint : When invoked, the code execution jumps to the next advice or to the target method.
     * @return objectAfterProceeding : It's the response of that method.
     * @throws Throwable :  constructing JSON parser ( JsonParser ) and generator ( JsonGenerator ) instances and also
     *                   exception for proceed.
     */
    @Around("pointcut()")
    public Object applicationLogger(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        ObjectMapper objectMapper = new ObjectMapper();
        String methodName = proceedingJoinPoint.getSignature().getName();
        String className = proceedingJoinPoint.getTarget().getClass().toString();
        Object[] array = proceedingJoinPoint.getArgs();
        logger.info("method invoked " + className + " : " + methodName + "()" + "arguments : "
                + objectMapper.writeValueAsString(array));
        Object object = proceedingJoinPoint.proceed();
        logger.info(className + " : " + methodName + "()" + "Response : "
                + objectMapper.writeValueAsString(array));
        return object;
    }
}
