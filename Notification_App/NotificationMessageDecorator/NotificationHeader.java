package Notification_App.NotificationMessageDecorator;

public class NotificationHeader implements NotificationMessage {
    
    private NotificationMessage notificationMessage;
    private String header;

    public NotificationHeader(NotificationMessage notificationMessage, String header) {
        this.notificationMessage = notificationMessage;
        this.header = header;
    }
    
    public String createNotificationMessage(String message) {
        return "<Header>" + header + "</Header>" + '\n' + notificationMessage.createNotificationMessage(message);
    }
}
