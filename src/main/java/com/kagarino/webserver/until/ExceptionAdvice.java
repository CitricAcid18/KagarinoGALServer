package com.kagarino.webserver.until;

import com.kagarino.webserver.entity.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.UncategorizedSQLException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.nio.file.AccessDeniedException;


/**
 * @Author: zwj
 * @Date: 2024/6/7 10:16
 * @Version: v1.0.0
 * @Description: TODO 全局异常拦截类
 **/
@RestControllerAdvice
@Slf4j
public class ExceptionAdvice {

    /**
     * 权限校验异常，无权访问文件夹时触发
     */
    @ExceptionHandler(AccessDeniedException.class)
    public Result<String> handleAccessDeniedException(AccessDeniedException e,
                                              HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        log.error("请求地址{},权限校验失败{}", requestURI, e.getMessage());
        Result<String> res = new Result<>("没有权限，请联系管理员授权");
        return res.error(ResultEnum.FORBIDDEN.code, ResultEnum.FORBIDDEN.msg);
    }

    /**
     * 请求方式不支持，请求路径正确但是请求方式不对时触发
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public Result<String> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException e,
                                                          HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        log.error("请求地址{},不支持{}请求", requestURI, e.getMethod());
        Result<String> res = new Result<>(e.getMessage());
        return res.error(ResultEnum.UNSUPPORTED_TYPE.code, ResultEnum.UNSUPPORTED_TYPE.msg);
    }

    /**
     * 参数验证失败异常，使用@Valid注解验证参数不正确时触发
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result<String> handleMethodArgumentNotValidException(MethodArgumentNotValidException e,
                                                            HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        String message = e.getBindingResult().getFieldError().getDefaultMessage();
        log.error("请求地址{},参数验证失败{}", requestURI, e.getObjectName(),e);
        Result<String> res = new Result<>(e.getMessage());
        return res.error(ResultEnum.BAD_REQUEST.code, ResultEnum.BAD_REQUEST.msg);
    }

    /**
     * 拦截错误SQL异常，sql语句或参数类型、个数不匹配时触发
     */
    @ExceptionHandler(BadSqlGrammarException.class)
    public Result<String> handleBadSqlGrammarException(BadSqlGrammarException e,
                                                   HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        log.error("请求地址'{}',发生数据库异常.", requestURI, e);
        Result<String> res = new Result<>("数据库异常！");
        return res.error(ResultEnum.BAD_SQL.code, ResultEnum.BAD_SQL.msg);
    }

    /**
     * 可以拦截表示违反数据库的完整性约束导致的异常。
     */
    @ExceptionHandler(DataIntegrityViolationException.class)
    public Result<String> handleDataIntegrityViolationException(DataIntegrityViolationException e,
                                                            HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        log.error("请求地址'{}',发生数据库异常.", requestURI, e);
        Result<String> res = new Result<>("数据库约束异常！");
        return res.error(ResultEnum.BAD_SQL.code, ResultEnum.BAD_SQL.msg);
    }


    /**
     * 可以拦截违反数据库的非完整性约束导致的异常，可能也会拦截一些也包括 SQL 语句错误、连接问题、权限问题等各种数据库异常。
     */
    @ExceptionHandler(UncategorizedSQLException.class)
    public Result<String> handleUncategorizedSqlException(UncategorizedSQLException e,
                                                      HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        log.error("请求地址'{}',发生数据库异常.", requestURI, e);
        Result<String> res = new Result<>("数据库连接/权限异常！");
        return res.error(ResultEnum.BAD_SQL.code, ResultEnum.BAD_SQL.msg);
    }

    /**
     * 拦截未知的运行时异常
     */
    @ExceptionHandler(RuntimeException.class)
    public Result<String> handleRuntimeException(RuntimeException e,
                                             HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        log.error("请求地址{},发生未知运行异常", requestURI, e);
        Result<String> res = new Result<>(e.getMessage());
        return res.error("520","未知异常");
    }

    /**
     * 业务自定义异常
     */
    @ExceptionHandler(ServiceException.class)
    public Result<String> handleServiceException(ServiceException e,
                                             HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        String code = e.getCode();
        log.error("请求地址{},发生业务自定义异常{}",requestURI,e.getMessage(), e);
        Result<String> res = new Result<>(e.getMessage());
        return code != null ? res.error(code,"业务异常") : res.error("520","未知异常");
    }

    /**
     * 全局异常
     */
    @ExceptionHandler(Exception.class)
    public Result<String> handleException(Exception e,
                                      HttpServletRequest request){
        String requestURI = request.getRequestURI();
        log.error("请求地址{},发生系统异常",requestURI,e);
        Result<String> res = new Result<>(e.getMessage());
        return res.error("520","未知异常");
    }

}

