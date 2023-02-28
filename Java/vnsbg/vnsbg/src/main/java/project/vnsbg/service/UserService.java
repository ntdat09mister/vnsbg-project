package project.vnsbg.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.vnsbg.entity.dto.CustomerIdDto;
import project.vnsbg.entity.dto.DataLoginDto;
import project.vnsbg.entity.dto.UserDetailDto;
import project.vnsbg.entity.dto.UserDto;
import project.vnsbg.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    public DataLoginDto loginToTheSystem(UserDto userDto) {
        List<CustomerIdDto> userList = userRepository.findByUsernameAndPassword(userDto.getUserName(), userDto.getPassword());
        DataLoginDto dataLoginDto = new DataLoginDto();
        if (userList.isEmpty()) {
            dataLoginDto.setFullName("");
            dataLoginDto.setCustomerId("");
            dataLoginDto.setStatusLogin("false");
        } else {
            dataLoginDto.setCustomerId(String.valueOf(userList.get(0).getCustomerId()));
            dataLoginDto.setStatusLogin("true");
            dataLoginDto.setFullName(String.valueOf(userList.get(0).getFullName()));
        }
        return dataLoginDto;
    }

    public UserDetailDto getUserDetail(Long userId) {
        List<UserDetailDto> userDetailDtos = userRepository.getUserDetail(String.valueOf(userId));
        return userDetailDtos.get(0);
    }
}
