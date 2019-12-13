package com.arobs.internship.demointernship.service.user;

import com.arobs.internship.demointernship.entity.User;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;
import org.springframework.stereotype.Component;

@Component
public class UserMapper extends ConfigurableMapper {

    protected void configure(MapperFactory factory) {
        factory.classMap(User.class, UserDTO.class)
                .byDefault()
                .register();
    }
}
