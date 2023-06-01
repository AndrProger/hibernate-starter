package com.dmdev;

import com.dmdev.entity.Company;
import com.dmdev.entity.Profile;
import com.dmdev.entity.User;
import com.dmdev.until.HibernateUtil;
import lombok.Cleanup;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;

import javax.persistence.Column;
import javax.persistence.Table;
import java.lang.reflect.Field;

import java.sql.SQLException;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.joining;


class HibernateRunnerTest {
    @Test
     void checkOneToOne() {
        try(SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
            Session session = sessionFactory.openSession()){
            session.beginTransaction();
            var user =session.get(User.class,7L);
            System.out.println();

//            var user = User.builder()
//                    .username("test@g.d")
//                    .build();
//            var profile = Profile.builder()
//                    .language("ru")
//                    .street("kols 2")
//                    .build();
//
//            session.save(user);
//            profile.setUser(user);


            session.getTransaction().commit();


        }
    }
    @Test
    void checkOrhanRemoval(){
        try(SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
            Session session = sessionFactory.openSession()){
            session.beginTransaction();

            var company = session.get(Company.class, 2L);
            company.getUsers().removeIf(user -> user.getId().equals(6L));
            session.getTransaction().commit();

        }
    }
    @Test
    void checkLazyInitialisatuon(){
        Company company =null;
        try(SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
        Session session = sessionFactory.openSession()){
            session.beginTransaction();

            company = session.get(Company.class, 2L);

            session.getTransaction().commit();

        }
        var  users =company.getUsers();
        System.out.println(users.size());


    }
    @Test
    void deleteCompany(){
        @Cleanup SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup Session session = sessionFactory.openSession();

        session.beginTransaction();

        User company = session.get(User.class, 3L);
        session.delete(company);
        session.getTransaction().commit();
    }

    @Test
    void addUserToNewCompany() {
        @Cleanup SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup Session session = sessionFactory.openSession();

        session.beginTransaction();

        Company company = Company.builder()
                .name("Facebook")
                .build();

        User user = User.builder()
                .username("sveta@gmail.com")
                .build();
        company.addUser(user);

        session.save(company);
        session.getTransaction().commit();
    }

    @Test
    void oneToMany() {
        @Cleanup SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup Session session = sessionFactory.openSession();

        session.beginTransaction();


        Company company = session.get(Company.class, 2);
    System.out.println();
        session.getTransaction().commit();
    }

    @Test
    void checkReflectionAPI() throws SQLException, IllegalAccessException {
        User user = User.builder()
                .build();
        String sql = """
                    insert 
                    into 
                    %s
                    (%s)
                    values
                    (%s)
                """;
        String tableName = ofNullable(user.getClass().getAnnotation(Table.class))
                .map(tableAnnotation -> tableAnnotation.schema() + "." + tableAnnotation.name())
                .orElse(user.getClass().getName());


        Field[] declaredFields = user.getClass().getDeclaredFields();
        String columnNames = Arrays.stream(declaredFields)
                .map(field -> Optional.ofNullable(field.getAnnotation(Column.class))
                        .map(Column::name)
                        .orElse(field.getName()))
                .collect(Collectors.joining(", "));

        String columnValues = Arrays.stream(declaredFields).map(field -> "?").collect(joining(", "));

        System.out.println(sql.formatted(tableName, columnNames, columnValues));

//        Connection conn =null;
//        PreparedStatement preparedStatement = conn.prepareStatement(sql.formatted(tableName,columnNames, columnValues));
//        for (Field field : declaredFields){
//            field.setAccessible(true);
//            preparedStatement.setObject(1,field.get(user));
//        }
        // ANSI Escape-последовательности для установки красного цвета
        String redColor = "\u001B[31m";
        // ANSI Escape-последовательство для сброса цвета в консоли
        String resetColor = "\u001B[0m";

        // Текст, который нужно вывести красным
        String text = "red text!";

        // Вывод текста красным цветом
        System.out.println(redColor + text + resetColor);
    }
}