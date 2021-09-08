package com.example.zeeno.postgres.domains.debug;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.example.zeeno.postgres.auth.GenerateRDSAuthToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/debug")
public class DebugController {

    @Autowired
    private Environment env;

    @GetMapping("/variables")
    public Map<String, String> getVariables(){
        Map<String, String> variables  = new HashMap<>();

        String region = env.getProperty("aws.region");
        String hostname = env.getProperty("aws.hostname");
        String port = env.getProperty("aws.port");
        String username = env.getProperty("aws.username");

        DefaultAWSCredentialsProviderChain chain = new DefaultAWSCredentialsProviderChain();

        variables.put("accessKey", chain.getCredentials().getAWSAccessKeyId());
        variables.put("secretKey", chain.getCredentials().getAWSSecretKey());

        String token = GenerateRDSAuthToken.generateAuthToken(region, hostname, port, username);
        variables.put("rdsToken", token);
        return variables;
    }
}
