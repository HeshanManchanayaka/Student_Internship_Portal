package edu.icet.controllers;

import edu.icet.dtos.InternshipPostDto;
import edu.icet.service.InternshipPostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/internships")
@RequiredArgsConstructor
@CrossOrigin
public class InternshipPostController {

    private final InternshipPostService internshipPostService;

    @PostMapping
    @PreAuthorize("hasRole('COMPANY')")
    public ResponseEntity<InternshipPostDto> createInternship(@RequestBody InternshipPostDto internshipPostDTO) {
        return ResponseEntity.ok(internshipPostService.createInternship(internshipPostDTO));
    }

    @GetMapping("/company/{companyId}")
    @PreAuthorize("hasRole('COMPANY') or hasRole('ADMIN')")
    public ResponseEntity<List<InternshipPostDto>> getInternshipsByCompany(@PathVariable Long companyId) {
        List<InternshipPostDto> dto = internshipPostService.getInternshipsByCompany(companyId);
        System.out.println(dto);
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<List<InternshipPostDto>> getAllInternships() {
        return ResponseEntity.ok(internshipPostService.getAllInternships());
    }

    @GetMapping("/{id}")
    public ResponseEntity<InternshipPostDto> getInternshipById(@PathVariable Long id) {
        return ResponseEntity.ok(internshipPostService.getInternshipById(id));
    }

    @GetMapping("/search")
    public ResponseEntity<List<InternshipPostDto>> searchInternships(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String location) {
        return ResponseEntity.ok(internshipPostService.searchInternships(title, location));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('COMPANY')")
    public ResponseEntity<InternshipPostDto> updateInternship(@PathVariable Long id,
                                                              @RequestBody InternshipPostDto internshipPostDto) {
        return ResponseEntity.ok(internshipPostService.updateInternship(id, internshipPostDto));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('COMPANY') or hasRole('ADMIN')")
    public ResponseEntity<Void> deleteInternship(@PathVariable Long id) {
        internshipPostService.deleteInternship(id);
        return ResponseEntity.ok().build();
    }
}
