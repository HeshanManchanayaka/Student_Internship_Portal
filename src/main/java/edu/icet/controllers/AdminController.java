package edu.icet.controllers;

import edu.icet.dtos.UserDto;
import edu.icet.service.UserService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
@CrossOrigin
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    private final UserService userService;

    @GetMapping("/users")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }
    @GetMapping("/users/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUser(id));
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }
}
