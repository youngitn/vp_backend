package com.vp.tw.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * point cut AOP綁定 controller.
 * log顯示  controllerName-methodName
 * @author USER
 *
 */
@Aspect
@Component
public class LogAspect {

	@Pointcut("execution(* com.vp.tw.controller..*(..))")
	public void pointcut() {
	}

	@Before("pointcut()")
	public void before(JoinPoint joinPoint) {

		Logger logger = LoggerFactory.getLogger(joinPoint.getTarget().getClass().getName()+"-"+joinPoint.getSignature().getName());
		logger.info("start");
	}

	@After("pointcut()")
	public void after(JoinPoint joinPoint) {
		Logger logger = LoggerFactory.getLogger(joinPoint.getTarget().getClass().getName()+"-"+joinPoint.getSignature().getName());
		logger.info("end");

	}

}