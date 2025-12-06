package com.quiz.app.QuizApp.model;

import lombok.RequiredArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;

@RequiredArgsConstructor
public class Response {
    @NotNull
    private int id;
    @NotNull
    private String response;

    public Response(int id, String response) {
        this.id = id;
        this.response = response;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
