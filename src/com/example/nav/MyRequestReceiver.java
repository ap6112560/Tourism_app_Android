package com.example.nav;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
public class MyRequestReceiver extends BroadcastReceiver{
	 public static final String PROCESS_RESPONSE = "com.example.nav.intent.action.PROCESS_RESPONSE";

	@Override
	public void onReceive(Context context, Intent intent) {
		NotificationManager n=(NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
		Bundle b=intent.getExtras();
		PendingIntent p=PendingIntent.getActivity(context, -1, new Intent(Intent.ACTION_VIEW).setData(Uri.parse((String)b.get("link"))), PendingIntent.FLAG_ONE_SHOT);
		Notification notification=new Notification.Builder(context).setContentTitle("New Version Available").setContentText("Version:"+(String)b.get("version")).setSmallIcon(R.drawable.ic_launcher).setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)).setContentIntent(p).setAutoCancel(true).getNotification();
		n.notify(1,notification);
	}
}