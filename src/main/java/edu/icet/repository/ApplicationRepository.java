package edu.icet.repository;

import edu.icet.entities.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ApplicationRepository extends JpaRepository<Application , Long> {
    List<Application> findByStudentId(Long studentId);
    List<Application> findByInternshipPostId(Long internshipPostId);
    List<Application> findByInternshipPostCreatedById(Long companyId);
}
