package com.inside.service;

import com.inside.dto.MessageDTO;

import java.util.List;

/**
 * This interface allows ut to save messages to DB and also get them from its.
 *
 */
public interface MessageService {

    /**
     * Save message to DB
     * @param bearerToken string JWT token, which one we get from header
     * @param messageDTO message DTO, where we store sender name(name) and message text
     * @return MessageDTO
     */
    MessageDTO save(String bearerToken, MessageDTO messageDTO);

    /**
     * Get exact amount of messages from DB
     * @param bearerToken string JWT token, which one we get from header
     * @param userName string name, which one we get from users request
     * @param amount number of messages we will get from the DB
     * @return
     */
    List<MessageDTO> getHistory(String bearerToken, String userName, int amount);
}
