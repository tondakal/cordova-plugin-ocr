package cz.zeus_solutions.cordova.ocr;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import edu.sfsu.cs.orange.ocr.CaptureActivity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.Iterator;

public class OCR extends CordovaPlugin {
	private static final int RESULT_OK=-1;
    private static final int OCR_ACTIVITY=200;
 protected CallbackContext callbackContext;
    public static final String TAG="cz.zeus_solutions.ocr";

public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == OCR_ACTIVITY) {
            if (resultCode == RESULT_OK) {
                String returnedResult = data.getData().toString();
		this.callbackContext.success(returnedResult);
            }
        }
	return;
    }

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext)
            throws JSONException {
	this.callbackContext = callbackContext;
	if (action.equals("reading")){
		Context context=this.cordova.getActivity().getApplicationContext();
		Intent intent = new Intent(context, CaptureActivity.class);

                if (this.cordova != null) {
                    JSONObject config = args.getJSONObject(0);
                    Iterator<?> keys = config.keys();
                    while( keys.hasNext() ) {
                        String key = (String)keys.next();
                        Object obj = config.get(key);

                        if (obj instanceof Integer) {
                            Integer val =(Integer)obj;
                            intent.putExtra(key, val);
                        }else if (obj instanceof Boolean) {
                            Boolean val =(Boolean)obj;
                            intent.putExtra(key, val);
                        }else {
                            String val =(String)obj;
                            intent.putExtra(key, val);
                        }
                    }
                    this.cordova.startActivityForResult((CordovaPlugin) this, intent, OCR_ACTIVITY);
                }
        PluginResult r = new PluginResult(PluginResult.Status.NO_RESULT);
        r.setKeepCallback(true);
        callbackContext.sendPluginResult(r);

        return true;
	}
        return false;

    }

}

