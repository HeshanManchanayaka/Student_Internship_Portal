package edu.icet.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import edu.icet.enums.ApplicationStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationDto {
    private Long id;
    private String resumeLink;
    private ApplicationStatus status;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "UTC")
    private LocalDateTime appliedAt;

    private Long studentId;
    private String studentName;
    private String studentEmail;
    private Long internshipPostId;
    private String internshipTitle;
    private String companyName;
}
