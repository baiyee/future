package com.zx.config.orika;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrikaConfiguration {

    public OrikaConfiguration() {
    }

    @Bean
    public OrikaBeanMapper beanMapper() {
        return new OrikaBeanMapper();
    }

}
