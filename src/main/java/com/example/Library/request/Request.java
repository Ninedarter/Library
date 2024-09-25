package com.example.Library.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

@AllArgsConstructor
public class Request {

    @Getter
    @NonNull
    private String filter;
}
