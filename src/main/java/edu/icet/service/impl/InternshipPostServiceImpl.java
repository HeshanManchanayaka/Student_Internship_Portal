package edu.icet.service.impl;

import edu.icet.dtos.InternshipPostDto;
import edu.icet.dtos.UserDto;
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
import java.util.stream.Collectors;

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
        if (user.getRole() != UserRole.COMPANY) {
            throw new RuntimeException("User with ID  is not a company");
        }
        InternshipPost entity = modelMapper.map(internshipPostDto, InternshipPost.class);
        entity.setCreatedBy(user);
        InternshipPost saved = internshipPostRepository.save(entity);
        return modelMapper.map(saved, InternshipPostDto.class);
    }

    @Override
    public List<InternshipPostDto> getInternshipsByCompany(Long companyId) {
        User user = userRepository.findById(companyId)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + companyId));

        if (user.getRole() != UserRole.COMPANY) {
            throw new RuntimeException("User with ID " + companyId + " is not a company");
        }
        return internshipPostRepository.findByCreatedById(companyId).stream()
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
        return internshipPostRepository.findById(id)
                .map(InternshipPost -> modelMapper.map(InternshipPost, InternshipPostDto.class))
                .orElse(null);
    }

    @Override
    public List<InternshipPostDto> searchInternships(String title, String location) {
        return internshipPostRepository
                .findByTitleAndLocation(title, location)
                .stream()
                .map(post -> modelMapper.map(post, InternshipPostDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public InternshipPostDto updateInternship(Long id, InternshipPostDto internshipPostDto) {
        InternshipPost internshipPost = internshipPostRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Internship not found"));

        modelMapper.map(internshipPostDto, internshipPost);

        InternshipPost updatedPost = internshipPostRepository.save(internshipPost);
        return modelMapper.map(updatedPost, InternshipPostDto.class);
    }

    @Override
    public void deleteInternship(Long id) {
        if (!internshipPostRepository.existsById(id)) {
            throw new RuntimeException("Internship not found");
        }
        internshipPostRepository.deleteById(id);
    }
}
