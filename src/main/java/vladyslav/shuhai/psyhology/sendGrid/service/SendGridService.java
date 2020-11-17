package vladyslav.shuhai.psyhology.sendGrid.service;

import vladyslav.shuhai.psyhology.sendGrid.model.EmailPojo;

public interface SendGridService {
    String sendMail(EmailPojo emailPojo);
}
