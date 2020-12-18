package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.UUID;

@Document(collection = "group")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Group {
    private String groupId;

    private String groupName;

    private String adminId;

    private List<Integer> userIds;

    public Group(String groupName,String adminId) {
        this.groupId = UUID.randomUUID().toString();
        this.groupName = groupName;
        this.adminId = adminId;
    }


}
