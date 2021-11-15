package br.edu.utfpr.pb.tcc.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcConnection {


//    spring.profiles.active=prod
//    spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
//    spring.datasource.url=jdbc:postgresql://localhost:5433/tcc2
//    spring.datasource.username=postgres
//    spring.datasource.password=postgres
//    spring.datasource.driverClassName=org.postgresql.Driver
//
    private static final String URL = "jdbc:mysql://localhost:3306/jaspercurso?serverTimezone=America/Sao_Paulo";
    private static final String USER = "root";
    private static final String PASS = "root";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";

    public static Connection connection() {

        Connection connection = null;
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL, USER, PASS);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return connection;
    }
}



