package com.example.company.Controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Result {
    private String text;
    private Boolean active;
    private Object object;

    public Result(String text, boolean active) {
        this.text = text;
        this.active = active;
    }

    public Result(String text, boolean active, Object object) {
        this.text = text;
        this.active = active;
        this.object = object;
    }
}
