package com.communicator.controller;

import com.communicator.domain.UserConvDto;
import com.communicator.domain.UserDto;
import com.communicator.domain.UserSearchDto;
import com.communicator.facade.UserFacade;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTestSuite {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserFacade facade;

    @Test
    public void shouldFetchAllUsers() throws Exception {
        //Given
        List<UserDto> userDtoList = new ArrayList<>();
        userDtoList.add(UserDto.builder()
                .id(1L)
                .firstname("Jan")
                .lastname("Szewczyk")
                .email("szewczykjan2@gmail.com")
                .build());
        userDtoList.add(UserDto.builder()
                .id(2L)
                .firstname("Jan")
                .lastname("Szewczyk")
                .email("fourstory0@gmail.com")
                .build());
        when(facade.getAllUsers()).thenReturn(userDtoList);
        //When & Then
        mockMvc.perform(get("/v1/users").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    public void shouldFetchUserById() throws Exception {
        //Given
        UserDto userDto = UserDto.builder()
                .id(1L)
                .firstname("Jan")
                .lastname("Szewczyk")
                .email("szewczykjan2@gmail.com")
                .build();
        when(facade.getUserById(1L)).thenReturn(userDto);
        //When & Then
        mockMvc.perform(get("/v1/users/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.firstname", is("Jan")))
                .andExpect(jsonPath("$.lastname", is("Szewczyk")))
                .andExpect(jsonPath("$.email", is("szewczykjan2@gmail.com")));
    }

    @Test
    public void shouldFetchUserConvDtoById() throws Exception {
        //Given
        UserConvDto userConvDto = UserConvDto.builder()
                .id(1L)
                .firstname("Jan")
                .lastname("Szewczyk")
                .build();
        when(facade.getUserConvById(1L)).thenReturn(userConvDto);
        //When & Then
        mockMvc.perform(get("/v1/users/1/conv").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.firstname", is("Jan")))
                .andExpect(jsonPath("$.lastname", is("Szewczyk")));
    }

    @Test
    public void shouldFetchMatchingUsers() throws Exception {
        //Given
        List<UserSearchDto> userSearchDtoList = new ArrayList<>();
        userSearchDtoList.add(UserSearchDto.builder()
                .id(1L)
                .firstname("Jan")
                .lastname("Szewczyk")
                .build());
        userSearchDtoList.add(UserSearchDto.builder()
                .id(2L)
                .firstname("Jan")
                .lastname("Szewczyk")
                .build());
        when(facade.getUserByRegexPattern(1L, "Jan")).thenReturn(userSearchDtoList);
        //When & Then
        mockMvc.perform(get("/v1/users/pattern")
                .contentType(MediaType.APPLICATION_JSON)
                .param("searchType", "1")
                .param("searchValue", "Jan"))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    public void shouldFetchUserByData() throws Exception {
        //Given
        UserDto userDto = UserDto.builder()
                .id(1L)
                .firstname("Jan")
                .lastname("Szewczyk")
                .email("szewczykjan2@gmail.com")
                .build();
        when(facade.getUserByData("Jan", "Szewczyk", "szewczykjan2@gmail.com")).thenReturn(userDto);
        //When & Then
        mockMvc.perform(get("/v1/users/googleauth")
                .contentType(MediaType.APPLICATION_JSON)
                .param("firstname", "Jan")
                .param("lastname", "Szewczyk")
                .param("email", "szewczykjan2@gmail.com"))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.firstname", is("Jan")))
                .andExpect(jsonPath("$.lastname", is("Szewczyk")))
                .andExpect(jsonPath("$.email", is("szewczykjan2@gmail.com")));

    }

    @Test
    public void shouldCreateUser() throws Exception {
        //Given
        UserDto userDto = UserDto.builder()
                .id(1L)
                .firstname("Jan")
                .lastname("Szewczyk")
                .email("szewczykjan2@gmail.com")
                .build();
        when(facade.createUser(userDto)).thenReturn(userDto);
        //When & Then
        mockMvc.perform(post("/v1/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(userDto)))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.firstname", is("Jan")))
                .andExpect(jsonPath("$.lastname", is("Szewczyk")))
                .andExpect(jsonPath("$.email", is("szewczykjan2@gmail.com")));

    }

    @Test
    public void shouldUpdateUser() throws Exception {
        //Given
        UserDto userDto = UserDto.builder()
                .id(1L)
                .firstname("Jan")
                .lastname("Szewczyk")
                .email("szewczykjan2@gmail.com")
                .build();
        when(facade.updateUser(userDto)).thenReturn(userDto);
        //When & Then
        mockMvc.perform(put("/v1/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(userDto)))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.firstname", is("Jan")))
                .andExpect(jsonPath("$.lastname", is("Szewczyk")))
                .andExpect(jsonPath("$.email", is("szewczykjan2@gmail.com")));

    }

    @Test
    public void shouldChangeBannedStatus() throws Exception {
        //Given
        UserDto userDto = UserDto.builder()
                .id(1L)
                .firstname("Jan")
                .lastname("Szewczyk")
                .email("szewczykjan2@gmail.com")
                .build();
        when(facade.changeBanned(1L, false)).thenReturn(userDto);
        //When & Then
        mockMvc.perform(put("/v1/users/1/banned")
                .contentType(MediaType.APPLICATION_JSON)
                .param("value", "false"))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.firstname", is("Jan")))
                .andExpect(jsonPath("$.lastname", is("Szewczyk")))
                .andExpect(jsonPath("$.email", is("szewczykjan2@gmail.com")));
    }

    @Test
    public void shouldChangeActiveStatus() throws Exception {
        //Given
        UserDto userDto = UserDto.builder()
                .id(1L)
                .firstname("Jan")
                .lastname("Szewczyk")
                .email("szewczykjan2@gmail.com")
                .build();
        when(facade.changeActivated(1L, false)).thenReturn(userDto);
        //When & Then
        mockMvc.perform(put("/v1/users/1/activated")
                .contentType(MediaType.APPLICATION_JSON)
                .param("value", "false"))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.firstname", is("Jan")))
                .andExpect(jsonPath("$.lastname", is("Szewczyk")))
                .andExpect(jsonPath("$.email", is("szewczykjan2@gmail.com")));
    }

    @Test
    public void shouldChangeStatus() throws Exception {
        //Given
        UserDto userDto = UserDto.builder()
                .id(1L)
                .firstname("Jan")
                .lastname("Szewczyk")
                .email("szewczykjan2@gmail.com")
                .build();
        when(facade.changeStatus(1L, 1)).thenReturn(userDto);
        //When & Then
        mockMvc.perform(put("/v1/users/1/status")
                .contentType(MediaType.APPLICATION_JSON)
                .param("value", "1"))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.firstname", is("Jan")))
                .andExpect(jsonPath("$.lastname", is("Szewczyk")))
                .andExpect(jsonPath("$.email", is("szewczykjan2@gmail.com")));
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
