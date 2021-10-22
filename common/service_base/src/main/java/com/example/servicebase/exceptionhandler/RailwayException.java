package com.example.servicebase.exceptionhandler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RailwayException  extends RuntimeException {
    private Integer code;
    private String msg;

    public RailwayException(String msg){
        this.msg = msg;
    }
}
