package com.example.Library.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

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

}
