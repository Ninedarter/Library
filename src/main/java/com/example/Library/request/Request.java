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
    private String titleOfBookToRate;
    private double ratingValue;


    public Request(String filter) {
        this.filter = filter;
    }

    public Request(double minRating, double maxRating) {
        this.minRating = minRating;
        this.maxRating = maxRating;
    }

    public Request(String titleOfBookToRate, double ratingValue) {
        this.titleOfBookToRate = titleOfBookToRate;
        this.ratingValue = ratingValue;
    }
}
