package vladyslav.shuhai.psyhology.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthenticationResponse {
    private String username;

    private String token;


    public AuthenticationResponse(final String username, final String token) {
        this.username = username;
        this.token = token;
    }
}
