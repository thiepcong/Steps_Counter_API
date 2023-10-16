package service.user;

import org.mapstruct.Mapper;

import dto.user.UserOutputDto;
import model.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
	UserOutputDto getOutputFromEntity(User user);
}
