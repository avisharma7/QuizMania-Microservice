package com.example.quiz_service.Model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Response {
    private Integer id;
    private String response;

    public String getResponse() {
        return response;
    }

    public Integer getId() {
        return id;
    }
}
