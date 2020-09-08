package vladyslav.shuhai.psyhology.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserRequest {

    private String firstName;
    private String secondName;
    private String email;
    private String phoneNumber;
    private List<Long> eventId;
}
