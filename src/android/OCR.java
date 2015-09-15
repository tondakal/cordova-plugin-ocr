package cz.zeus_solutions.cordova.ocr;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import edu.sfsu.cs.orange.ocr.CaptureActivity;
import android.content.Context;
import android.content.Intent;

public class OCR extends CordovaPlugin {


    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext)
            throws JSONException {

        if (action.equals("sayHello")){
            Context context =  cordova.getActivity().getApplicationContext();
            //Intent intent = new Intent(context,CaptureActivity.class);

            //cordova.startActivityForResult(this, intent,0);
            try {
                String responseText = "Hello world, " + args.getString(0);
                callbackContext.success(responseText);
            } catch (JSONException e){
                callbackContext.error("Failed to parse parameters");
            }
            return true;
        }

        return false;
        
    }

}