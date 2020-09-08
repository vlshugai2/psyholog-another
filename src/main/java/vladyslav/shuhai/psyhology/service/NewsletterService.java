package vladyslav.shuhai.psyhology.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import vladyslav.shuhai.psyhology.dto.request.EventRequest;
import vladyslav.shuhai.psyhology.dto.request.NewsletterRequest;
import vladyslav.shuhai.psyhology.entity.Event;
import vladyslav.shuhai.psyhology.entity.Newsletter;
import vladyslav.shuhai.psyhology.repository.NewsletterRepository;

@Service
public class NewsletterService {
    @Autowired
    private NewsletterRepository newsletterRepository;
    @Autowired
    private MailSender mailSender;
    public void create(NewsletterRequest request){
        newsletterRepository.save(newsletterRequestToNewsletter(null,request));
    }



    private Newsletter newsletterRequestToNewsletter(Newsletter newsletter,
                                                     NewsletterRequest request){
        if (newsletter == null){
            newsletter = new Newsletter();
        }
        newsletter.setName(request.getName());
        newsletter.setEmail(request.getEmail());
        if (!StringUtils.isEmpty(request.getEmail())) {
            String customerMessage = String.format(
                            "Ви успішно підписалися на наші новини.\n"+
                                    " Тепер ви будете повідомлені про всі нові події, гарного дня."
            );
            String adminMessage = String.format("Ім'я: "+request.getName()+", Email: "+request.getEmail());
            mailSender.send(request.getEmail(), "Підписка на новини студії Self", customerMessage);
            mailSender.send("mshugay@gmail.com","Підписка на НОВИНИ", adminMessage);
        }
        return newsletter;
    }

}
