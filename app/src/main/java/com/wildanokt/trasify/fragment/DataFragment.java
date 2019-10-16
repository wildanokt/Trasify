package com.wildanokt.trasify.fragment;


import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.wildanokt.trasify.R;
import com.wildanokt.trasify.adapter.TrashAdapter;
import com.wildanokt.trasify.model.Trash;
import com.wildanokt.trasify.notification.notificationItem;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class DataFragment extends Fragment {

    private RecyclerView rvTrash;
    private ProgressBar pgLoad;
    private List<Trash> mTrashes;

    private int idNotification = 0;
    private final List<notificationItem> stackNotif = new ArrayList<>();

    private static final CharSequence CHANNEL_NAME = "trasify channel";
    private final static String GROUP_KEY_EMAILS = "group_key_emails";
    private final static int NOTIFICATION_REQUEST_CODE = 200;
    private static final int MAX_NOTIFICATION = 10;

    public DataFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_data, container, false);
        pgLoad = view.findViewById(R.id.pg_load);
        pgLoad.setVisibility(View.VISIBLE);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rvTrash = view.findViewById(R.id.rv_trash);
        rvTrash.setHasFixedSize(true);

        mTrashes = new ArrayList<>();

        rvTrash.setLayoutManager(new LinearLayoutManager(getContext()));

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child("Data").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mTrashes.clear();
                for (DataSnapshot ds: dataSnapshot.getChildren()) {
                    Trash mTrash = ds.getValue(Trash.class);
                    mTrashes.add(mTrash);
                    if(mTrash.getAnorganik() > 80){
                        setNotification(mTrash.getLokasi(), "Sampah Anorganik penuh");
                    }
                    if(mTrash.getOrganik() > 80){
                        setNotification(mTrash.getLokasi(), "Sampah Organik penuh");
                    }
                    if(mTrash.getLogam() > 80){
                        setNotification(mTrash.getLokasi(), "Sampah logam penuh");
                    }
                }
                TrashAdapter adapter = new TrashAdapter(getContext(), mTrashes);
                rvTrash.setAdapter(adapter);
                pgLoad.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Failed to read value
                Log.w("gagal", "Failed to read value.", databaseError.toException());
            }
        });
    }

    private void setNotification(String title, String content){
        stackNotif.add(new notificationItem(idNotification, title, content));
        NotificationManager mNotificationManager = (NotificationManager) getContext().getSystemService(Context.NOTIFICATION_SERVICE);
        Bitmap largeIcon = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_logo);
        NotificationCompat.Builder mBuilder;

        String CHANNEL_ID = "channel_01";
        if (idNotification < MAX_NOTIFICATION) {
            mBuilder = new NotificationCompat.Builder(getContext(), CHANNEL_ID)
                    .setContentTitle("Node " + stackNotif.get(idNotification).getTitle())
                    .setContentText(stackNotif.get(idNotification).getMessage())
                    .setSmallIcon(R.mipmap.ic_logo)
                    .setLargeIcon(largeIcon)
                    .setGroup(GROUP_KEY_EMAILS)
                    .setAutoCancel(true);
        } else {
            NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle()
                    .addLine("Node " + stackNotif.get(idNotification).getTitle())
                    .addLine("Node " + stackNotif.get(idNotification - 1).getTitle())
                    .setBigContentTitle(idNotification + " new notification")
                    .setSummaryText("mail@dicoding");
            mBuilder = new NotificationCompat.Builder(getContext(), CHANNEL_ID)
                    .setContentTitle(idNotification + " new emails")
                    .setContentText("mail@dicoding.com")
                    .setSmallIcon(R.mipmap.ic_logo)
                    .setGroup(GROUP_KEY_EMAILS)
                    .setGroupSummary(true)
                    .setStyle(inboxStyle)
                    .setAutoCancel(true);
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            /* Create or update. */
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID,
                    CHANNEL_NAME,
                    NotificationManager.IMPORTANCE_DEFAULT);

            mBuilder.setChannelId(CHANNEL_ID);

            if (mNotificationManager != null) {
                mNotificationManager.createNotificationChannel(channel);
            }
        }

        Notification notification = mBuilder.build();

        if (mNotificationManager != null) {
            mNotificationManager.notify(idNotification, notification);
        }
        idNotification++;
    }
}
