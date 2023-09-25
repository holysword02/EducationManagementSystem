package common.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TokenData {
    private String username;
    private Integer roles;
    private String accessToken;
    private String refreshToken;
    private Date expires;
}
