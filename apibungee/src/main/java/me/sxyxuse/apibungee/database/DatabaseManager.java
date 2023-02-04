package me.sxyxuse.apibungee.database;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import me.sxyxuse.apibungee.ApiBungee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.logging.Level;

public class DatabaseManager {
    private final String url;
    private final String user;
    private final String password;
    private final ApiBungee APIBUNGEE;
    private HikariDataSource hikariDataSource;

    public DatabaseManager(ApiBungee apiBungee) {
        this.url = "jdbc:mysql://localhost:8889/minecraft";
        this.user = "root";
        this.password = "root";
        this.APIBUNGEE = apiBungee;
        this.initPool();
    }

    private void setupHikari() {
        final HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setMaximumPoolSize(10);
        hikariConfig.setJdbcUrl(getUrl());
        hikariConfig.setUsername(getUser());
        hikariConfig.setPassword(getPassword());
        hikariConfig.setMaxLifetime(300000L);
        hikariConfig.setLeakDetectionThreshold(300000L);
        hikariConfig.setConnectionTimeout(10000L);

        this.hikariDataSource = new HikariDataSource(hikariConfig);
    }

    public void initPool() {
        setupHikari();
        APIBUNGEE.log(Level.WARNING, "Connexion à la base de données établie.");
    }

    public void closePool() {
        this.hikariDataSource.close();
        APIBUNGEE.log(Level.WARNING, "Connexion à la base de données interrompue.");
    }

    public Connection getConnection() throws SQLException {
        if (this.hikariDataSource == null)
            setupHikari();

        return this.hikariDataSource.getConnection();
    }

    public String getUrl() {
        return this.url;
    }

    public String getUser() {
        return this.user;
    }

    public String getPassword() {
        return this.password;
    }

    public void update(String qry) {
        try (Connection connection = getConnection();
             PreparedStatement s = connection.prepareStatement(qry)) {
            s.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Object query(String qry, Function<ResultSet, Object> consumer) {
        try (Connection connection = getConnection();
             PreparedStatement s = connection.prepareStatement(qry);
             ResultSet rs = s.executeQuery()) {
            return consumer.apply(rs);
        } catch (SQLException e) {
            throw new IllegalStateException(e.getMessage());
        }
    }

    public void query(String qry, Consumer<ResultSet> consumer) {
        try (Connection connection = getConnection();
             PreparedStatement s = connection.prepareStatement(qry);
             ResultSet rs = s.executeQuery()) {
            consumer.accept(rs);
        } catch (SQLException e) {
            throw new IllegalStateException(e.getMessage());
        }
    }
}
