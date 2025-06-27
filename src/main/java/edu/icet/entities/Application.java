package edu.icet.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Application {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private User student;

    @ManyToOne
    private InternshipPost post;

    private String status;
    private String resumeLink;
}
