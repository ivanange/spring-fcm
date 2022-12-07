import { Component, OnInit } from '@angular/core';
import { AngularFireMessaging } from "@angular/fire/messaging";
import { HttpClient } from "@angular/common/http";
import { Message } from "./models/message";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {
  title = 'fcm-demo-front';
  messages: Array<Message> = [];

  constructor(private msg: AngularFireMessaging, private http: HttpClient) { }

  ngOnInit() {

    this.msg.requestToken.subscribe(token => {

      console.log(token);

      this.http.post('http://localhost:8080/notification/token', {
        token: token,
        title: 'hello world',
        message: 'First notification, kinda nervous',
      }).subscribe(() => { });

      this.http.post<Message>('http://localhost:8080/notification/subscribe', {
        topic: 'common',
        subscriber: token
      }).subscribe((message) => {
        this.messages.push(message);
      });

    }, error => {

      console.log(error);

    });

  }


}
