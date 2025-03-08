package hello.hello_spring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TraceAop {

    @Around("execution(* hello.hello_spring..*(..))") // AOP를 사용할 위치 설정
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis(); // 시작 시간 측정
        System.out.println("START: " + joinPoint.toString());
        try {
            return joinPoint.proceed(); // 다음 메서드로 진행
        } finally {
            long finish = System.currentTimeMillis(); // 종료 시간 측정
            long timeMs = finish - start; // 소요 시간 측정

            System.out.println("END: " + joinPoint.toString() + " " + timeMs + "ms");
        }
    }
}