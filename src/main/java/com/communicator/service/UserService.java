package com.communicator.service;

import com.communicator.domain.Attachments;
import com.communicator.domain.User;
import com.communicator.domain.UserDto;
import com.communicator.exception.UserDontExistsException;
import com.communicator.exception.UserNotFoundException;
import com.communicator.mapper.UserMapper;
import com.communicator.service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository repository;
    private final UserMapper mapper;

    public List<UserDto> getAll(){
        return mapper.mapUserListToUserDtoList(repository.findAll());
    }

    public UserDto getById(Long id) {
        return mapper.mapToUserDto(repository.findById(id).orElseThrow(UserNotFoundException::new));
    }

    @Transactional
    public UserDto create(UserDto userDto){
        User mappedUser = mapper.mapToUser(userDto);
        Attachments profilePicture = Attachments.builder()
                .fileName("950-9501315_katie-notopoulos-katienotopoulos-i-write-about-tech-user")
                .fileExtension("PNG")
                .filePath("https://www.pngkey.com/png/detail/950-9501315_katie-notopoulos-katienotopoulos-i-write-about-tech-user.png")
                .build();
        mappedUser.setCreationDate(new Date());
        mappedUser.setProfilePic(profilePicture);
        User savedUser = repository.save(mappedUser);
        return mapper.mapToUserDto(savedUser);
    }

    public UserDto update(UserDto userDto){
        if(userDto.getId() != null){
            isUserExisting(userDto.getId());
        }
        User mappedUser = mapper.mapToUser(userDto);
        User savedUser = repository.save(mappedUser);
        return mapper.mapToUserDto(savedUser);
    }

    public void delete(UserDto userDto){
        if(userDto.getId() != null){
            isUserExisting(userDto.getId());
        }
        User mappedUser = mapper.mapToUser(userDto);
        repository.delete(mappedUser);
    }

    private void isUserExisting(Long userId) {
        if(!repository.existsById(userId)){
            throw new UserDontExistsException();
        }
    }

}
