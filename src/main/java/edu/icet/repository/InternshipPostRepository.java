package edu.icet.repository;

import edu.icet.entities.InternshipPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InternshipPostRepository extends JpaRepository<InternshipPost, Long> {
    List<InternshipPost> findByTitleAndLocation(String title, String location);

    List<InternshipPost> findByCreatedById(Long companyId);
}
