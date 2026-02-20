package nimblix.in.HealthCareHub.service;

public interface EmailService {

    void sendAppointmentReminder(String to, String doctorName, String dateTime);
    void sendEmergencyAlert(String to, String message);

 //   void sendSimpleMail(String mail, String s, String message);
    void sendSimpleMail(String to, String subject, String text);

    //void sendEmail(String to, String subject, String text);
}
