package com.dmdev.entity;

import lombok.*;

import javax.persistence.*;
import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of={"user","chat","createdAt","createdBy"})
@Entity
@Table(name = "users_chat")
public class UserChat extends AuditableEntity<Long>  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private User user;

    @ManyToOne
    @JoinColumn(name = "chat_id")
    private Chat chat;

    @Column(name = "created_at")
    private Instant  createdAt;

    @Column(name = "created_by")
    private  String createdBy;
    public void setUser(User user){
        this.user = user;
        this.user.getUserChats().add(this);
    }

    public void setChat(Chat chat){
        this.chat=chat;
        this.chat.getUserChats().add(this);
    }
}
