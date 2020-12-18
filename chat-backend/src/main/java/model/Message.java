package model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.UUID;


@AllArgsConstructor
@Data
@NoArgsConstructor
@Document(collection = "message")
public class Message {

    private String id;

    private String content;

    private Long timestamp;

    private String groupId;

    public Message(String content){
        this.id = UUID.randomUUID().toString();
        this.content = content;
        this.groupId = groupId;
        this.timestamp = Instant.now().getEpochSecond() * 1000;
    }

}
