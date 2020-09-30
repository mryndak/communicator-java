package com.communicator.service;


import com.communicator.domain.*;
import com.communicator.mapper.UserMapper;
import com.communicator.service.repository.UserRepository;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@ExtendWith(MockitoExtension.class)
public class UserServiceTestSuite {

    @Mock
    private UserRepository repository;
    @InjectMocks
    private UserService service;
    @Mock
    private UserMapper mapper;

    @Test
    public void getAll() {
        //Given
        List<User> users = new ArrayList<>();
        users.add(User.builder()
                .id(1L)
                .firstname("Jan")
                .lastname("Szewczyk")
                .email("szewczykjan2@gmail.com")
                .build());
        List<UserDto> usersDtoList = new ArrayList<>();
        usersDtoList.add(UserDto.builder()
                .id(1L)
                .firstname("Jan")
                .lastname("Szewczyk")
                .email("szewczykjan2@gmail.com")
                .build());
        when(mapper.mapUserListToUserDtoList(users)).thenReturn(usersDtoList);
        when(repository.findAll()).thenReturn(users);
        //When
        List<UserDto> usersDtos = service.getAll();
        //Given
        assertEquals(usersDtos.size(), users.size());
    }

    @Test
    public void getById() {
        //Given
        User user = User.builder()
                .id(1L)
                .firstname("Jan")
                .lastname("Szewczyk")
                .email("szewczykjan2@gmail.com")
                .build();
        UserDto usersDto = UserDto.builder()
                .id(1L)
                .firstname("Jan")
                .lastname("Szewczyk")
                .email("szewczykjan2@gmail.com")
                .build();
        when(mapper.mapToUserDto(user)).thenReturn(usersDto);
        given(repository.findById(1L)).willReturn(Optional.of(user));
        //When
        UserDto userDto = service.getById(1L);
        //Given
        assertEquals(userDto.getId(), user.getId());
        assertEquals(userDto.getFirstname(), user.getFirstname());
        assertEquals(userDto.getLastname(), user.getLastname());
        assertEquals(userDto.getEmail(), user.getEmail());
    }

    @Test
    public void getConvById() {
        //Given
        User user = User.builder()
                .id(1L)
                .firstname("Jan")
                .lastname("Szewczyk")
                .email("szewczykjan2@gmail.com")
                .build();
        UserConvDto usersDto = UserConvDto.builder()
                .id(1L)
                .firstname("Jan")
                .lastname("Szewczyk")
                .build();
        when(mapper.mapToUserConvDto(user)).thenReturn(usersDto);
        given(repository.findById(1L)).willReturn(Optional.of(user));
        //When
        UserConvDto userDto = service.getConvById(1L);
        //Given
        assertEquals(userDto.getId(), user.getId());
        assertEquals(userDto.getFirstname(), user.getFirstname());
        assertEquals(userDto.getLastname(), user.getLastname());
    }

    @Test
    public void create() {
        //Given
        User user = User.builder()
                .id(1L)
                .firstname("Jan")
                .lastname("Szewczyk")
                .email("szewczykjan2@gmail.com")
                .build();
        UserDto usersDto = UserDto.builder()
                .id(1L)
                .firstname("Jan")
                .lastname("Szewczyk")
                .build();
        UserDataChecker userConvDto = UserDataChecker.builder()
                .firstname("Jan")
                .email("szewczykjan2@gmail.com")
                .build();
        userConvDto.setLastname("Szewczyk");
        when(mapper.mapToUserDto(user)).thenReturn(usersDto);
        when(mapper.mapToUser(usersDto)).thenReturn(user);
        when(mapper.mapToUserDataChecker(usersDto)).thenReturn(userConvDto);
        given(repository.save(user)).willReturn(user);
        //When
        UserDto userDto = service.create(usersDto);
        //Given
        assertEquals(userDto.getId(), user.getId());
        assertEquals(userDto.getFirstname(), user.getFirstname());
        assertEquals(userDto.getLastname(), user.getLastname());
    }

    @Test
    public void update() {
        //Given
        User user = User.builder()
                .id(1L)
                .firstname("Jan")
                .lastname("Szewczyk")
                .email("szewczykjan2@gmail.com")
                .build();
        UserDto usersDto = UserDto.builder()
                .id(1L)
                .firstname("Jan")
                .lastname("Szewczyk")
                .build();
        when(mapper.mapToUserDto(user)).thenReturn(usersDto);
        when(mapper.mapToUser(usersDto)).thenReturn(user);
        given(repository.save(user)).willReturn(user);
        //When
        UserDto userDto = service.update(usersDto);
        //Given
        assertEquals(userDto.getId(), user.getId());
        assertEquals(userDto.getFirstname(), user.getFirstname());
        assertEquals(userDto.getLastname(), user.getLastname());
    }

    @Test
    public void getUserByData() {
        //Given
        User user = User.builder()
                .id(1L)
                .firstname("Jan")
                .lastname("Szewczyk")
                .email("szewczykjan2@gmail.com")
                .build();
        UserDto usersDto = UserDto.builder()
                .id(1L)
                .firstname("Jan")
                .lastname("Szewczyk")
                .email("szewczykjan2@gmail.com")
                .build();
        UserDataChecker userDataChecker = UserDataChecker.builder()
                .firstname("Jan")
                .lastname("Szewczyk")
                .email("szewczykjan2@gmail.com")
                .build();
        when(mapper.mapToUserDto(user)).thenReturn(usersDto);
        given(repository.getByFirstnameAndLastnameAndEmail(userDataChecker.getFirstname(), userDataChecker.getLastname(), userDataChecker.getEmail())).willReturn(user);
        //When
        UserDto userDto = service.getUserByData(userDataChecker.getFirstname(), userDataChecker.getLastname(), userDataChecker.getEmail());
        //Given
        assertEquals(userDto.getId(), user.getId());
        assertEquals(userDto.getFirstname(), user.getFirstname());
        assertEquals(userDto.getLastname(), user.getLastname());
    }

    @Test
    public void isUserExistingByData() {
        //Given
        UserDataChecker userDataChecker = UserDataChecker.builder()
                .firstname("Jan")
                .lastname("Szewczyk")
                .email("szewczykjan2@gmail.com")
                .build();
        given(repository.existsByFirstnameAndLastnameAndEmail(userDataChecker.getFirstname(), userDataChecker.getLastname(), userDataChecker.getEmail())).willReturn(true);
        //When
        boolean exists = service.isUserExistingByData(userDataChecker);
        //Given
        assertTrue(exists);
    }
}
