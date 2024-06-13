package com.kagarino.webserver.entity;

import com.kagarino.webserver.until.ResultEnum;
import lombok.Data;

/**
 * @Author: zwj
 * @Date: 2024/6/7 9:39
 * @Version: v1.0.0
 * @Description: TODO 统一返回结果类
 **/
@Data
public class Result<T> {

    //返回信息码
    private String code;
    //返回信息
    private String msg;
    //返回数据
    T data;

    public Result(){}

    public Result(T data){
        this.data=data;
    }


    //成功，只返回成功码和信息
    public Result<T> success(){
        Result<T> result=new Result<>();
        result.setCode(ResultEnum.SUCCESS.code);
        result.setMsg(ResultEnum.SUCCESS.msg);
        return result;
    }

    //成功，返回成功码、信息和数据
    public Result<T> success(String code,String msg){
        this.setCode(code);
        this.setMsg(msg);
        return this;
    }

    //失败，返回自己定义的信息码和信息
    public Result<T> error(){
        Result<T> result=new Result<>();
        result.setCode(ResultEnum.ERROR.code);
        result.setMsg(ResultEnum.ERROR.code);
        return result;
    }

    //失败，返回controller层传过来信息码和信息
    public Result<T> error(String code,String msg){
        this.setCode(code);
        this.setMsg(msg);
        return this;
    }


    public void setCode(String code){
        this.code=code;
    }

    public void setMsg(String msg){
        this.msg=msg;
    }

    public void setData(T data){
        this.data=data;
    }

    @Override
    public String toString() {
        return "Result{" +
                "code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }

}


