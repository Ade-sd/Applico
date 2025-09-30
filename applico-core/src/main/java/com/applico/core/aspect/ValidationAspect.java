package com.applico.core.aspect;

import com.applico.core.dto.IValidation;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
class ValidationAspect {
    @Pointcut("within(@org.springframework.stereotype.Controller *) || within(@org.springframework.web.bind.annotation.RestController *)")
    public void controllers() {
    }

    @Before("controllers()")
    public void iValidationAspect(JoinPoint joinPoint) throws Throwable {
        if (joinPoint.getArgs() == null) {
            return;
        }
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            if (arg == null) {
                continue;
            }
            if (arg instanceof IValidation) {
                ((IValidation) arg).validate();
            }
            Class<?>[] declaredClasses = arg.getClass().getDeclaredClasses();
            for (Class<?> innerClass : declaredClasses) {
                if (IValidation.class.isAssignableFrom(innerClass)) {
                    IValidation innerInstance = (IValidation) innerClass.getDeclaredConstructor().newInstance();
                    innerInstance.validate();
                }
            }
        }
    }
}