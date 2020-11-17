package vladyslav.shuhai.psyhology.sendGrid.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmailPojo {

    private String toName;
    private String toEmail;
    private String fromName;
    private String fromEmail;
    private String emailSubject;
    private String message;
}