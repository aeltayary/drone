/**
 * 
 */
package com.musala.aop;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

/**
 * @author aeltayary
 *
 */
@Component
@Aspect
public class ApplicationLogger {

	private static final Logger log= LoggerFactory.getLogger(ApplicationLogger.class);
	
	
	
	
	/**
	 * log all exception in service layer
	 * 
	 * @param ex
	 */

	@AfterThrowing(value = "execution(* com.musala.service..*(..))", throwing = "ex")
	public void logAfterThrowing(JoinPoint point, Exception ex) {
		log.info("{} has been executed with exception {},  ", point.getSignature().getName(), ex.getMessage());
	}

	/**
	 * logging aspect for service layer log at start of the method log at the end of
	 * method log the execution time
	 * 
	 * @param point
	 * @throws Throwable
	 */


	@Around("execution(* com.musala.service..*(..))")
	public Object  logAround(ProceedingJoinPoint point) throws Throwable {
		log.info("{} has been started,  ", point.getSignature().getName());
		Object[] args = point.getArgs();
		Arrays.asList(args).forEach(arg -> log.info("arg:{}", arg));
		final StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		Object ret = point.proceed();
		stopWatch.stop();
		log.info("{} has been executed , time taken is {} ms", point.getSignature().getName(),
		stopWatch.getTotalTimeMillis());
		return ret;
	}

	
}
