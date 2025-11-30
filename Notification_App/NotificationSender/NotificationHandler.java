package Notification_App.NotificationSender;

import java.util.ArrayDeque;
import java.util.Deque;

public class NotificationHandler implements INotificationHandler{

    Deque<NotificationSendType> notificationSendTypes = new ArrayDeque<>();

    @Override
    public void addNotificationSendType(NotificationSendType notificationSendType){
        notificationSendTypes.addLast(notificationSendType);
    }
    @Override
    public void removeNotificationSendType(NotificationSendType notificationSendType){
        notificationSendTypes.remove(notificationSendType);
    }
    @Override
    public void sendNotificationOneByOne(){
        if(!notificationSendTypes.isEmpty()){
            NotificationSendType notificationSendType = notificationSendTypes.pollFirst();
            notificationSendType.sendNotification();
        }
    }
    @Override
    public void sendNotificationAllAtOnce(){
        while(!notificationSendTypes.isEmpty()){
            NotificationSendType notificationSendType = notificationSendTypes.pollFirst();
            notificationSendType.sendNotification();
            System.out.println("Sending next notification..................");
        }
    }

}