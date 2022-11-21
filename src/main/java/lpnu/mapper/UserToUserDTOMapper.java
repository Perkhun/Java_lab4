package lpnu.mapper;

import lpnu.dto.UserDTO;
import lpnu.entity.Operation;
import lpnu.entity.User;
import org.springframework.stereotype.Component;
import javax.validation.Valid;
import java.util.List;

@Component
public class UserToUserDTOMapper {
    public User toEntity(final UserDTO userDTO, final List<Operation> operationList) {
        final User user = new User();

        user.setId(userDTO.getId());
        user.setFullname(userDTO.getFullname());
        user.setBirth(userDTO.getBirth());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        user.setEmail(userDTO.getEmail());
        user.setOperations(operationList);

        return user;
    }

    public UserDTO toDTO(final @Valid User user) {
        final UserDTO userDTO = new UserDTO();

        userDTO.setId(user.getId());
        userDTO.setFullname(user.getFullname());
        userDTO.setBirth(user.getBirth());
        userDTO.setPhoneNumber(user.getPhoneNumber());
        userDTO.setEmail(user.getEmail());

        return userDTO;
    }
}
