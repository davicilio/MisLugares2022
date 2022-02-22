package com.example.mislugares2022.casosdeuso;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;

import androidx.core.app.ActivityCompat;

public class CasoDeUsoPermisos {
    private Activity actividad;
    private static Context context;

    public CasoDeUsoPermisos(Activity actividad, Context context) {
        this.actividad = actividad;
        this.context = context;
    }

    public static void solicitarPermiso(Activity actividad, final String permiso[],
                                        String justificacion,
                                        final int requestCode) {
        for (String perm : permiso) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(actividad, perm)) {
//                new AlertDialog.Builder(context)
//                        .setTitle("Solicitud de permiso")
//                        .setMessage(justificacion)
//                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int whichButton) {
//                                ActivityCompat.requestPermissions(actividad,
//                                        new String[]{perm}, requestCode);
//                            }
//                        })
//                        .show();
                ActivityCompat.requestPermissions(actividad,
                        new String[]{perm}, requestCode);
            } else {
//                new AlertDialog.Builder(context)
//                        .setTitle("Solicitud de permiso")
//                        .setMessage(justificacion)
//                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int whichButton) {
//                                ActivityCompat.requestPermissions(actividad,
//                                        new String[]{perm}, requestCode);
//                            }
//                        })
//                        .show();

                ActivityCompat.requestPermissions(actividad,
                        new String[]{perm}, requestCode);
                // Toast.makeText(SMSActivity.this,"Me dijiste que no al permiso, concedelo a mano" +
                //       "si quieres que funcione la aplicacion",Toast.LENGTH_LONG);
                //ActivityCompat.requestPermissions(this,
                //     new String[]{perm}, requestCode);
            }

        }
    }

    public static void solicitarPermisoAlmacenamiento(Activity actividad) {

    }

    public boolean hayPermisoAlmacenamiento(Activity actividad) {
        return (ActivityCompat.checkSelfPermission(context, Manifest.permission.MANAGE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED);
    }

    public boolean hayPermisoCamara(Activity actividad) {
        return (ActivityCompat.checkSelfPermission(context, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED);
    }

    public boolean hayPermisoUbicacion(Activity actividad) {
        return (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED);
    }



    /*
    * public static int SOLICITAR_PERMISO_ALMACENAMIENTO = 2;
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
    public boolean hayPermisoAlmacenamiento(Activity actividad){
        return (ActivityCompat.checkSelfPermission(this, Manifest.permission.MANAGE_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED);
    }
    * */

}
