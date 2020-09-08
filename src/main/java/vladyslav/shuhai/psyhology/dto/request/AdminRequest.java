package vladyslav.shuhai.psyhology.dto.request;

import lombok.Getter;
import lombok.Setter;
import vladyslav.shuhai.psyhology.entity.UserRole;

import javax.persistence.Column;
@Getter
@Setter
public class AdminRequest {
    private String username;
    private String password;

}
