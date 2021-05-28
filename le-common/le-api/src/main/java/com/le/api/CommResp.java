package com.le.api;

import com.le.enums.RespStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommResp<T> {
    private String code;
    private String message;
    private T data;

    public static CommResp ok(RespStatus status, Object data) {
        return new CommResp(status.code(), status.message(), data);
    }

    public static CommResp error(RespStatus status) {
        return new CommResp(status.code(), status.message(), null);
    }

    public static CommResp ok(String code, String message, Object data) {
        return new CommResp(code, message, data);
    }

    public static CommResp error(String code, String message) {
        return new CommResp(code, message, null);
    }


}
