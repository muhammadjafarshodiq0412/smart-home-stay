package com.smarthomestay.model.Response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GeneralResponse<E> {
    private int code;
    private String status;
    private String message;
    private E data;

    public GeneralResponse(int code, String status, String message, E data) {
        this.code = code;
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public GeneralResponse(int code) {
        this.code = code;
    }
}
