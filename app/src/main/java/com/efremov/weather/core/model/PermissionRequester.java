package com.efremov.weather.core.model;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.efremov.weather.core.model.app.App;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

public class PermissionRequester {

    private static PermissionRequester instance;

    public static synchronized PermissionRequester getInstance() {
        instance = instance == null ? new PermissionRequester() : instance;
        return instance;
    }

    private final Map<String, WeakReference<OnResultListener>> pendingListeners = new HashMap<>();

    private PermissionRequester() {
        LocalBroadcastManager.getInstance(App.getInstance()).registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String action = intent != null ? intent.getAction() : "";
                if ("ACTION_PERMISSION_REQUEST_RESPONSE".equals(action)) {
                    callbackListener(intent.getStringExtra("LISTENER"), intent.getIntExtra("RESULT", -1));
                }
            }
        }, new IntentFilter("ACTION_PERMISSION_REQUEST_RESPONSE"));
    }

    public void requestPermission(@NonNull OnResultListener listener, @NonNull String permission) {
        pendingListeners.put(listener.getClass().getName() + listener.hashCode(), new WeakReference<>(listener));

        Intent requestPermissionIntent = new Intent("ACTION_REQUEST_PERMISSION");
        requestPermissionIntent.putExtra("PERMISSION", permission);
        LocalBroadcastManager.getInstance(App.getInstance().getApplicationContext()).sendBroadcast(requestPermissionIntent);
    }

    private void callbackListener(String listener, int result) {
        WeakReference<OnResultListener> listenerReference = pendingListeners.get(listener);
        OnResultListener onResultListener = listenerReference != null ? listenerReference.get() : null;
        if (onResultListener != null) {
            pendingListeners.remove(listenerReference);
            if (result == 1) {
                onResultListener.onPermissionGranted();
            } else if (result == 0) {
                onResultListener.onPermissionDenied();
            }
        }
    }

    public interface OnResultListener {
        void onPermissionGranted();
        void onPermissionDenied();
    }
}
