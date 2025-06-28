package edu.icet.service;

import edu.icet.dtos.ApplicationDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ApplicationService {
    ApplicationDto applyForInternship(ApplicationDto applicationDto);

    List<ApplicationDto> getApplicationsByInternship(Long internshipId);

    List<ApplicationDto> getApplicationsByStudent(Long studentId);

    List<ApplicationDto> getApplicationsByCompany(Long companyId);

    ApplicationDto updateApplicationStatus(Long applicationId, String status);
}
