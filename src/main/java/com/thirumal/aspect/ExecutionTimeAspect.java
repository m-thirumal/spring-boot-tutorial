/**
 * 
 */
package com.thirumal.aspect;

import java.time.Duration;
import java.time.OffsetDateTime;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.thirumal.annotation.MeasureExecutionTime;

/**
 * @author Thirumal
 */
@Aspect
@Component
public class ExecutionTimeAspect {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Around("@annotation(measureExecutionTime)")
    public Object measureTime(ProceedingJoinPoint joinPoint, MeasureExecutionTime measureExecutionTime) throws Throwable {
		OffsetDateTime startTime = OffsetDateTime.now();
        try {
            return joinPoint.proceed();
        } finally {
            OffsetDateTime endTime = OffsetDateTime.now();
            Duration duration = Duration.between(startTime, endTime);
            logger.info("Method {} started at {} executed in {} nano sec until {}", joinPoint.getSignature().toShortString(), startTime, duration.getNano(), endTime);
        }
    }
	
}
