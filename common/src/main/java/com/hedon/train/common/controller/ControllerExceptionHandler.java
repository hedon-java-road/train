package com.hedon.train.common.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hedon.train.common.exception.BusinessException;
import com.hedon.train.common.resp.CommonResp;

@ControllerAdvice
public class ControllerExceptionHandler {
    private static final Logger LOG = LoggerFactory.getLogger(ControllerExceptionHandler.class);

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public CommonResp<Void> exceptionHandler(Exception e) throws Exception {
        LOG.error("系统异常：", e);
        return CommonResp.error("系统出现异常，请联系管理员");
    }

    @ExceptionHandler(value = BusinessException.class)
    @ResponseBody
    public CommonResp<Void> exceptionHandler(BusinessException e) throws Exception {
        LOG.error("业务异常：{}", e.getE().getDesc());
        return CommonResp.error(e.getE().getDesc());
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseBody
    public CommonResp<Void> exceptionHandler(MethodArgumentNotValidException e) throws Exception {
        List<ObjectError> errors = e.getBindingResult().getAllErrors();
        return CommonResp.error(errors.get(0).getDefaultMessage());
    }
}
