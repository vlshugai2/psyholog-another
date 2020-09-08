package vladyslav.shuhai.psyhology.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vladyslav.shuhai.psyhology.dto.request.AdminRequest;
import vladyslav.shuhai.psyhology.dto.response.AuthenticationResponse;
import vladyslav.shuhai.psyhology.service.AdminService;

import javax.validation.Valid;
@RestController
@CrossOrigin
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;
    @PostMapping("/login")
    public final AuthenticationResponse login(
            @Valid @RequestBody final AdminRequest request) {
        return adminService.login(request);
    }
    @PostMapping("/register")
    public final AuthenticationResponse register(
            @Valid @RequestBody final AdminRequest request) {
        return adminService.register(request);
    }
}
