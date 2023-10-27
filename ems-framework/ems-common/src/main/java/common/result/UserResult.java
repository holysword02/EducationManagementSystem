package common.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResult {
    private Boolean success;
    private TokenData data;

    public static UserResult success(String username, Integer roles, String accessToken, String refreshToken, Date expires) {
        return new UserResult(true, new TokenData());
    }

    public static UserResult success(TokenData tokenData) {
        return new UserResult(true, tokenData);
    }

    public static UserResult fail() {
        return new UserResult(false, null);
    }
}
