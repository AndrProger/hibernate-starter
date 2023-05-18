package com.dmdev;


import com.dmdev.convertor.BirthdayConverter;
import com.dmdev.entity.Birthday;
import com.dmdev.entity.Role;
import com.dmdev.entity.User;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.model.naming.CamelCaseToUnderscoresNamingStrategy;
import org.hibernate.cfg.Configuration;

import java.sql.SQLException;
import java.time.LocalDate;

public class HibernateRunner {
    public static void main(String[] args) throws SQLException {
        Configuration configuration = new Configuration();
//        configuration.setPhysicalNamingStrategy(new CamelCaseToUnderscoresNamingStrategy());
//        configuration.addAnnotatedClass(User.class);
        configuration.addAttributeConverter(new BirthdayConverter());
        configuration.registerTypeOverride(new JsonBinaryType());
        configuration.configure();

        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
             Session session=sessionFactory.openSession()
        ){
            session.beginTransaction();
//            User user = User.builder()
//                    .username("ivan32@gmail.com")
//                    .firstName("Ivan")
//                    .lastName("Ivanov")
//                    .birthDate(new Birthday(LocalDate.of(2000, 1,19)))
//                    .info("""
//                            {
//                                "name":"Ivan",
//                                "id":25
//                            }
//                            """)
//                    .role(Role.ADMIN)
//                    .build();
//
//            session.delete(user);


            User user = session.get(User.class,"ivan@gmail.com");
            System.out.println(user);
            session.getTransaction().commit();

        }

    }
}
