package common.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: 作者：陈子枢
 * @Copyright 版权：v1.0
 * @CreateTime: 16:34
 * @Description: 描述：统一返回数据结构
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysResult<T> {
    private Integer code;   //状态码
    private String msg;     //提示信息
    private T data;         //返回数据，T泛型支持各种数据

    //返回标准成功消息
    public static <T> SysResult<T> success(T data){
        return new SysResult<>(SysResultEnum.SUCCESS.getCode(), SysResultEnum.SUCCESS.getMsg(), data);
    }

    //返回自定义成功消息
    public static <T> SysResult<T> success(String msg, T data){
        return new SysResult<>(SysResultEnum.SUCCESS.getCode(), msg, data);
    }

    //返回标准失败消息
    public static <T> SysResult<T> fail(T data){
        return new SysResult<>(SysResultEnum.COMMON_FAILED.getCode(), SysResultEnum.COMMON_FAILED.getMsg(), data);
    }

    //返回自定义失败消息
    public static <T> SysResult<T> fail(String msg, T data){
        return new SysResult<>(SysResultEnum.COMMON_FAILED.getCode(), msg, data);
    }

    //完全自定义代码和信息
    public static <T> SysResult<T> intance(Integer code, String msg, T data){
        SysResult<T> result = new SysResult<>();
        result.setCode(code);
        result.setMsg(msg);
        result.setData(data);
        return result;
    }
}
