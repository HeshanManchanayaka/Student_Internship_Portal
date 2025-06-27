package edu.icet.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class InternshipPost {
    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private String description;
    private String location;
    private String duration;

    @ManyToOne
    private User createdBy;
}


