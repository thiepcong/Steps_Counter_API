package com.example.stepcounter.service.user;

import com.example.stepcounter.dto.user.UserInputDto;
import com.example.stepcounter.dto.user.UserOutputDto;
import com.example.stepcounter.model.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-11-04T15:40:18+0700",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.8 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserOutputDto getOutputFromEntity(User user) {
        if ( user == null ) {
            return null;
        }

        UserOutputDto userOutputDto = new UserOutputDto();

        userOutputDto.setId( user.getId() );
        userOutputDto.setName( user.getName() );
        userOutputDto.setAge( user.getAge() );
        userOutputDto.setHeight( user.getHeight() );
        userOutputDto.setWeight( user.getWeight() );
        userOutputDto.setGender( user.getGender() );

        return userOutputDto;
    }

    @Override
    public User getEntityFromInputDto(UserInputDto userInputDto) {
        if ( userInputDto == null ) {
            return null;
        }

        User user = new User();

        user.setName( userInputDto.getName() );
        user.setAge( userInputDto.getAge() );
        user.setHeight( userInputDto.getHeight() );
        user.setWeight( userInputDto.getWeight() );
        user.setGender( userInputDto.getGender() );

        return user;
    }
}
