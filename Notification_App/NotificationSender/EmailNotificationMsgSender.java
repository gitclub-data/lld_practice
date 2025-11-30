package Notification_App.NotificationSender;

public class EmailNotificationMsgSender implements NotificationSendType {

    private String smtpServer;
    private int port;
    private String username;
    private String message;

    public EmailNotificationMsgSender(String smtpServer, int port, String username, String message) {
        this.smtpServer = smtpServer;
        this.port = port;
        this.username = username;
        this.message = message;
    }

    public boolean sendNotification() {
        // Logic to send email notification using SMTP server
        System.out.println("Sending Email Notification:");
        System.out.println("SMTP Server: " + smtpServer);
        System.out.println("Port: " + port);
        System.out.println("Username: " + username);
        System.out.println("Message: " + message);
        // Assume the email is sent successfully
        return true;
    }
    
}
