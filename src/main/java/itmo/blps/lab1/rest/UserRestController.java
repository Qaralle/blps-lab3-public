package itmo.blps.lab1.rest;

import itmo.blps.lab1.repository.xml.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/app/")
public class UserRestController {
    private final UserRepository userRepository;

    public UserRestController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("userList")
    public ResponseEntity getReservedTime() {
        return ResponseEntity.ok(userRepository.findAll());
    }
}
