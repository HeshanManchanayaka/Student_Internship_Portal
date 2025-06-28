package edu.icet.entities;

import edu.icet.enums.ApplicationStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name= "Application")
@NoArgsConstructor
@AllArgsConstructor
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "Student_id")
    private User student;

    @Enumerated(EnumType.STRING)
    private ApplicationStatus status;
    private String resumeLink;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime appliedAt;

    @ManyToOne
    @JoinColumn(name = "internship_post_id")
    private InternshipPost internshipPost;

    @PrePersist
    protected void onCreate() {
        appliedAt = LocalDateTime.now();
        if (status == null) {
            status = ApplicationStatus.PENDING;
        }
    }
}
