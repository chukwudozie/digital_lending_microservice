package dev.code.digital_lending_microservice;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.containers.Network;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@DirtiesContext
@Testcontainers
@Slf4j
class DigitalLendingMicroserviceApplicationTests {
    public static final Network NETWORK = Network.newNetwork();
    @Container
    public static final MySQLContainer<?> MYSQL = new MySQLContainer<>("mysql:8.0.22")
            .withDatabaseName("test")
            .withPassword("test")
            .withUsername("test")
            .withNetwork(NETWORK)
            .waitingFor(Wait.forLogMessage("*ready for connections*", 1));

    @DynamicPropertySource
    static void mysqlProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", MYSQL::getJdbcUrl);
        registry.add("spring.datasource.username", () -> "test");
        registry.add("spring.datasource.password", () -> "test");
        registry.add("spring.liquibase.enabled", () -> true);
        registry.add("spring.liquibase.change-log", () -> "classpath:db/changelog/changelog-master.yaml");
    }

    @Test
    void contextLoads() {
    }

}
