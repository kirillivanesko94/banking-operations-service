package com.example.servicebankingoperations.db.migration;

import jakarta.annotation.PostConstruct;
import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.configuration.ClassicConfiguration;

import javax.sql.DataSource;

public class FlywayMigration implements DbMigration {
    private final DataSource dataSource;

    public FlywayMigration(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * Method to initiate the database migration using Flyway.
     */
    @Override
    @PostConstruct
    public void migrate() {
        ClassicConfiguration config = new ClassicConfiguration();
        config.setBaselineOnMigrate(true);
        config.setTable("schema_version");
        config.setDataSource(dataSource);

        new Flyway(config).migrate();
    }
}
