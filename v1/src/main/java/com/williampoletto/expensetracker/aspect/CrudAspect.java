package com.williampoletto.expensetracker.aspect;

import java.util.Optional;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class CrudAspect {

	@AfterReturning(pointcut="execution(* com.williampoletto.expensetracker.repository.*.*(..))",
					returning="result")
	public void beforeCrud(JoinPoint joinPoint, Object result) {
		
		String methodSignature =  joinPoint.getSignature().toShortString();
		
		Optional<String> methodArgument =
				Optional.of(
						Optional.ofNullable(joinPoint)
							.filter(list -> joinPoint.getArgs().length > 0)
								.map(arg -> joinPoint.getArgs()[0])
								.map(String::valueOf)
							.orElse("N/A"));
		
		Optional<String> methodReturn =
				Optional.of(
						Optional.ofNullable(result)
								.map(String::valueOf)
								.orElse("N/A"));
						
		
		log.info(">>> Executing CRUD method in " + methodSignature);
		log.info(">>> Argument - " + "'" + methodArgument.get() + "'");
		log.info(">>> Return - " + methodReturn.get());
		
	}
	
}
