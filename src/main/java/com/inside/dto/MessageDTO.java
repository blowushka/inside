package com.inside.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class MessageDTO {

    @JsonProperty("name")
    private String sender;

    @JsonProperty("message")
    private String text;
}
