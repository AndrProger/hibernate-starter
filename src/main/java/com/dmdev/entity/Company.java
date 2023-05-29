package com.dmdev.entity;

import lombok.*;

import javax.persistence.*;

import java.util.HashSet;
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
    private Long id;

    private String name;
    @Builder.Default
    @OneToMany(mappedBy = "company" ,cascade =  CascadeType.ALL,fetch = FetchType.LAZY)
    private Set<User> users=new HashSet<>();
    public void addUser(User user){
        users.add(user);
        user.setCompany(this);
    }
}
