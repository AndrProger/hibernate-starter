package com.dmdev.entity;

import lombok.*;

import javax.persistence.*;

import java.util.*;

@Data
@EqualsAndHashCode(exclude = "users")
@ToString(exclude = "users")

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
    @OneToMany(mappedBy = "company" ,cascade =  CascadeType.ALL,fetch = FetchType.LAZY, orphanRemoval = true)
    @MapKey(name="username")
    private Map<String,User> users=new HashMap();
@ElementCollection
@CollectionTable(name="company_locale")
    private List<LocaleInfo> locales;
    public void addUser(User user){
        users.put(user.getUsername(),user);
        user.setCompany(this);
    }
}
