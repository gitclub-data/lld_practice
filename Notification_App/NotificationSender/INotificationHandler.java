package Notification_App.NotificationSender;

public interface INotificationHandler {
    public void addNotificationSendType(NotificationSendType notificationSendType);
    public void removeNotificationSendType(NotificationSendType notificationSendType);
    public void sendNotificationOneByOne();
    public void sendNotificationAllAtOnce();
}
