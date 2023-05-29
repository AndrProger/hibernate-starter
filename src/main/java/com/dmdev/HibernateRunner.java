package com.dmdev;


import com.dmdev.convertor.BirthdayConverter;
import com.dmdev.entity.*;
import com.dmdev.until.HibernateUtil;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.model.naming.CamelCaseToUnderscoresNamingStrategy;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.time.LocalDate;

@Slf4j
public class HibernateRunner {

    //private static final Logger log= LoggerFactory.getLogger(HibernateRunner.class);
    public static void main(String[] args) throws SQLException {


        try (SessionFactory sessionFactory = HibernateUtil.buildSessionFactory()) {
            Company company = Company.builder()
                    .name("Amazon")
                    .build();
            User user = User.builder()
                    .username("Ivan@gmail.com")
                    .company(company)
                    .build();

            log.info("User entity is in transient state, object: {}", user);
            Session session1 = sessionFactory.openSession();
            try (session1) {
                Transaction transaction = session1.beginTransaction();


                session1.save(user);


                session1.getTransaction().commit();
            }

        }


    }
}
