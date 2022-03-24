package com.inside.controller;

import com.inside.dto.MessageDTO;
import com.inside.service.MessageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@Tag(name = "Message", description = "The Message API")
@RequiredArgsConstructor
@RequestMapping("/message")
public class MessageController {

    private final MessageService messageService;

    /**
     * Add message from user into the DB
     * @param bearerToken JWT token for verification
     * @param messageDTO Message DTO which one contains username and message
     */
    @PostMapping
    @Operation(summary = "Save message to DataBase")
    public void saveMessage(@RequestHeader String bearerToken, @RequestBody MessageDTO messageDTO){
        messageService.save(bearerToken, messageDTO);
    }

    /**
     * Get a specific amount of messages from the DB
     * @param bearerToken JWT token for verification
     * @param userName String username for which we want to put in the DB
     * @param amount number of messages to return
     * @return List of the messages
     */
    @GetMapping("/history/{amount}")
    @Operation(summary = "Get messages from DataBase")
    public List<MessageDTO> getHistory(@RequestHeader String bearerToken, final String userName, final int amount) {
      return messageService.getHistory(bearerToken, userName, amount);
    }
}
