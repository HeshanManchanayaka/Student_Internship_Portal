package edu.icet.service.impl;

import edu.icet.dtos.InternshipPostDto;
import edu.icet.entities.InternshipPost;
import edu.icet.entities.User;
import edu.icet.enums.UserRole;
import edu.icet.repository.InternshipPostRepository;
import edu.icet.repository.UserRepository;
import edu.icet.service.InternshipPostService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InternshipPostServiceImpl implements InternshipPostService {
    private final InternshipPostRepository internshipPostRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public InternshipPostDto createInternship(InternshipPostDto internshipPostDto) {
        User user = userRepository.findById(internshipPostDto.getCreatedById())
                .orElseThrow(() -> new RuntimeException("User not found"));

        InternshipPost entity = modelMapper.map(internshipPostDto, InternshipPost.class);
        entity.setCreatedBy(user);
        InternshipPost saved = internshipPostRepository.save(entity);
        System.out.println(entity);
        return modelMapper.map(saved, InternshipPostDto.class);
    }

    @Override
    public List<InternshipPostDto> getInternshipsByCompany(Long companyId) {
        User user = userRepository.findById(companyId)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + companyId));

        if (user.getRole() != UserRole.COMPANY) {
            throw new RuntimeException("User with ID " + companyId + " is not a company");
        }
        return internshipPostRepository.findById(companyId).stream()
                .map(entity->modelMapper.map(entity, InternshipPostDto.class))
                .toList();
    }

    @Override
    public List<InternshipPostDto> getAllInternships() {
        return internshipPostRepository.findAll()
                .stream()
                .map(post -> modelMapper.map(post, InternshipPostDto.class))
                .toList();
    }

    @Override
    public InternshipPostDto getInternshipById(Long id) {
        return null;
    }

    @Override
    public List<InternshipPostDto> searchInternships(String title, String location) {
        return List.of();
    }

    @Override
    public InternshipPostDto updateInternship(Long id, InternshipPostDto internshipPostDTO) {
        return null;
    }

    @Override
    public void deleteInternship(Long id) {

    }
}
