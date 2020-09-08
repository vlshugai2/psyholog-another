package vladyslav.shuhai.psyhology.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vladyslav.shuhai.psyhology.dto.request.EventRequest;
import vladyslav.shuhai.psyhology.dto.request.UserRequest;
import vladyslav.shuhai.psyhology.service.EventService;
import vladyslav.shuhai.psyhology.service.UserService;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/create")
    public final void create(@Valid @RequestBody final UserRequest request) {
        userService.create(request);
    }

}
