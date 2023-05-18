package com.dmdev;

import com.dmdev.entity.User;
import org.junit.jupiter.api.Test;

import javax.persistence.Column;
import javax.persistence.Table;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.joining;

class HibernateRunnerTest {

    @Test
    void checkReflectionAPI() throws SQLException, IllegalAccessException {
        User user = User.builder()
                .build();
        String sql= """
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
        String columnNames =Arrays.stream(declaredFields)
                .map(field-> Optional.ofNullable(field.getAnnotation(Column.class))
                    .map(Column::name)
                    .orElse(field.getName()))
                .collect(Collectors.joining(", "));

        String columnValues = Arrays.stream(declaredFields).map(field -> "?").collect(joining(", "));

        System.out.println(sql.formatted(tableName,columnNames, columnValues));

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