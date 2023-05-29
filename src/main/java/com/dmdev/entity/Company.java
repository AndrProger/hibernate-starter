package com.dmdev.entity;

import lombok.*;

import javax.persistence.*;

import java.util.Set;

@Data
@ToString(exclude = "users")
@EqualsAndHashCode(exclude = "users")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    @OneToMany(mappedBy = "company")
    private Set<User> users;
}
