package vladyslav.shuhai.psyhology.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import vladyslav.shuhai.psyhology.dto.request.AdminRequest;
import vladyslav.shuhai.psyhology.dto.request.EventRequest;
import vladyslav.shuhai.psyhology.dto.response.AuthenticationResponse;
import vladyslav.shuhai.psyhology.entity.Admin;
import vladyslav.shuhai.psyhology.entity.User;
import vladyslav.shuhai.psyhology.entity.UserRole;
import vladyslav.shuhai.psyhology.repository.AdminRepository;
import vladyslav.shuhai.psyhology.security.JwtTokenTool;
import vladyslav.shuhai.psyhology.security.JwtUser;

@Service
public class AdminService implements UserDetailsService {
@Autowired
    private AdminRepository adminRepository;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    public JwtTokenTool jwtTokenTool;
    @Autowired
    private BCryptPasswordEncoder encoder;
    private Admin findByUsername(String username)  {
        return adminRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User with name " + username + " not exists"));
    }
    public AuthenticationResponse register(AdminRequest request) {
        if (adminRepository.existsByUsername(request.getUsername())) {
            throw new BadCredentialsException("User with username " + request.getUsername() + " already exists");
        }
        Admin admin = new Admin();

        admin.setUsername(request.getUsername());
        admin.setUserRole(UserRole.ROLE_ADMIN);
        admin.setPassword(encoder.encode(request.getPassword()));

        adminRepository.save(admin);

        return login(request);
    }
    public AuthenticationResponse login(AdminRequest request) {
        String username = request.getUsername();
        Admin admin = findByUsername(username);
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, request.getPassword()));
        String token = jwtTokenTool.createToken(username, admin.getUserRole());
        return new AuthenticationResponse(username, token);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Admin admin = findByUsername(username);
        return new JwtUser(admin.getUsername(), admin.getUserRole(), admin.getPassword());
    }
}
