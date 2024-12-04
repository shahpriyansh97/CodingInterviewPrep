package DesignPatterns.CreationalPattern.FactoryPattern.NotificationFactory;


public class NotificationService {
    public static void main(String[] args) {
        Notification emailNotification = NotificationFactory.createNotification(ChannelType.EMAIL);
        Notification pushNotification = NotificationFactory.createNotification(ChannelType.PUSH);
        Notification smsNotification = NotificationFactory.createNotification(ChannelType.SMS);

        emailNotification.sendNotification();
        pushNotification.sendNotification();
        smsNotification.sendNotification();
    }
}