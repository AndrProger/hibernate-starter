package com.dmdev.entity;

import lombok.*;

import javax.persistence.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"street","language"})
@ToString(exclude = "user")
@Builder
@Entity
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "user_id")
     private User user;
     private String street;
     private String language;
     public void setUser(User user) {
         user.setProfile(this);
         this.user = user;
     }
}
