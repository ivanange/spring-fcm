package net.mestwin.fcmpushnotifications.model;

public class SubscriptionRequest {

    private String subscriber;
    private String topic;

    public SubscriptionRequest() {
    }

    public SubscriptionRequest(String subscriber, String topicName) {
        this.subscriber = subscriber;
        this.topic = topicName;
    }

    public String getSubscriber() {
        return subscriber;
    }

    public void setSubscriber(String subscriber) {
        this.subscriber = subscriber;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }
}
