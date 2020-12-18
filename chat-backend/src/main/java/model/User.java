package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Document(collection = "user")
public class User {

    private String id;

    private String password;

    private String name;

    private String email;

    private List<Integer> groupIds;

    public User(String name, String email,String password){
        this.id = UUID.randomUUID().toString();
        this.password = password;
        this.name = name;
        this.email = email;
    }

}
