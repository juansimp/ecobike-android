package mx.softux.ecobike;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.support.v4.content.Loader;
import android.support.v4.content.LocalBroadcastManager;

/**
 * Created by gianpa on 12/26/14.
 */
public abstract class ModelLoader<D> extends Loader<D> {
    private LocalBroadcastManager broadcastManager = null;
    protected ApiService apiService = null;

    public ModelLoader(ApiService apiService, Context context) {
        super(context);
        this.apiService = apiService;
    }

    public void registerReceiver(BroadcastReceiver receiver, String action) {
        broadcastManager.registerReceiver(receiver, new IntentFilter(action));
    }

    public void unregisterReceiver(BroadcastReceiver receiver) {
        broadcastManager.unregisterReceiver(receiver);
    }

    /**
     * Call this method before using broadcastManager
     */
    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        broadcastManager = LocalBroadcastManager.getInstance(getContext());
    }

    /**
     * Call after using broadcastManager
     */
    @Override
    protected void onReset() {
        super.onReset();
        broadcastManager = null;
    }

    protected void cancelRequest(Integer requestId) {
        if (apiService != null && requestId != null) {
            apiService.cancelRequest(requestId);
        }
    }
}
