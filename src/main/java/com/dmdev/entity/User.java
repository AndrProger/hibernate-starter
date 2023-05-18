package com.dmdev.entity;


import com.dmdev.convertor.BirthdayConverter;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.time.LocalDate;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="users",schema = "public")
@TypeDef(name="dmdev",typeClass=JsonBinaryType.class)
public class User {
    @Id
    private String username;
    private String firstName;
    private String lastName;
    //@Convert(converter = BirthdayConverter.class)
    @Column(name = "birth_date")
    private Birthday birthDate;

    @Type(type="dmdev")
    private String info;
    @Enumerated(EnumType.STRING)
    private Role role;
}


