# Push-Notification-Reciever
### 푸시 알림 수신기 안드로이드 앱

#### FCM(Firebase Cloud Messaging)을 통해 보낸 메세지를 수신하는 안드로이드 앱 입니다.
### Blog
* <https://hwayomingdlog.tistory.com/228>
* <https://hwayomingdlog.tistory.com/232>
</br>


#### ⚠️ 주의사항
Firebase 프로젝트에서 다운받은 google-services.json 파일을 추가해야 합니다.
</br>


## 주기능
### 알림 수신
* 푸시 알림을 수신하여 상태표시줄 Status Bar에 알림을 표시합니다.
* 알림의 제목과 내용을 알림 창에서 확인할 수 있습니다.
* Normal, Expandable, Custom 세 가지 타입의 알림을 수신합니다.
* 알림 타입 별로 알림을 구분하여 앱 화면에 표시합니다.
* 사용자가 알림을 클릭하였을 때, 앱이 백그라운드 상태인 경우에는 앱을 실행시키고, 앱이 포그라운드 상태인 경우에는 앱 화면을 갱신합니다.
</br>

## 활용 기술
* FCM - Firebase Cloud Messaging을 통해 안드로이드 앱에 메세지를 전송합니다.
* FirebaseMessagingService - FirebaseMessagingService를 통해 메세지를 수신하여 세 가지 타입별로 Notification을 빌드하였습니다.
* PendingIntent - PendingIntent를 사용하여 사용자가 알림을 클릭하였을 때 다른 프로세스에서 해당 앱의 작업을 실행할 수 있도록 하였습니다.
* NotificationChannal - Android 8.0 이상의 기기에서 알림을 채널에 할당하기 위해 NotificationChannal을 통해 채널을 생성하였습니다.
</br>

***
<img src="/img/img0.png" width="300px" height="600px" title="" alt=""></img>
<img src="/img/img1.png" width="300px" height="600px" title="" alt=""></img>
<img src="/img/img2.png" width="300px" height="600px" title="" alt=""></img>
<img src="/img/img3.png" width="300px" height="600px" title="" alt=""></img>
<img src="/img/img4.png" width="300px" height="600px" title="" alt=""></img>
<img src="/img/img5.png" width="300px" height="600px" title="" alt=""></img>
