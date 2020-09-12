package com.communicator.service;

import com.communicator.domain.FriendsList;
import com.communicator.domain.FriendsListDto;
import com.communicator.exception.FriendsListDontExistsException;
import com.communicator.exception.MessageDontExistsException;
import com.communicator.mapper.FriendsListMapper;
import com.communicator.service.repository.FriendsListRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class FriendsListService {
    private final FriendsListRepository repository;
    private final FriendsListMapper mapper;

    public List<FriendsListDto> getAll(){
        return mapper.mapToFriendsListDtoList(repository.findAll());
    }

    public FriendsListDto getById(Long id) throws Exception {
        return mapper.mapToFriendsListDto(repository.findById(id).orElseThrow(Exception::new));
    }

    public FriendsListDto create(FriendsListDto friendsListDto){
        FriendsList mappedFriendsList = mapper.mapToFriendsList(friendsListDto);
        FriendsList savedFriendsList = repository.save(mappedFriendsList);
        return mapper.mapToFriendsListDto(savedFriendsList);
    }

    public FriendsListDto update(FriendsListDto friendsListDto){
        if(friendsListDto.getId() != null){
            isAttachmentExisting(friendsListDto.getId());
        }
        FriendsList mappedFriendsList = mapper.mapToFriendsList(friendsListDto);
        FriendsList savedFriendsList = repository.save(mappedFriendsList);
        return mapper.mapToFriendsListDto(savedFriendsList);
    }

    public void delete(FriendsListDto friendsListDto){
        if(friendsListDto.getId() != null){
            isAttachmentExisting(friendsListDto.getId());
        }
        FriendsList mappedFriendsList = mapper.mapToFriendsList(friendsListDto);
        repository.delete(mappedFriendsList);
    }

    private void isAttachmentExisting(Long userId) {
        if(!repository.existsById(userId)){
            throw new FriendsListDontExistsException();
        }
    }
}
