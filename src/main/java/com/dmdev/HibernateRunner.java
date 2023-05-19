package com.dmdev;


import com.dmdev.convertor.BirthdayConverter;
import com.dmdev.entity.Birthday;
import com.dmdev.entity.Role;
import com.dmdev.entity.User;
import com.dmdev.until.HibernateUtil;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.model.naming.CamelCaseToUnderscoresNamingStrategy;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.time.LocalDate;

public class HibernateRunner {

    private static final Logger log= LoggerFactory.getLogger(HibernateRunner.class);
    public static void main(String[] args) throws SQLException {


        try (SessionFactory sessionFactory = HibernateUtil.buildSessionFactory())
        {
            User user = User.builder()
                    .username("Ivan@gmail.com")
                    .firstName("Ivan")
                    .lastName("Ivanov")
                    .build();
            log.info("User entity is in transient state, object: {}", user);
            Session session1=sessionFactory.openSession();
            try(session1) {
                Transaction transaction = session1.beginTransaction();
                log.trace("Transaction is created, {}", transaction);

                session1.saveOrUpdate(user);
                log.trace("User is in persistent state:{} session {}", user,session1);

                session1.getTransaction().commit();
            }
            log.warn("User is in detached state:{} session is closed {}", user,session1);
        } catch (Exception e){
            log.error("Exception occurred", e);
        }


    }
}
