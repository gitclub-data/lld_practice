package Notification_App.NotificationSender;

public class PushNotificationMsgSender implements NotificationSendType {

    private String message;
    private String deviceToken;
    private String title;


    public PushNotificationMsgSender(String message, String deviceToken, String title) {
        this.message = message;
        this.deviceToken = deviceToken;
        this.title = title;
    }

    public boolean sendNotification() {
        // Logic to send push notification using FCM or any other service
        System.out.println("Sending Push Notification:");
        System.out.println("Title: " + title);
        System.out.println("Message: " + message);
        System.out.println("Device Token: " + deviceToken);
        return true; // Return true if the notification was sent successfully
    }
    
}
