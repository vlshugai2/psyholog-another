package vladyslav.shuhai.psyhology.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vladyslav.shuhai.psyhology.dto.request.EventRequest;
import vladyslav.shuhai.psyhology.dto.response.EventResponse;
import vladyslav.shuhai.psyhology.service.EventService;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/adminPanel")
public class EventController {
    @Autowired
    private EventService eventService;
    @PostMapping("/addEvent")
    public final void create(@Valid @RequestBody final EventRequest request) {

        eventService.create(request);
    }
    @GetMapping("/getEvent")
    public List<EventResponse> findAll(){
        return eventService.findAll();

    }

}
