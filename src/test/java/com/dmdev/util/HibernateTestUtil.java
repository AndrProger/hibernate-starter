package com.dmdev.util;

import com.dmdev.until.HibernateUtil;
import lombok.experimental.UtilityClass;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.testcontainers.containers.PostgreSQLContainer;

@UtilityClass
public class HibernateTestUtil {
    private static final PostgreSQLContainer<?>  postgres=new PostgreSQLContainer<>("postgres:13");
    static {
        postgres.start();
    }
    public static SessionFactory buildSessionFactory(){
        Configuration configuration = HibernateUtil.buildConfiguration();

        configuration.setProperty("hibernate.connection.url", postgres.getJdbcUrl());
        System.out.println("url: "+postgres.getJdbcUrl());
        configuration.setProperty("hibernate.connection.username", postgres.getUsername());
        System.out.println("username: "+postgres.getUsername());
        configuration.setProperty("hibernate.connection.password", postgres.getPassword());
        System.out.println("password: "+postgres.getPassword());
        configuration.configure();

        return configuration.buildSessionFactory();
    }

}
