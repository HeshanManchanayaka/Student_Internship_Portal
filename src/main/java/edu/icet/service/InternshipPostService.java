package edu.icet.service;

import edu.icet.dtos.InternshipPostDto;

import java.util.List;

public interface InternshipPostService {
    InternshipPostDto createInternship(InternshipPostDto internshipPostDTO);
    List<InternshipPostDto> getInternshipsByCompany(Long companyId);
    List<InternshipPostDto> getAllInternships();
    InternshipPostDto getInternshipById(Long id);
    List<InternshipPostDto> searchInternships(String title, String location);
    InternshipPostDto updateInternship(Long id, InternshipPostDto internshipPostDTO);
    void deleteInternship(Long id);
}
