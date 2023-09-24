package common.result;

/**
 * @Author: 作者：陈子枢
 * @Copyright 版权：v1.0
 * @CreateTime: 16:34
 * @Description: 描述：以map获取数据的方式，根据枚举类code获取msg
 */

public interface ISysResult {
    Integer getCode();
    String getMsg();
}
