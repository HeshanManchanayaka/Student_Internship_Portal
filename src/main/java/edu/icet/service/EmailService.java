package edu.icet.service;

public interface EmailService {
    void sendSimpleMessage(String to, String subject, String text);
    void sendApplicationNotification(String to, String studentName, String internshipTitle);
    void sendStatusUpdateNotification(String to, String internshipTitle, String status);
}
