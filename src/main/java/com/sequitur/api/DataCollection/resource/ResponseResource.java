package com.sequitur.api.DataCollection.resource;

import lombok.Data;

@Data
public class ResponseResource {
    private Long id;
    private Long intentId;
    private String messageText;
}
