package com.nio.swc.utils.aspects;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nio.swc.beans.ApiResponse;
import com.nio.swc.utils.ApiException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Arrays;

import com.nio.swc.beans.ApiErrorResponse;

@Aspect
@Order(0)
@Component
public class ApiAspect {
    private static final Logger logger = LoggerFactory.getLogger(ApiAspect.class);
    private static final ObjectMapper MAPPER = new ObjectMapper();



    @Pointcut("@annotation(com.nio.swc.utils.aspects.ApiRequest)")
    public void apiReqAspect() {
    }

    @Around("apiReqAspect()")
    public Object doAround(ProceedingJoinPoint pjp) {
        long startTime = System.currentTimeMillis();
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert attributes != null;
        HttpServletRequest request = attributes.getRequest();
        String uri = request.getRequestURI();
        HttpSession session = request.getSession();

        logger.info("[API Request] IP: {} METHOD: {} URI: {} ARGS: {}" + request.getRemoteAddr(), request.getMethod(), uri, Arrays.toString(pjp.getArgs()));

        ApiResponse response;
        try {
            Object result = pjp.proceed();
            String resultStr = MAPPER.writeValueAsString(result);
            long spendTime = System.currentTimeMillis() - startTime;
            logger.info("[API Response] SPEND TIME: " + spendTime + "ms DATA: " + resultStr);
            response = new ApiResponse(result);
        } catch (ApiException ex) {
            logger.info("[API Response] API ERROR: " + ex.getMessage());
            response = new ApiErrorResponse(ex.getHttpStatus(), ex.getMessage());
        } catch (Throwable ex) {
            logger.info("[API Response] Other ERROR: " + ex.getClass().getName() + " " + ex.getMessage());
            ex.printStackTrace();
            response = new ApiErrorResponse(ex.getMessage());
        }
        return response;
    }
}
