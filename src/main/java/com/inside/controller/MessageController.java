package com.inside.controller;

import com.inside.dto.MessageDTO;
import com.inside.service.MessageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@Tag(name = "Message", description = "The Message API")
@RequiredArgsConstructor
@RequestMapping("/message")
public class MessageController {

    private final MessageService messageService;

    @PostMapping
    @Operation(summary = "Save message to DataBase")
    public void saveMessage(@RequestBody MessageDTO messageDTO){
        messageService.save(messageDTO);
    }
}
