package vladyslav.shuhai.psyhology.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vladyslav.shuhai.psyhology.dto.request.EventRequest;
import vladyslav.shuhai.psyhology.dto.response.EventResponse;
import vladyslav.shuhai.psyhology.entity.Event;
import vladyslav.shuhai.psyhology.entity.User;
import vladyslav.shuhai.psyhology.repository.EventRepository;
import vladyslav.shuhai.psyhology.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventService {
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private UserService userService;

    public void create(EventRequest request){
        eventRepository.save(eventRequestToEvent(null,request));
    }
    public Event findById(Long id){
        return eventRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("Event with id:"+id+"is not exists"));
    }
    private Event eventRequestToEvent(Event event,EventRequest request){
        if (event == null){
            event = new Event();
        }
        event.setGroupName(request.getGroupName());
        event.setStartDate(request.getStartDate());
        event.setStartTime(request.getStartTime());
        event.setFinishDate(request.getFinishDate());
        event.setFinishTime(request.getFinishTime());
        event.setDescription(request.getDescription());
        event.setLocation(request.getLocation());
        event.setImgPath(request.getImgPath());


        return event;
    }
    public void update(Long id,EventRequest request){
        eventRepository.save(eventRequestToEvent(findById(id),request));
    }
    public void delete(Long id){
        eventRepository.delete(findById(id));
    }
    public List<EventResponse> findAll(){
        return eventRepository.findAll().stream().map(EventResponse::new).collect(Collectors.toList());
    }
}
