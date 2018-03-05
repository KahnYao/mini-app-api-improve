package com.dpt.common;

public class ApiResult extends Result {

    /**
     *
     * @param code
     * @param message
     */
    public ApiResult(int code, String message) {
        super(code, message, new Object());
    }

    /**
     *
     * @param code
     * @param message
     * @param data
     */
    public ApiResult(int code, String message, Object data) {
        super(code, message, data);
    }

    /**
     *
     * @param resultConstant
     * @param data
     */
    public ApiResult(ResultConstant resultConstant, Object data) {
        super(resultConstant.getCode(), resultConstant.getMessage(), data);
    }

}
