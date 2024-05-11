package org.brito.pontodigitalbackend.core;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper geraBeanModelMapper(){
        return new ModelMapper();
    }
}
