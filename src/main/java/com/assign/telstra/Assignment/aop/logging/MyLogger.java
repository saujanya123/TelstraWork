package com.assign.telstra.Assignment.aop.logging;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

@Aspect
@Component
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class MyLogger {

	 private static final Logger logger = LogManager.getLogger(MyLogger.class);


	@After("execution(* com.assign.telstra.Assignment..*.*(..))")
	public void logMethodAccessAfter(JoinPoint joinPoint) {
		logger.info("***** Completed: " + joinPoint.getSignature().getName() + " *****");
	}

	@Before("execution(* com.assign.telstra.Assignment..*.*(..))")
	public void logMethodAccessBefore(JoinPoint joinPoint) {
		logger.info("***** Starting: " + joinPoint.getSignature().getName() + " *****");
	}
}