package vladyslav.shuhai.psyhology.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import vladyslav.shuhai.psyhology.dto.request.EventRequest;
import vladyslav.shuhai.psyhology.dto.request.UserRequest;
import vladyslav.shuhai.psyhology.entity.Admin;
import vladyslav.shuhai.psyhology.entity.Event;
import vladyslav.shuhai.psyhology.entity.User;
import vladyslav.shuhai.psyhology.repository.UserRepository;
import vladyslav.shuhai.psyhology.security.JwtUser;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
@Autowired
private EventService eventService;
@Autowired
private MailSender mailSender;
    public void create(UserRequest request){
        userRepository.save(register(null,request));
    }

    public User findById(Long id){
        return userRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("User with id:"+id+"is not exists"));
    }
    public User register(User user, UserRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            user = findByEmail(request.getEmail());
            if(user.getEvents().contains(eventService.findById(request.getEventId().get(0)))){
                System.out.println("event contains");
            }
            else {
                List<Long> events = request.getEventId();
                if (events != null && !events.isEmpty()) {
                    List<Event> collect = new ArrayList<>();
                    collect = user.getEvents();

                    collect.add(eventService.findById(events.get(0)));


                    System.out.println(collect);
                    // List<Event> collect = events.stream().map(eventService::findById).collect(Collectors.toList());
                    user.setEvents(collect);

                }
            }
            }

        else {
            if (user == null) {
                user = new User();
            }

            user.setFirstName(request.getFirstName());
            user.setSecondName(request.getSecondName());
            user.setEmail(request.getEmail());
            user.setPhoneNumber(request.getPhoneNumber());

            List<Long> events = request.getEventId();
            if (events != null && !events.isEmpty()) {
                List<Event> collect = events.stream().map(eventService::findById).collect(Collectors.toList());
                user.setEvents(collect);
            }

        }
        assert request.getEventId() != null;
        String groupNameStr = eventService.findById(request.getEventId().get(0)).getGroupName();
        String customerMessage = String.format("Ви успішно зареєстровані на групу: "+groupNameStr+
                ".\nНаш менеджер зв'яжеться з вами для підтвердження реєстрації, гарного дня.");
        String adminMessage = String.format("Ім'я: "+request.getFirstName()+"\nПрізвище: "+request.getSecondName()+
                "\nEmail: "+request.getEmail()+"\nНомер телефону: "+request.getPhoneNumber()+
                "\nЗаписався на групу: "+groupNameStr+
                "\nСписок групи: "+eventService.findById(request.getEventId().get(0)).getUsers().toString()) + user;
        mailSender.send("mshugay@gmail.com","ЗАПИС на групу",adminMessage );
        mailSender.send(request.getEmail(),"Реєстрація на групове заняття: "+groupNameStr,customerMessage);
        return user;
    }
    private User findByEmail(String email)  {
        return userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User with email: " + email + " not exists"));
    }
    public void update(Long id, UserRequest request) {
        userRepository.save(register(findById(id), request));
    }

}
