package sisnot.sisnot.Mapper;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import sisnot.sisnot.Model.Dto.UserProfileDTO;
import sisnot.sisnot.Model.Dto.UserRegistrationDTO;
import sisnot.sisnot.Model.entity.User;

@RequiredArgsConstructor
@Component
public class UserMapper {

    private final ModelMapper modelMapper;

    public User toUserEntity(UserRegistrationDTO registrationDTO) {
        return modelMapper.map(registrationDTO, User.class);
    }

    public UserProfileDTO toUserProfileDTO(User user) {
        return modelMapper.map(user, UserProfileDTO.class);
    }
}
