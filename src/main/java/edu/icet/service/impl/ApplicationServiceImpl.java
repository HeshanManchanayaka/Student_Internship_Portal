package edu.icet.service.impl;

import edu.icet.dtos.ApplicationDto;
import edu.icet.entities.Application;
import edu.icet.entities.InternshipPost;
import edu.icet.entities.User;
import edu.icet.enums.ApplicationStatus;
import edu.icet.repository.ApplicationRepository;
import edu.icet.repository.InternshipPostRepository;
import edu.icet.repository.UserRepository;
import edu.icet.service.ApplicationService;
import edu.icet.service.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ApplicationServiceImpl implements ApplicationService {

    private final ModelMapper modelMapper;
    private final ApplicationRepository applicationRepository;
    private final UserRepository userRepository;
    private final InternshipPostRepository internshipPostRepository;
    private final EmailService emailService;

    @Override
    public ApplicationDto applyForInternship(ApplicationDto applicationDto) {
        User student = userRepository.findById(applicationDto.getStudentId())
                .orElseThrow(() -> new RuntimeException("Student not found"));

        InternshipPost internshipPost = internshipPostRepository.findById(applicationDto.getInternshipPostId())
                .orElseThrow(() -> new RuntimeException("Internship not found"));

        Application application = modelMapper.map(applicationDto, Application.class);
        application.setStudent(student);
        application.setInternshipPost(internshipPost);

        Application savedApplication = applicationRepository.save(application);
        return modelMapper.map(savedApplication, ApplicationDto.class);
    }

    @Override
    public List<ApplicationDto> getApplicationsByStudent(Long studentId) {
        return applicationRepository.findByStudentId(studentId).stream()
                .map(application -> modelMapper.map(application, ApplicationDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ApplicationDto> getApplicationsByInternship(Long internshipId) {
        return applicationRepository.findByInternshipPostId(internshipId).stream()
                .map(application -> modelMapper.map(application, ApplicationDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ApplicationDto> getApplicationsByCompany(Long companyId) {
        return applicationRepository.findByInternshipPostCreatedById(companyId).stream()
                .map(application -> modelMapper.map(application, ApplicationDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public ApplicationDto updateApplicationStatus(Long applicationId, String status) {
        Application application = applicationRepository.findById(applicationId)
                .orElseThrow(() -> new RuntimeException("Application not found"));

        try {
            ApplicationStatus newStatus = ApplicationStatus.valueOf(status.toUpperCase());
            application.setStatus(newStatus);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid application status: " + status);
        }
        try {
            emailService.sendStatusUpdateNotification(
                    application.getStudent().getEmail(),
                    application.getInternshipPost().getTitle(),
                    status
            );
        } catch (Exception e) {
            log.error("Failed to send status update email: {}", e.getMessage());
        }

        Application updatedApplication = applicationRepository.save(application);
        return modelMapper.map(updatedApplication, ApplicationDto.class);
    }


}
