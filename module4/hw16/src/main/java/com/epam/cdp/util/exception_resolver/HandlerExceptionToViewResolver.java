package com.epam.cdp.util.exception_resolver;

import org.springframework.http.HttpStatus;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HandlerExceptionToViewResolver implements HandlerExceptionResolver {

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception exception) {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            ErrorView errorView = handlerMethod.getMethodAnnotation(ErrorView.class);
            if (errorView != null) {
                ModelAndView model = new ModelAndView(errorView.value());
                model.addObject("requestUri", request.getRequestURI());
                model.addObject("exception", exception);

                HttpStatus status = errorView.status();
                model.addObject("statusValue", status.value());
                model.addObject("statusText", status.getReasonPhrase());

                response.setStatus(status.value());

                return model;
            }
        }
        return null;
    }

}