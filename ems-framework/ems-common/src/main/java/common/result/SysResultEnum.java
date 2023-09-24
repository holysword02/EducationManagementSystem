package common.result;

/**
 * @Author: 作者：陈子枢
 * @Copyright 版权：v1.0
 * @CreateTime: 16:34
 * @Description: 描述：常用结果的枚举
 */

public enum SysResultEnum implements ISysResult {
    SUCCESS(6001, "接口调用成功"),
    VALIDATE_FAILED(6002, "参数校验失败"),
    COMMON_FAILED(6003, "接口调用失败"),
    FORBIDDEN(6004, "没有权限访问资源");

    //扩充用户自定义的信息提示
    SysResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private Integer code;
    private String msg;

    @Override
    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @Override
    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
