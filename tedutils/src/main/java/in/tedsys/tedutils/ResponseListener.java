package in.tedsys.tedutils;

import org.json.JSONArray;
import org.json.JSONObject;

public interface ResponseListener {
    void onResponseSuccess(int RequestCode, JSONObject result);

    void onResponseError(int RequestCode, int ErrorCode, String Error);
}
