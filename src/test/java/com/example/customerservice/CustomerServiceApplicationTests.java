package com.example.customerservice;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@Slf4j
class CustomerServiceApplicationTests {

    @Autowired
    private DataSource dataSource;
    @Test
    void contextLoads() {
    }

    @Test
    void databaseConnectionTest(){
        assertThat(dataSource).isNotNull();
        log.info("DataSource properties -> {}", dataSource);

        try {
            Connection connection = dataSource.getConnection();
            assertThat(connection).isNotNull();
            log.info("Database -> {}", connection.getCatalog());
            assertThat(connection.getCatalog()).isEqualTo("customerdb");
        }catch (SQLException exception){
            log.info("An exception occurred -> {}", exception.getMessage());
        }
    }

}
