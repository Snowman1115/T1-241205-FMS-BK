package com.fms.fmsback.common.result;

import com.fms.fmsback.common.constants.ResultConstants;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result {

    /**
     * Result Code
     */
    private Integer code;

    /**
     * Result Message
     */
    private String message;

    /**
     * Result Object
     */
    private Object data;

    /**
     * Return Success With Empty Message & Data
     * @return Result
     */
    public static Result success() {
        return new Result(ResultConstants.OK, "", null);
    }

    /**
     * Return Success With Data, Empty Message
     * @return Result
     */
    public static Result success(Object data) {
        return new Result(ResultConstants.OK, "", data);
    }

    /**
     * Return Success With Data & Message
     * @return Result
     */
    public static Result success(Object data, String message) {
        return new Result(ResultConstants.OK, message, data);
    }

    /**
     * Return Error With Default "System Error" Message
     * @return Result
     */
    public static Result error() {
        return new Result(ResultConstants.INTERNAL_SERVER_ERROR, "System Error", null);
    }

    /**
     * Return Error With Custom Error Code & Message
     * @param code
     * @param message
     * @return Result
     */
    public static Result error(Integer code, String message) {
        return new Result(code, message, null);
    }


}
