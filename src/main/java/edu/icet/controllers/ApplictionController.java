package edu.icet.controllers;

import edu.icet.dtos.ApplicationDto;
import edu.icet.service.ApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/application")
@RequiredArgsConstructor
@CrossOrigin
public class ApplictionController {
    private final ApplicationService applicationService;

    @PostMapping
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<?> applyForInternship(@RequestBody ApplicationDto applicationDto){
        try {
            return ResponseEntity.ok(applicationService.applyForInternship(applicationDto));
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/student/{studentId}")
    @PreAuthorize("hasRole('STUDENT') or hasRole('ADMIN')")
    public ResponseEntity<List<ApplicationDto>> getApplicationsByStudent(@PathVariable Long studentId) {
        return ResponseEntity.ok(applicationService.getApplicationsByStudent(studentId));
    }

    @GetMapping("/internship/{internshipId}")
    @PreAuthorize("hasRole('COMPANY') or hasRole('ADMIN')")
    public ResponseEntity<List<ApplicationDto>> getApplicationsByInternship(@PathVariable Long internshipId) {
        return ResponseEntity.ok(applicationService.getApplicationsByInternship(internshipId));
    }

    @GetMapping("/company/{companyId}")
    @PreAuthorize("hasRole('COMPANY') or hasRole('ADMIN')")
    public ResponseEntity<List<ApplicationDto>> getApplicationsByCompany(@PathVariable Long companyId) {
        return ResponseEntity.ok(applicationService.getApplicationsByCompany(companyId));
    }

    @PutMapping("/{applicationId}/status/{status}")
    @PreAuthorize("hasRole('COMPANY') or hasRole('ADMIN')")
    public ResponseEntity<ApplicationDto> updateApplicationStatus(@PathVariable Long applicationId,
                                                                  @PathVariable String status) {
        return ResponseEntity.ok(applicationService.updateApplicationStatus(applicationId, status));
    }
}
