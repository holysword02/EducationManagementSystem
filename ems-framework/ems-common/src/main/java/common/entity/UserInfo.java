package common.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfo {
    private Serializable userId;
    private String userName;
    private Serializable companyId;
    private String companyName;
}
