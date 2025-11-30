package Notification_App.NotificationMessageDecorator;

public class NotificationRegard implements NotificationMessage {
    
    private NotificationMessage notificationMessage;
    private String regard;

    public NotificationRegard(NotificationMessage notificationMessage, String regard) {
        this.notificationMessage = notificationMessage;
        this.regard = regard;
    }
    
    public String createNotificationMessage(String message) {
        return notificationMessage.createNotificationMessage(message) + '\n' + "<Regard>" + regard + "</Regard>";
    }
    
}
