package DesignPatterns.CreationalPattern.FactoryPattern.NotificationFactory;


class EmailNotification implements Notification {
    @Override
    public void sendNotification() {
        System.out.println("Sending Email Notification");
    }
}

class PushNotification implements Notification {
    @Override
    public void sendNotification() {
        System.out.println("Sending Push Notification");
    }
}

class SMSNotification implements Notification {
    @Override
    public void sendNotification() {
        System.out.println("Sending SMS Notification");
    }
}

public class NotificationFactory {
    public NotificationFactory() {
    }

    public static Notification createNotification(ChannelType channelType) {
        switch (channelType) {
            case EMAIL:
                return new EmailNotification();
            case PUSH:
                return new PushNotification();
            case SMS:
                return new SMSNotification();
            default:
                throw new IllegalArgumentException("Invalid channel type");
        }
    }
}

