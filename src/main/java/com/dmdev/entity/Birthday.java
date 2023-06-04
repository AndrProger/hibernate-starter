package com.dmdev.entity;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;


public record Birthday(LocalDate bithday)  {
    public long getAge() {
        return ChronoUnit.YEARS.between(bithday, LocalDate.now());
    }
}
