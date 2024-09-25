package com.example.Library.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Request {


    private String filter;
    private String authorFullName;
    private double minRating;
    private double maxRating;

}