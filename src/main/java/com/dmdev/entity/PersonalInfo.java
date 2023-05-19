package com.dmdev.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Embeddable
public class PersonalInfo {

    private String firstName;
    private String lastName;
    //@Convert(converter = BirthdayConverter.class)
  //  @Column(name = "birth_date")
    private Birthday birthDate;
}
