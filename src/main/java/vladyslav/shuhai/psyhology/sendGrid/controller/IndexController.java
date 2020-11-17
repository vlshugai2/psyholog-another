package vladyslav.shuhai.psyhology.sendGrid.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import vladyslav.shuhai.psyhology.sendGrid.model.EmailPojo;
import vladyslav.shuhai.psyhology.sendGrid.service.SendGridService;

@RestController
public class IndexController {

    @Autowired
    SendGridService sendGridService;

    @RequestMapping(value = "/email/", method = RequestMethod.POST)
    public String index(@RequestBody EmailPojo emailPojo) {
        String response = sendGridService.sendMail(emailPojo);
        return response;
    }

}

