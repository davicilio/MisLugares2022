package com.example.mislugares2022.servicios;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

public class ServicioNotif extends Service {

    private NotificationManager notificationManager;
    static final String CANAL_ID = "mi_canal";
    static final int NOTIFICATION_ID = 1;

    @Override
    public void onCreate() {
        super.onCreate();

    }

    public int onStartCommand(Intent intent, int flags, int idArranque) {
        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        //  NotificationChannel notificationChannel = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(
                    CANAL_ID, "Nombre", NotificationManager.IMPORTANCE_DEFAULT);
            notificationChannel.setDescription("Esta es la descripción");
            notificationManager.createNotificationChannel(notificationChannel);
        }
        NotificationCompat.Builder notificacion = new NotificationCompat.Builder(this, CANAL_ID)
                .setContentTitle("titulo")
                .setContentText("mucho texto");
        notificationManager.notify(NOTIFICATION_ID, notificacion.build());

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
                new Intent(this, AlarmaReceiver.class), 0);
        notificacion.setContentIntent(pendingIntent);

        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
