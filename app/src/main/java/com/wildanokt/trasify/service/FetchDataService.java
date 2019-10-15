package com.wildanokt.trasify.service;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.wildanokt.trasify.R;
import com.wildanokt.trasify.adapter.TrashAdapter;
import com.wildanokt.trasify.model.Trash;


public class FetchDataService extends Service {

    private static final String CHANNEL_ID = "channel_id";
    private final LocalBinder mBinder = new LocalBinder();
    private int NOTIFICATION_ID = 101;

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    public class LocalBinder extends Binder {
        public FetchDataService getService() {
            return FetchDataService.this;
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
                databaseReference.child("Data").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot ds: dataSnapshot.getChildren()) {
                            Trash mTrash = ds.getValue(Trash.class);
                            if(mTrash.getAnorganik() > 60){
                                NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID)
                                        .setSmallIcon(R.mipmap.ic_logo)
                                        .setContentTitle(mTrash.getLokasi())
                                        .setContentText("Sampah anorganik hampir penuh")
                                        .setPriority(NotificationCompat.PRIORITY_DEFAULT);
                                Notification notification = builder.build();
                                NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(getApplicationContext());
                                notificationManagerCompat.notify(NOTIFICATION_ID, notification);
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        // Failed to read value
                        Log.w("gagal", "Failed to read value.", databaseError.toException());
                    }
                });
            }
        });
        return Service.START_STICKY;
    }
}
