package com.nio.swc.configuration;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;


@Data
@Validated
@Configuration
@EqualsAndHashCode(callSuper = false)
@EnableConfigurationProperties
@ConfigurationProperties("sharingan")
public class SharinganProperties {
        @NotNull
        private String workspace;
}
