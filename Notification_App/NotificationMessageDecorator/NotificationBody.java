package Notification_App.NotificationMessageDecorator;

public class NotificationBody implements NotificationMessage {
    public String createNotificationMessage(String message) {
        return "<Body>" + message + "</Body>";
    }
}
