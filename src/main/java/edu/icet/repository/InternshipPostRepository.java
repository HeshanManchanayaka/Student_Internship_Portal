package edu.icet.repository;

import edu.icet.entities.InternshipPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InternshipPostRepository extends JpaRepository<InternshipPost, Long> {
}
