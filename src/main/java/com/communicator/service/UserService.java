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
import java.util.ArrayList;
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
        try{
            User user = repository.findById(id).orElseThrow(UserNotFoundException::new);
            return mapper.mapToUserDto(user);
        }catch (UserNotFoundException e){
            log.error(e.getMessage());
        }
        return UserDto.builder().build();
    }

    public UserConvDto getConvById(Long id) {
        try{
            User user = repository.findById(id).orElseThrow(UserNotFoundException::new);
            return mapper.mapToUserConvDto(user);
        }catch (UserNotFoundException e){
            log.error(e.getMessage());
        }
        return UserConvDto.builder().build();
    }

    public List<UserSearchDto> getByNamePattern(List<String> pattern) {
        List<User> usersTypeA = repository.findUsingNamePatternTypeA(pattern.get(0), pattern.get(1));
        List<User> usersTypeB = repository.findUsingNamePatternTypeB(pattern.get(0), pattern.get(1));
        return getUserSearchDtos(usersTypeA, usersTypeB);
    }

    public List<UserSearchDto> getBySingleNamePattern(String pattern) {
        List<User> usersTypeA = repository.findUsingSingleNamePatternTypeA(pattern);
        List<User> usersTypeB = repository.findUsingSingleNamePatternTypeB(pattern);
        return getUserSearchDtos(usersTypeA, usersTypeB);
    }

    private List<UserSearchDto> getUserSearchDtos(List<User> usersTypeA, List<User> usersTypeB) {
        try{
            if(usersTypeA.size() > usersTypeB.size()){
                return mapper.mapUserListToUserSearchDtoList(usersTypeA);
            }else if(usersTypeB.size() > usersTypeA.size()){
                return mapper.mapUserListToUserSearchDtoList(usersTypeB);
            }else{
                throw new UserNotFoundException();
            }
        }catch (UserNotFoundException e){
            log.error(e.getMessage());
        }
        return new ArrayList<>();
    }

    public List<UserSearchDto> getByMailPattern(String pattern) {
        List<User> userList = repository.findUsingMailPattern(pattern);
        log.info(String.valueOf(userList.size()));
        return mapper.mapUserListToUserSearchDtoList(userList);
    }

    @Transactional
    public void create(UserDto userDto){
        try{
            if(!isUserExistingByData(mapper.mapToUserDataChecker(userDto))){
                User mappedUser = mapper.mapToUser(userDto);
                mappedUser.setCreationDate(new Date());
                repository.save(mappedUser);
            }else{
                throw new UserExistsException();
            }
        }catch (UserExistsException e){
            log.error(e.getMessage());
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

    private void isUserExisting(Long userId) {
        try{
            if(!repository.existsById(userId)){
                throw new UserDontExistsException();
            }
        }catch (UserDontExistsException e){
            log.error(e.getMessage());
        }
    }

    public UserDto getUserByData(String firstname, String lastname, String email) {
        return mapper.mapToUserDto(repository.getByFirstnameAndLastnameAndEmail(firstname, lastname, email));
    }

    public boolean isUserExistingByData(UserDataChecker userDataChecker){
        return repository.existsByFirstnameAndLastnameAndEmail(userDataChecker.getFirstname(),userDataChecker.getLastname(),userDataChecker.getEmail());
    }
}
