package com.example.mislugares2022.servicios;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;

public class Alarma {
    static AlarmManager managerAlarma;

    public static void crearAlarmaNotificaciones(Activity activity) {

        Intent intentReceiver = new Intent(activity, AlarmaReceiver.class);
        PendingIntent intentAlarma = PendingIntent.getBroadcast(activity, 0, intentReceiver, 0);

        managerAlarma = (AlarmManager) activity.getSystemService(Context.ALARM_SERVICE);
        managerAlarma.setRepeating(AlarmManager.RTC_WAKEUP,
                SystemClock.elapsedRealtime() + 10000, 30 * 1000, intentAlarma);

    }

    public static void pararAlarmaServicio() {

    }

    public static void cancelarNotificaciones(Activity act) {

    }
}
