package net.mestwin.fcmpushnotifications.controller;

import net.mestwin.fcmpushnotifications.model.PushNotificationRequest;
import net.mestwin.fcmpushnotifications.model.PushNotificationResponse;
import net.mestwin.fcmpushnotifications.model.SubscriptionRequest;
import net.mestwin.fcmpushnotifications.service.PushNotificationService;

import java.util.Arrays;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;

@CrossOrigin
@RestController
public class PushNotificationController {

    private PushNotificationService pushNotificationService;

    public PushNotificationController(PushNotificationService pushNotificationService) {
        this.pushNotificationService = pushNotificationService;
    }

    @PostMapping("/notification/topic")
    public ResponseEntity sendNotification(@RequestBody PushNotificationRequest request) {
        pushNotificationService.sendPushNotificationWithoutData(request);
        return new ResponseEntity<>(new PushNotificationResponse(HttpStatus.OK.value(), "Notification has been sent."), HttpStatus.OK);
    }

    @PostMapping("/notification/token")
    public ResponseEntity sendTokenNotification(@RequestBody PushNotificationRequest request) {
        pushNotificationService.sendPushNotificationToToken(request);
        return new ResponseEntity<>(new PushNotificationResponse(HttpStatus.OK.value(), "Notification has been sent."), HttpStatus.OK);
    }

    @PostMapping("/notification/data")
    public ResponseEntity sendDataNotification(@RequestBody PushNotificationRequest request) {
        pushNotificationService.sendPushNotification(request);
        return new ResponseEntity<>(new PushNotificationResponse(HttpStatus.OK.value(), "Notification has been sent."), HttpStatus.OK);
    }

    @GetMapping("/notification")
    public ResponseEntity sendSampleNotification() {
        pushNotificationService.sendSamplePushNotification();
        return new ResponseEntity<>(new PushNotificationResponse(HttpStatus.OK.value(), "Notification has been sent."), HttpStatus.OK);
    }
    
    @PostMapping("/notification/subscribe")
    public ResponseEntity subscribeToTopic(@RequestBody SubscriptionRequest request) throws FirebaseMessagingException{
    	FirebaseMessaging.getInstance().subscribeToTopic(Arrays.asList(request.getSubscriber()), request.getTopic());
    	return new ResponseEntity<>(new PushNotificationResponse(HttpStatus.OK.value(), "Subscribed succesfully."), HttpStatus.OK);
    }
}
