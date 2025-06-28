package edu.icet.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class InternshipPostDto {
    private Long id;
    private String title;
    private String description;
    private String location;
    private String duration;
    private String requirements;
    private String salary;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "UTC")
    private Date applicationDeadline;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "UTC")
    private LocalDateTime createdAt;

    private Long createdById;
    private String createdByName;
    private String companyName;
}
