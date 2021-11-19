package com.activelogging.configurations;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "corelogging")
public class CoreActiveLoggingConfig {
    private String domain;
    private String faceLoggingStorage;

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getFaceLoggingStorage() {
        return faceLoggingStorage;
    }

    public void setFaceLoggingStorage(String reportStorage) {
        this.faceLoggingStorage = reportStorage;
    }
}
