package vladyslav.shuhai.psyhology.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import vladyslav.shuhai.psyhology.dto.request.EventRequest;
import vladyslav.shuhai.psyhology.dto.request.NewsletterRequest;
import vladyslav.shuhai.psyhology.entity.Event;
import vladyslav.shuhai.psyhology.entity.Newsletter;
import vladyslav.shuhai.psyhology.repository.NewsletterRepository;
import vladyslav.shuhai.psyhology.sendGrid.model.EmailPojo;
import vladyslav.shuhai.psyhology.sendGrid.service.SendGridServiceImpl;

@Service
public class NewsletterService {
    @Autowired
    private NewsletterRepository newsletterRepository;
    @Autowired
    private SendGridServiceImpl sendGridService;
    public void create(NewsletterRequest request) throws Exception {
        newsletterRepository.save(newsletterRequestToNewsletter(null,request));
    }



    private Newsletter newsletterRequestToNewsletter(Newsletter newsletter,
                                                     NewsletterRequest request) throws Exception {
        if (newsletter == null){
            newsletter = new Newsletter();
        }
        newsletter.setName(request.getName());
        newsletter.setEmail(request.getEmail());
        if (!StringUtils.isEmpty(request.getEmail())) {
//            String customerMessage = String.format(
//                            "Ви успішно підписалися на наші новини.\n"+
//                                    " Тепер ви будете повідомлені про всі нові події, гарного дня."
//            );
//            String adminMessage = String.format("Ім'я: "+request.getName()+", Email: "+request.getEmail());
//            mailSender.send(request.getEmail(), "Підписка на новини студії Self", customerMessage);
//            mailSender.send("mshugay@gmail.com","Підписка на НОВИНИ", adminMessage);
            EmailPojo emailPojo = new EmailPojo();
            emailPojo.setFromEmail("vlshugai1@gmail.com");
            emailPojo.setEmailSubject("Новини психотерапевт Марія Шугай");
            emailPojo.setFromName("Маря Шугай");
            emailPojo.setToEmail(request.getEmail());
            emailPojo.setToName(request.getName());
            emailPojo.setMessage("Ви успішно підписалися на новини. Тепере ви будете повідомлені про всі нові події, гарного дня");
            sendGridService.sendMail(emailPojo);
         //   mailSender.sendText("vlshugai1@gmail.com",request.getEmail(),"some title","some text");

        }
        return newsletter;
    }

}
//new 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
