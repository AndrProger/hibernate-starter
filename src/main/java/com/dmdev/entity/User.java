package com.dmdev.entity;


import com.dmdev.convertor.BirthdayConverter;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.*;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString(exclude = {"company", "profile","userChats"})
@EqualsAndHashCode(of={"username","info","role"})
@Table(name = "users", schema = "public")
@TypeDef(name = "dmdev", typeClass = JsonBinaryType.class)
@Access(AccessType.FIELD) //def
@Inheritance(strategy = InheritanceType.JOINED)

public abstract class User implements BaseEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    //@AttributeOverride(name="birthDate", column = @Column(name="birth_date"))
    private Long id;
    private PersonalInfo personalInfo;
    @Column(unique = true)
    private String username;
    @Type(type = "dmdev")
    private String info;
    @Enumerated(EnumType.STRING)
    private Role role;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;
    @OneToOne(
            mappedBy = "user",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private Profile profile;

    @OneToMany(mappedBy = "user")
    private List<UserChat> userChats =new ArrayList<>();




}


