package com.communicator.facade;

import com.communicator.domain.UserConvDto;
import com.communicator.domain.UserDto;
import com.communicator.domain.UserSearchDto;
import com.communicator.facade.logic.UserStatusToggle;
import com.communicator.service.UserService;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@ExtendWith(MockitoExtension.class)
public class UserFacadeTestSuite {

    @Mock
    private UserService service;
    @InjectMocks
    private UserFacade facade;
    @Mock
    private UserStatusToggle toggle;

    @Test
    public void shouldGetAllUsers() {
        //Given
        List<UserDto> users = new ArrayList<>();
        users.add(UserDto.builder()
                .id(1L)
                .firstname("Jan")
                .lastname("Szewczyk")
                .email("szewczykjan2@gmail.com")
                .build());
        users.add(UserDto.builder()
                .id(2L)
                .firstname("Jan")
                .lastname("Szewczyk")
                .email("fourstory0@gmail.com")
                .build());
        when(service.getAll()).thenReturn(users);
        //When
        List<UserDto> fetchedUsers = facade.getAllUsers();
        //Then
        assertEquals(fetchedUsers.size(), users.size());
        for(int i=0;i<fetchedUsers.size();i++){
            assertEquals(fetchedUsers.get(i).getId(), users.get(i).getId());
            assertEquals(fetchedUsers.get(i).getFirstname(), users.get(i).getFirstname());
            assertEquals(fetchedUsers.get(i).getLastname(), users.get(i).getLastname());
            assertEquals(fetchedUsers.get(i).getEmail(), users.get(i).getEmail());
        }
    }

    @Test
    public void shouldGetUserById() {
        //Given
        UserDto userDto = UserDto.builder()
                .id(1L)
                .firstname("Jan")
                .lastname("Szewczyk")
                .email("szewczykjan2@gmail.com")
                .build();
        when(service.getById(1L)).thenReturn(userDto);
        //When
        UserDto fetchedUsers = facade.getUserById(userDto.getId());
        //Then
        assertEquals(fetchedUsers.getId(), userDto.getId());
        assertEquals(fetchedUsers.getFirstname(), userDto.getFirstname());
        assertEquals(fetchedUsers.getLastname(), userDto.getLastname());
        assertEquals(fetchedUsers.getEmail(), userDto.getEmail());
    }

    @Test
    public void shouldGetUserConvById() {
        //Given
        UserConvDto userDto = UserConvDto.builder()
                .id(1L)
                .firstname("Jan")
                .lastname("Szewczyk")
                .build();
        when(service.getConvById(1L)).thenReturn(userDto);
        //When
        UserConvDto fetchedUsers = facade.getUserConvById(userDto.getId());
        //Then
        assertEquals(fetchedUsers.getId(), userDto.getId());
        assertEquals(fetchedUsers.getFirstname(), userDto.getFirstname());
        assertEquals(fetchedUsers.getLastname(), userDto.getLastname());
    }

    @Test
    public void shouldCreateUser() {
        //Given
        UserDto userDto = UserDto.builder()
                .id(1L)
                .firstname("Jan")
                .lastname("Szewczyk")
                .email("szewczykjan2@gmail.com")
                .build();
        when(service.create(userDto)).thenReturn(userDto);
        //When
        UserDto fetchedUsers = facade.createUser(userDto);
        //Then
        assertEquals(fetchedUsers.getId(), userDto.getId());
        assertEquals(fetchedUsers.getFirstname(), userDto.getFirstname());
        assertEquals(fetchedUsers.getLastname(), userDto.getLastname());
        assertEquals(fetchedUsers.getEmail(), userDto.getEmail());
    }

    @Test
    public void shouldUpdateUser() {
        UserDto userDto = UserDto.builder()
                .id(1L)
                .firstname("Jan")
                .lastname("Szewczyk")
                .email("szewczykjan2@gmail.com")
                .build();
        when(service.update(userDto)).thenReturn(userDto);
        //When
        UserDto fetchedUsers = facade.updateUser(userDto);
        //Then
        assertEquals(fetchedUsers.getId(), userDto.getId());
        assertEquals(fetchedUsers.getFirstname(), userDto.getFirstname());
        assertEquals(fetchedUsers.getLastname(), userDto.getLastname());
        assertEquals(fetchedUsers.getEmail(), userDto.getEmail());
    }

    @Test
    public void shouldChangeActivated() {
        UserDto userDto = UserDto.builder()
                .id(1L)
                .firstname("Jan")
                .lastname("Szewczyk")
                .email("szewczykjan2@gmail.com")
                .build();
        when(service.getById(userDto.getId())).thenReturn(userDto);
        when(toggle.statusChanger(userDto, true, false)).thenReturn(userDto);
        when(service.update(userDto)).thenReturn(userDto);
        //When
        UserDto fetchedUsers = facade.changeActivated(userDto.getId(), false);
        //Then
        assertEquals(fetchedUsers.getId(), userDto.getId());
        assertEquals(fetchedUsers.getFirstname(), userDto.getFirstname());
        assertEquals(fetchedUsers.getLastname(), userDto.getLastname());
        assertEquals(fetchedUsers.getEmail(), userDto.getEmail());
    }

    @Test
    public void schouldChangeBanned() {
        UserDto userDto = UserDto.builder()
                .id(1L)
                .firstname("Jan")
                .lastname("Szewczyk")
                .email("szewczykjan2@gmail.com")
                .build();
        when(service.getById(userDto.getId())).thenReturn(userDto);
        when(toggle.statusChanger(userDto, false, false)).thenReturn(userDto);
        when(service.update(userDto)).thenReturn(userDto);
        //When
        UserDto fetchedUsers = facade.changeBanned(userDto.getId(), false);
        //Then
        assertEquals(fetchedUsers.getId(), userDto.getId());
        assertEquals(fetchedUsers.getFirstname(), userDto.getFirstname());
        assertEquals(fetchedUsers.getLastname(), userDto.getLastname());
        assertEquals(fetchedUsers.getEmail(), userDto.getEmail());
    }

    @Test
    public void shouldChangeStatusToOne() {
        UserDto userDto = UserDto.builder()
                .id(1L)
                .firstname("Jan")
                .lastname("Szewczyk")
                .email("szewczykjan2@gmail.com")
                .build();
        when(service.getById(userDto.getId())).thenReturn(userDto);
        when(toggle.statusChanger(userDto, 1)).thenReturn(userDto);
        when(service.update(userDto)).thenReturn(userDto);
        //When
        UserDto fetchedUsers = facade.changeStatus(userDto.getId(), 1);
        //Then
        assertEquals(fetchedUsers.getId(), userDto.getId());
        assertEquals(fetchedUsers.getFirstname(), userDto.getFirstname());
        assertEquals(fetchedUsers.getLastname(), userDto.getLastname());
        assertEquals(fetchedUsers.getEmail(), userDto.getEmail());
    }

    @Test
    public void shouldChangeStatusToTwo() {
        UserDto userDto = UserDto.builder()
                .id(1L)
                .firstname("Jan")
                .lastname("Szewczyk")
                .email("szewczykjan2@gmail.com")
                .build();
        when(service.getById(userDto.getId())).thenReturn(userDto);
        when(toggle.statusChanger(userDto, 1)).thenReturn(userDto);
        when(service.update(userDto)).thenReturn(userDto);
        //When
        UserDto fetchedUsers = facade.changeStatus(userDto.getId(), 1);
        //Then
        assertEquals(fetchedUsers.getId(), userDto.getId());
        assertEquals(fetchedUsers.getFirstname(), userDto.getFirstname());
        assertEquals(fetchedUsers.getLastname(), userDto.getLastname());
        assertEquals(fetchedUsers.getEmail(), userDto.getEmail());
    }

    @Test
    public void getUserByRegexPatternOne() {
        List<UserSearchDto> searchDtos = new ArrayList<>();
        UserSearchDto userDto = UserSearchDto.builder()
                .id(1L)
                .firstname("Jan")
                .lastname("Szewczyk")
                .build();
        searchDtos.add(userDto);
        when(service.getBySingleNamePattern("Jan")).thenReturn(searchDtos);
        //When
        List<UserSearchDto> fetchedUsers = facade.getUserByRegexPattern(0L,"Jan");
        //Then
        assertEquals(fetchedUsers.get(0).getId(), userDto.getId());
        assertEquals(fetchedUsers.get(0).getFirstname(), userDto.getFirstname());
        assertEquals(fetchedUsers.get(0).getLastname(), userDto.getLastname());
    }

    @Test
    public void getUserByRegexPatternTwo() {
        List<UserSearchDto> searchDtos = new ArrayList<>();
        UserSearchDto userDto = UserSearchDto.builder()
                .id(1L)
                .firstname("Jan")
                .lastname("Szewczyk")
                .build();
        searchDtos.add(userDto);
        List<String> params = new ArrayList<>();
        params.add("Jan");
        params.add("Szew");
        when(service.getByNamePattern(params)).thenReturn(searchDtos);
        //When
        List<UserSearchDto> fetchedUsers = facade.getUserByRegexPattern(1L,"Jan Szew");
        //Then
        assertEquals(fetchedUsers.get(0).getId(), userDto.getId());
        assertEquals(fetchedUsers.get(0).getFirstname(), userDto.getFirstname());
        assertEquals(fetchedUsers.get(0).getLastname(), userDto.getLastname());
    }

    @Test
    public void getUserByRegexPatternThree() {
        List<UserSearchDto> searchDtos = new ArrayList<>();
        UserSearchDto userDto = UserSearchDto.builder()
                .id(1L)
                .firstname("Jan")
                .lastname("Szewczyk")
                .build();
        searchDtos.add(userDto);
        when(service.getByMailPattern("szewczykjan2@gmail.com")).thenReturn(searchDtos);
        //When
        List<UserSearchDto> fetchedUsers = facade.getUserByRegexPattern(2L,"szewczykjan2@gmail.com");
        //Then
        assertEquals(fetchedUsers.get(0).getId(), userDto.getId());
        assertEquals(fetchedUsers.get(0).getFirstname(), userDto.getFirstname());
        assertEquals(fetchedUsers.get(0).getLastname(), userDto.getLastname());
    }

    @Test
    public void getUserByData() {
        UserDto userDto = UserDto.builder()
                .id(1L)
                .firstname("Jan")
                .lastname("Szewczyk")
                .email("szewczykjan2@gmail.com")
                .build();
        when(service.getUserByData("Jan", "Szewczyk", "szewczykjan2@gmail.com")).thenReturn(userDto);
        //When
        UserDto fetchedUser = facade.getUserByData("Jan", "Szewczyk", "szewczykjan2@gmail.com");
        //Then
        assertEquals(fetchedUser.getId(), userDto.getId());
        assertEquals(fetchedUser.getFirstname(), userDto.getFirstname());
        assertEquals(fetchedUser.getLastname(), userDto.getLastname());
    }
}