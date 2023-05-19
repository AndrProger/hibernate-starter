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
    @GeneratedValue(generator = "user_gen", strategy=GenerationType.TABLE)
//    @SequenceGenerator(name ="user_gen", sequenceName = "user_id_seq", allocationSize = 1)
    @TableGenerator(name="user_gen",
            allocationSize = 1,
            table = "all_sequence",
            pkColumnName = "table_name",
            valueColumnName = "pk_value"
    )
    private  Long id;

    @Column(unique = true)
    private String username;
    @Embedded
    @AttributeOverride(name="birthDate", column = @Column(name="birth_date"))
    private PersonalInfo personalInfo;
    @Type(type="dmdev")
    private String info;
    @Enumerated(EnumType.STRING)
    private Role role;
}


