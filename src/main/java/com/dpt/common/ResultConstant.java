package com.dpt.common;

public enum ResultConstant {

    SUCCESS(0, "操作成功。"),

    ERROR(1, "系统错误，请联系管理员。"),

    NO_LOGINED_IN(1001, "未登录。"),

    PERMISSION_DENIED(1002, "无修改权限。"),

    FILE_TYPE_ERROR(1003, "文件类型不支持。"),

    INVALID_LENGTH(1004, "无效长度。"),

    INVALID_PARAMETER(1005, "无效参数。"),

    ERROR_CREATEUSER(1006, "用户创建失败。"),

    ERROR_CREATESHOP(1008, "请输入正确的城市。"),

    NO_LIST(1007,"暂无数据");

    public int code;

    public String message;

    /**
     *
     * @param code
     * @param message
     */
    ResultConstant(int code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     *
     * @return
     */
    public int getCode() {
        return code;
    }

    /**
     *
     * @param code
     */
    public void setCode(int code) {
        this.code = code;
    }

    /**
     *
     * @return
     */
    public String getMessage() {
        return message;
    }

    /**
     *
     * @param message
     */
    public void setMessage(String message) {
        this.message = message;
    }
}
