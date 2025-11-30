package Notification_App.MessagePreperation;

import Notification_App.NotificationMessageDecorator.NotificationBody;
import Notification_App.NotificationMessageDecorator.NotificationHeader;
import Notification_App.NotificationMessageDecorator.NotificationMessage;

public class PushNotificationPreperation implements MessagePreperationStrategy {
    
    private String header;
    private String body;
    
    public PushNotificationPreperation(String header, String body) {
        this.header = header;
        this.body = body;
    } 

    @Override
    public String prepareMessage() {
        NotificationMessage notificationMessage = new NotificationHeader(new NotificationBody(), header);
        return notificationMessage.createNotificationMessage(body);
    }
    
}
