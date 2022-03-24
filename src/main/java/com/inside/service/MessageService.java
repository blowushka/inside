package com.inside.service;

import com.inside.dto.MessageDTO;
import com.inside.model.Message;

import java.util.List;

public interface MessageService {

    MessageDTO save(String bearerToken, MessageDTO messageDTO);

    List<MessageDTO> getHistory(String userName, int amount);
}
