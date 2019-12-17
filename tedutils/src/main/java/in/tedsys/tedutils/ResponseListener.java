package in.tedsys.tedutils;

import org.json.JSONArray;

public interface ResponseListener {
    void onResponseSuccess(int RequestCode, JSONArray result);

    void onResponseError(int RequestCode, int ErrorCode, String Error);
}
