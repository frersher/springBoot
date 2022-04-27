package com.shine.service;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(ignoreUnknownFields = false, prefix = "citi.sftp.client")
public class SftpProperties {
    private String host;

    private Integer port;

    private String protocol;

    private String username;

    private String password;

    private String root;

    private String privateKey;

    private String passphrase;

    private String sessionStrictHostKeyChecking;

    private Integer sessionConnectTimeout;

    private Integer channelConnectedTimeout;
}