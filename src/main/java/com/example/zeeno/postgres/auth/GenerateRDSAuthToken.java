package com.example.zeeno.postgres.auth;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.services.rds.auth.GetIamAuthTokenRequest;
import com.amazonaws.services.rds.auth.RdsIamAuthTokenGenerator;

public class GenerateRDSAuthToken {

    public static String generateAuthToken(String region, String hostName, String port, String username) {
        RdsIamAuthTokenGenerator generator = RdsIamAuthTokenGenerator.builder()
                .credentials(new DefaultAWSCredentialsProviderChain())
                .region(region)
                .build();

        return generator.getAuthToken(
                GetIamAuthTokenRequest.builder()
                        .hostname(hostName)
                        .port(Integer.parseInt(port))
                        .userName(username)
                        .build());
    }

}