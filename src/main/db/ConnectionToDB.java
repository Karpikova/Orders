package main.db;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;

import java.sql.*;
import java.util.concurrent.Future;

public class ConnectionToDB {

    public static DataSource dataSource;

    static {
        PoolProperties p = new PoolProperties();
        p.setUrl("jdbc:postgresql://localhost:5432/OCRV");
        p.setDriverClassName("org.postgresql.Driver");
        p.setUsername("postgres");
        p.setPassword("1");
        p.setJmxEnabled(true);
        p.setTestWhileIdle(false);
        p.setTestOnBorrow(true);
        p.setValidationQuery("SELECT 1");
        p.setTestOnReturn(false);
        p.setValidationInterval(30000);
        p.setTimeBetweenEvictionRunsMillis(30000);
        p.setMaxActive(500);
        p.setInitialSize(10);
        p.setMaxWait(10000);
        p.setRemoveAbandonedTimeout(60);
        p.setMinEvictableIdleTimeMillis(30000);
        p.setMinIdle(10);
        p.setLogAbandoned(true);
        p.setRemoveAbandoned(true);
        p.setJdbcInterceptors(
                "org.apache.tomcat.jdbc.pool.interceptor.ConnectionState;" +
                        "org.apache.tomcat.jdbc.pool.interceptor.StatementFinalizer");
        dataSource = new DataSource();
        dataSource.setPoolProperties(p);
    }

    private Connection connection;

    public static void execute(String qText) {
        try {
            ConnectionToDB connectionToDB = new ConnectionToDB();
            Connection connection = connectionToDB.toConnect();
            Statement st = connection.createStatement(); //TODO PS
            st.execute(qText);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static int executeInt(String qText) throws SQLException {
        try {
            ConnectionToDB connectionToDB = new ConnectionToDB();
            Connection connection = connectionToDB.toConnect();
            PreparedStatement st = connection.prepareStatement(qText);
            st.execute();
            ResultSet resultSet = st.getResultSet();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            } else {
                throw new SQLException("Странно, но при добавлении заявки не вернулся ее номер");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Беда в БД");
        }

    }

    public Connection toConnect() {
        try {
            Future<Connection> future = dataSource.getConnectionAsync();
            int numb_of_try = 0;
            while (!future.isDone() && numb_of_try < 100) {
                numb_of_try++;
                try {
                    Thread.sleep(100);
                } catch (InterruptedException x) {
                    Thread.currentThread().interrupt();
                }
            }
            if (!future.isDone()) {
                System.out.println("Connection is not yet available. We are sorry. Try again later.");
                throw new SQLException();
            }
            connection = future.get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

}