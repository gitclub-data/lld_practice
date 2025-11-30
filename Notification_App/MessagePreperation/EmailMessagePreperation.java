package Notification_App.MessagePreperation;

import Notification_App.NotificationMessageDecorator.NotificationBody;
import Notification_App.NotificationMessageDecorator.NotificationHeader;
import Notification_App.NotificationMessageDecorator.NotificationMessage;
import Notification_App.NotificationMessageDecorator.NotificationRegard;

public class EmailMessagePreperation implements MessagePreperationStrategy {
      
    private String header;
    private String body;
    private String regards;
    
    public EmailMessagePreperation(String header, String body, String regards) {
        this.header = header;
        this.body = body;
        this.regards = regards;
    } 

    @Override
    public String prepareMessage() {
        NotificationMessage notificationMessage = new NotificationRegard(new NotificationHeader(new NotificationBody(), header), regards); 
        return notificationMessage.createNotificationMessage(body);
    }

}
