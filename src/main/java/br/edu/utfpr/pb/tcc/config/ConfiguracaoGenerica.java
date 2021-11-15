package br.edu.utfpr.pb.tcc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Configuration
public class ConfiguracaoGenerica {

    @Bean
    public static Connection connection(DataSource dataSource) throws SQLException {

        return dataSource.getConnection();
    }
}




