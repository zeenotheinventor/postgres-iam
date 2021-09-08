package com.example.zeeno.postgres;

import io.magj.iamjdbcdriver.IamAuthJdbcDriverWrapper;
import io.magj.iamjdbcdriver.PostgreSqlIamAuthJdbcDriverWrapper;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

public class IAMDriverTester {

    public static void main(String[] args) throws Exception {
        PostgreSqlIamAuthJdbcDriverWrapper driver = new PostgreSqlIamAuthJdbcDriverWrapper();
        String url = "jdbc:iampostgresql://pg-aws-rds-iam-test.cbbkpz5mggxh.eu-west-1.rds.amazonaws.com:5432/test_database";

        Properties connectionProperties = new Properties();

        connectionProperties.put(IamAuthJdbcDriverWrapper.DEFAULT_USER_PROPERTY, "test_user");
        connectionProperties.put(IamAuthJdbcDriverWrapper.AWS_REGION_PROPERTY, "eu-west-1");

        Connection connection = driver.connect(url, connectionProperties);

        //verify the connection is successful
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("SELECT 'Success!! DATABASE IS CONNECTED!' AS result;");
        while (rs.next()) {
            String id = rs.getString(1);
            System.out.println(id);
        }

        //close the connection
        statement.close();
        connection.close();
    }

}
