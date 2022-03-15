package rtuitlab.deliveries.mappers;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import rtuitlab.deliveries.dto.user.UserInfoDTO;
import rtuitlab.deliveries.dto.user.UserRegisterDTO;
import rtuitlab.deliveries.dto.user.UserUpdateDTO;
import rtuitlab.deliveries.entities.UserEntity;

@Component
@Mapper(componentModel = "spring")
public interface UserEntityMapper {
    UserEntity registerDTOToEntity(UserRegisterDTO registerDTO);
    UserEntity updateDTOToEntity(UserUpdateDTO updateDTO);
    UserInfoDTO entityToInfoDTO(UserEntity userEntity);
}
