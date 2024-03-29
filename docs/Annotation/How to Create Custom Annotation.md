# Creating Custom Annotations

1. Enable `@EnableAspectJAutoProxy` by adding this annotation to the project.

2. Create an interface `MeasureExecutionTime` that you want to use it as annotation

```java
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface MeasureExecutionTime {
	 String value() default "";
}
```

3. Create aspect 

```java
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
```

4. Use the annotation

```java
@MeasureExecutionTime
@GetMapping("")
public String annotaion() {
	return annotationService.get();
}
```

