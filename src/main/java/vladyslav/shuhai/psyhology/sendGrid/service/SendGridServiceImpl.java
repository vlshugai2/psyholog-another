package vladyslav.shuhai.psyhology.sendGrid.service;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import com.sendgrid.helpers.mail.objects.Personalization;
import org.springframework.stereotype.Service;
import vladyslav.shuhai.psyhology.sendGrid.model.EmailPojo;

import java.io.IOException;

@Service
public class SendGridServiceImpl implements SendGridService {

    final private String sendGridApi = "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";

    /**
     * PersonalizeEmail - details setting for each email. For the complete example:
     * https://github.com/sendgrid/sendgrid-java/blob/master/examples/helpers/mail/Example.java
     *
     * @param emailPojo
     * @return Mail
     */
    private Mail PersonalizeEmail(EmailPojo emailPojo) {

        Mail mail = new Mail();

        /* From information setting */
        Email fromEmail = new Email();
        fromEmail.setName(emailPojo.getFromName());
        fromEmail.setEmail(emailPojo.getFromEmail());

        mail.setFrom(fromEmail);
        mail.setSubject(emailPojo.getEmailSubject());

        /*
         * Personalization setting, we only add recipient info for this particular
         * example
         */
        Personalization personalization = new Personalization();
        Email to = new Email();
        to.setName(emailPojo.getToName());
        to.setEmail(emailPojo.getToEmail());
        personalization.addTo(to);

        personalization.addHeader("X-Test", "test");
        personalization.addHeader("X-Mock", "true");
        personalization.addHeader("Authorization:","Bearer"+sendGridApi);
        personalization.addHeader("Content-Type:","application/json");
        /* Substitution value settings */
        personalization.addDynamicTemplateData("%name%", emailPojo.getToName());
        personalization.addDynamicTemplateData("%from%", emailPojo.getFromName());

        mail.addPersonalization(personalization);

        /* Set template id */
        mail.setTemplateId("d-a88d4c587bd44509b605b1199cfc2791");

        /* Reply to setting */
        Email replyTo = new Email();
        replyTo.setName(emailPojo.getFromName());
        replyTo.setEmail(emailPojo.getFromEmail());
        mail.setReplyTo(replyTo);

        /* Adding Content of the email */
        Content content = new Content();

        /* Adding email message/body */
        content.setType("text/plain");
        content.setValue(emailPojo.getMessage());
        mail.addContent(content);

        return mail;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.monirthougth.sendgrid.service.SendGridService#sendMail(com.monirthought.
     * sendgrid.pojo.EmailPojo)
     */
    @Override
    public String sendMail(EmailPojo emailPojo) {

        SendGrid sg = new SendGrid(sendGridApi);
        sg.addRequestHeader("X-Mock", "true");

        Request request = new Request();
        Mail mail = PersonalizeEmail(emailPojo);
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);
            System.out.println(response.getStatusCode());
            System.out.println(response.getBody());
            System.out.println(response.getHeaders());
        } catch (IOException ex) {
            ex.printStackTrace();
            return "Failed to send mail! " + ex.getMessage();
        }
        return "Email is sent Successfully!!";
    }

}