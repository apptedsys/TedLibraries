package in.tedsys.tedutils;

import android.content.Context;
import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

public class TedRequest {

    public static final int JSON_PARSE_ERROR = 300;
    public static final int NO_DATA_FOUND = 400;
    public static final int SERVER_ERROR = 500;
    public static final int NETWORK_ERROR = 600;

    private static TedRequest sSoleInstance;

    private static Context context = null;
    private static String path = null;
    private static JSONObject params = null;
    private static ResponseListener listener;
    private static int request_code;
    private static int MY_SOCKET_TIMEOUT_MS = -1;
    private static String returnType = null;

    private TedRequest() {
    }  //private constructor.

    public static TedRequest get(Context context) {
        if (sSoleInstance == null) { //if there is no instance available... create new one
            sSoleInstance = new TedRequest();
        }
        sSoleInstance.context = context;
        return sSoleInstance;
    }

    public static TedRequest url(String Path) {
        sSoleInstance.path = Path;
        return sSoleInstance;
    }

    public static TedRequest params(String Inputs) {
        try {
            sSoleInstance.params = new JSONObject(Inputs);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return sSoleInstance;
    }

    public static TedRequest params(Object o) {
        try {
            sSoleInstance.params = new JSONObject(new Gson().toJson(o));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return sSoleInstance;
    }

    public static TedRequest requestCode(int RequestCode) {
        sSoleInstance.request_code = RequestCode;
        return sSoleInstance;
    }

    public static TedRequest listener(ResponseListener listener) {
        sSoleInstance.listener = listener;
        return sSoleInstance;
    }

    public static TedRequest returnType(String returnType) {
        sSoleInstance.returnType = returnType;
        return sSoleInstance;
    }

    public static TedRequest timeOut(int timeout) {
        sSoleInstance.MY_SOCKET_TIMEOUT_MS = timeout;
        return sSoleInstance;
    }

    public static void send() {
        if (validate()) {

            if (path != null)
                Log.e("Server Path", path);

            if (params != null) {
                Log.e("Params", params.toString());
            } else {
                Log.e("Params", "NULL");
            }

            if (Connectivity.isConnected(context)) {
                JsonObjectRequest getCNameRequest = new JsonObjectRequest
                        (Request.Method.POST, path, params, new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
//                                int RESP_CODE = 0;
                                //                                    RESP_CODE = response.getInt("Code");
//
//                                    switch (RESP_CODE) {
//                                        case 500:
//                                            listener.onResponseError(request_code, NO_DATA_FOUND, "Error getting data !");
//                                            break;
//                                        case 200:
//                                            if (returnType == null) {
//                                                JSONArray tempArray = response.getJSONArray("Result");
                                listener.onResponseSuccess(request_code, response);
//                                            } else {
//                                                JSONArray tempArray = response.getJSONArray(returnType);
//                                                listener.onResponseSuccess(request_code, tempArray);
//                                            }
//                                            break;
//                                    }
                            }

                        }, new Response.ErrorListener() {

                            @Override
                            public void onErrorResponse(VolleyError error) {
                                listener.onResponseError(request_code, SERVER_ERROR, error.toString());
                            }
                        });
                if (MY_SOCKET_TIMEOUT_MS > 0) {
                    getCNameRequest.setRetryPolicy(new DefaultRetryPolicy(
                            MY_SOCKET_TIMEOUT_MS,
                            DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                }
                AppController.getInstance(context).addToRequestQueue(getCNameRequest);
            } else {
                listener.onResponseError(request_code, NETWORK_ERROR, "Please enable internet");
            }
        }
    }

    private static boolean validate() {
        int e = 0;
        if (sSoleInstance.context == null) {
            e++;
            throw new RuntimeException("Context cannot be null!");
        }
        if (sSoleInstance.path == null) {
            e++;
            throw new RuntimeException("Url cannot be null!");
        }
        if (sSoleInstance.listener == null) {
            e++;
            throw new RuntimeException("Listener cannot be null!");
        }
        return e == 0 ? true : false;
    }

    private static JSONObject getParams(JSONObject inputs) {
        JSONObject obj = new JSONObject();
        try {
            obj.put("inputs", inputs);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return obj;
    }

}
