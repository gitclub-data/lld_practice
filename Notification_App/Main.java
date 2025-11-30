package Notification_App;

import Notification_App.MessagePreperation.EmailMessagePreperation;
import Notification_App.MessagePreperation.MessagePreperationStrategy;
import Notification_App.MessagePreperation.PushNotificationPreperation;
import Notification_App.NotificationSender.EmailNotificationMsgSender;
import Notification_App.NotificationSender.INotificationHandler;
import Notification_App.NotificationSender.NotificationHandler;
import Notification_App.NotificationSender.PushNotificationMsgSender;

public class Main {

    public static void main(String args[]){

        INotificationHandler notificationHandler = new NotificationHandler();

        // prpare notification message

        // email msg preperation
        MessagePreperationStrategy emailmsg1 = new EmailMessagePreperation("Welcome User", "This is to notify you about your account activity.", "Best Regards, Team");
        String notificationMessage = emailmsg1.prepareMessage();
        System.out.println("Email Notification Message: \n" + notificationMessage);

        System.out.println("--------------------------------------------------");

        // create email notification sender
        EmailNotificationMsgSender emailNotification = new EmailNotificationMsgSender("smtp.example.com", 587, "gauravpandey", notificationMessage);
        notificationHandler.addNotificationSendType(emailNotification);

        System.out.println("--------------------------------------------------");

        // prepare another email msg
        MessagePreperationStrategy emailmsg2 = new EmailMessagePreperation("Alert!", "Suspicious activity detected on your account.", "Security Team");
        notificationMessage = emailmsg2.prepareMessage();
        System.out.println("Email Notification Message: \n" + notificationMessage);

        System.out.println("--------------------------------------------------");

        EmailNotificationMsgSender emailNotification2 = new EmailNotificationMsgSender("smtp.example.com", 587, "gauravpandey", notificationMessage);
        notificationHandler.addNotificationSendType(emailNotification2);

        System.out.println("--------------------------------------------------");

        // prepare push notification msg
        MessagePreperationStrategy pushmsg1 = new PushNotificationPreperation("New Message", "You have received a new message in your inbox.");
        notificationMessage = pushmsg1.prepareMessage();
        System.out.println("Push Notification Message: \n" + notificationMessage);

        System.out.println("--------------------------------------------------");

        PushNotificationMsgSender pushNotification = new PushNotificationMsgSender(notificationMessage, "device_token_123", "New Message Alert");
        notificationHandler.addNotificationSendType(pushNotification);

        System.out.println("--------------------------------------------------");

        // prepere another push notification msg
        MessagePreperationStrategy pushmsg2 = new PushNotificationPreperation("Update Available", "A new update is available for your application.");
        notificationMessage = pushmsg2.prepareMessage();
        System.out.println("Push Notification Message: \n" + notificationMessage);

        System.out.println("--------------------------------------------------");

        PushNotificationMsgSender pushNotification2 = new PushNotificationMsgSender(notificationMessage, "device_token_456", "Update Alert");
        notificationHandler.addNotificationSendType(pushNotification2);

        System.out.println("--------------------------------------------------");

        // send notifications one by one
        System.out.println("Sending Notifications One by One:");
        notificationHandler.sendNotificationOneByOne();

        System.out.println("--------------------------------------------------");

        // send all notifications together
        System.out.println("Sending All Notifications At Once:");
        notificationHandler.sendNotificationAllAtOnce(); 

        System.out.println("--------------------------------------------------");
        
    }
    
}
