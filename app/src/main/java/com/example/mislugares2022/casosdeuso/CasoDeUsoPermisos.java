package com.example.mislugares2022.casosdeuso;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.pm.PackageManager;

import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;

import com.example.mislugares2022.presentacion.MainActivity;
import com.example.mislugares2022.presentacion.VistaLugarActivity;

public class CasoDeUsoPermisos {
    /*
    public static int SOLICITAR_PERMISO_ALMACENAMIENTO = 2;
    String permisos[] = {
            Manifest.permission.CAMERA,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.MANAGE_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE};


    public static void solicitarPermiso(Activity actividad, final String permiso[],
                                        String justificacion,
                                        final int requestCode){
        for(String perm : permiso) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this, perm)) {
                new AlertDialog.Builder(this)
                        .setTitle("Solicitud de permiso")
                        .setMessage(justificacion)
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                ActivityCompat.requestPermissions(VistaLugarActivity.this,
                                        new String[]{perm}, requestCode);
                            }
                        })
                        .show();
            } else {
                new AlertDialog.Builder(this)
                        .setTitle("Solicitud de permiso")
                        .setMessage(justificacion)
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                ActivityCompat.requestPermissions(VistaLugarActivity.this,
                                        new String[]{perm}, requestCode);
                            }
                        })
                        .show();
                // Toast.makeText(SMSActivity.this,"Me dijiste que no al permiso, concedelo a mano" +
                //       "si quieres que funcione la aplicacion",Toast.LENGTH_LONG);
                //ActivityCompat.requestPermissions(this,
                //     new String[]{perm}, requestCode);
            }

        }
    }
    public static void solicitarPermisoAlmacenamiento(Activity actividad){

    }
    public static boolean hayPermisoAlmacenamiento(Activity actividad){


        return (ActivityCompat.checkSelfPermission(this, Manifest.permission.MANAGE_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED);
    }
*/
}
