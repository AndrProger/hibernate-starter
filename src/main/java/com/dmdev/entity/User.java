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
import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="users",schema = "public")
@TypeDef(name="dmdev",typeClass=JsonBinaryType.class)
@Access(AccessType.FIELD) //def
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@AttributeOverride(name="birthDate", column = @Column(name="birth_date"))
    //private PersonalInfo personalInfo;
    private Long id;

    @Column(unique = true)
    private String username;
    @Type(type="dmdev")
    private String info;
    @Enumerated(EnumType.STRING)
    private Role role;
    @ManyToOne( fetch = FetchType.EAGER)
    @JoinColumn(name="company_id")
    private Company company;
}


