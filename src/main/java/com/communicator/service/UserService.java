package com.communicator.service;

import com.communicator.domain.*;
import com.communicator.exception.UserDontExistsException;
import com.communicator.exception.UserExistsException;
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

    public UserConvDto getConvById(Long id) {
        return mapper.mapToUserConvDto(repository.findById(id).orElseThrow(UserNotFoundException::new));
    }

    public List<UserSearchDto> getByNamePattern(List<String> pattern) {
        List<User> usersTypeA = repository.findUsingNamePatternTypeA(pattern.get(0), pattern.get(1));
        List<User> usersTypeB = repository.findUsingNamePatternTypeB(pattern.get(0), pattern.get(1));
        if(usersTypeA.size() > usersTypeB.size()){
            return mapper.mapUserListToUserSearchDtoList(usersTypeA);
        }else if(usersTypeB.size() > usersTypeA.size()){
            return mapper.mapUserListToUserSearchDtoList(usersTypeB);
        }else{
            throw new UserNotFoundException();
        }
    }

    public List<UserSearchDto> getBySingleNamePattern(String pattern) {
        List<User> usersTypeA = repository.findUsingSingleNamePatternTypeA(pattern);
        List<User> usersTypeB = repository.findUsingSingleNamePatternTypeB(pattern);
        if(usersTypeA.size() > usersTypeB.size()){
            return mapper.mapUserListToUserSearchDtoList(usersTypeA);
        }else if(usersTypeB.size() > usersTypeA.size()){
            return mapper.mapUserListToUserSearchDtoList(usersTypeB);
        }else{
            throw new UserNotFoundException();
        }
    }

    public List<UserSearchDto> getByMailPattern(String pattern) {
        List<User> userList = repository.findUsingMailPattern(pattern);
        log.info(String.valueOf(userList.size()));
        return mapper.mapUserListToUserSearchDtoList(userList);
    }

    @Transactional
    public void create(UserDto userDto){
        if(!isUserExistingByData(mapper.mapToUserDataChecker(userDto))){
            User mappedUser = mapper.mapToUser(userDto);
            mappedUser.setCreationDate(new Date());
            repository.save(mappedUser);
        }
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

    public UserDto getUserByData(String firstname, String lastname, String email) {
        return mapper.mapToUserDto(repository.getByFirstnameAndLastnameAndEmail(firstname, lastname, email));
    }

    public boolean isUserExistingByData(UserDataChecker userDataChecker){
        return repository.existsByFirstnameAndLastnameAndEmail(userDataChecker.getFirstname(),userDataChecker.getLastname(),userDataChecker.getEmail());
    }
}
