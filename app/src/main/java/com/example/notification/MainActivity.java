package com.example.notification;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.databinding.DataBindingUtil;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.notification.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding activityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);

        activityMainBinding= DataBindingUtil.setContentView(this,R.layout.activity_main);

        activityMainBinding.buttonPushNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                try {
//                    String message="this is notification example";
//                    NotificationCompat.Builder notificationBuilder=new NotificationCompat.Builder(MainActivity.this,"test_notification")
//                            .setSmallIcon(R.drawable.ic_launcher_background)
//                            .setContentTitle("New notification")
//                            .setContentText(message)
//                            .setAutoCancel(true);
//
//                    Intent intent=new Intent(MainActivity.this, NotificationActivity.class);
//                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                    intent.putExtra("message",message);
//
//                    PendingIntent pendingIntent=PendingIntent.getActivity(MainActivity.this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
//                    notificationBuilder.setContentIntent(pendingIntent);
//                    NotificationManager notificationManager= (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//                    notificationManager.notify(0,notificationBuilder.build());
//
//                }catch (Exception e){
//                    Log.e("Error==>",e.getMessage());
//                }

                Intent intent = new Intent(MainActivity.this, NotificationActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this, 0 /* Request code */, intent,
                        PendingIntent.FLAG_ONE_SHOT);

                String channelId = "fcm_default_channel";
                Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                NotificationCompat.Builder notificationBuilder =
                        new NotificationCompat.Builder(MainActivity.this, channelId)
                                .setSmallIcon(R.drawable.ic_launcher_background)
                                .setContentTitle("Just try")
                                .setContentText("message")
                                .setAutoCancel(true)
                                .setSound(defaultSoundUri)
                                .setContentIntent(pendingIntent);

                NotificationManager notificationManager =
                        (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

                // Since android Oreo notification channel is needed.
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    NotificationChannel channel = new NotificationChannel(channelId,
                            "Channel human readable title",
                            NotificationManager.IMPORTANCE_HIGH);
                    notificationManager.createNotificationChannel(channel);
                }

                notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());
            }

            });

    }
}