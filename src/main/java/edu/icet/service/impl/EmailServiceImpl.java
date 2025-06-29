package edu.icet.service.impl;

import edu.icet.service.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender emailSender;
    private ModelMapper modelMapper;

    @Override
    public void sendSimpleMessage(String to, String subject, String text) {
        if (emailSender == null) {
            log.error("Email service not configured. Would send: {} to {}", subject, to);
            return;
        }

        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(System.getProperty("EMAIL_USERNAME"));
            message.setTo(to);
            message.setSubject(subject);
            message.setText(text);
            emailSender.send(message);
        } catch (Exception e) {
            log.error("Failed to send email: {}", e.getMessage());
        }
    }

    @Override
    public void sendApplicationNotification(String to, String studentName, String internshipTitle) {
        String subject = "New Application Received - " + internshipTitle;
        String text = String.format(
                "Dear Recruiter,\n\n" +
                        "You have received a new application from %s for the internship position titled \"%s\".\n\n" +
                        "Please log in to your dashboard to view the applicant's details and take appropriate action.\n\n" +
                        "Thank you for using the Internship Portal.\n\n" +
                        "Best regards,\n" +
                        "The Internship Portal Team",
                studentName, internshipTitle
        );
        sendSimpleMessage(to, subject, text);
    }

    @Override
    public void sendStatusUpdateNotification(String to, String internshipTitle, String status) {
        String subject = "Application Status Update - " + internshipTitle;
        String text = String.format(
                "Dear Student,\n\n" +
                        "Your application for the internship position \"%s\" has been successfully updated.\n\n" +
                        "Updated Status: %s\n\n" +
                        "You can view more details by logging into your student dashboard.\n\n" +
                        "Thank you for using the Internship Portal.\n\n" +
                        "Best regards,\n" +
                        "The Internship Portal Team"
                ,
                internshipTitle, status
        );
        sendSimpleMessage(to, subject, text);
    }
}
