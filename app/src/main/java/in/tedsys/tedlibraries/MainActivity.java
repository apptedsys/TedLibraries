package in.tedsys.tedlibraries;

import android.os.Bundle;
import android.view.Gravity;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;

import in.tedsys.tedutils.ResponseListener;
import in.tedsys.tedutils.TedRequest;
import in.tedsys.tedutils.TedToast;

public class MainActivity extends AppCompatActivity implements ResponseListener {

    private static final int REQUEST_CODE = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_test).setOnClickListener(v -> Test());
    }

    private void Test() {
        loadData();
    }

    private void loadData() {
        TedRequest.get(this)//Context
                //API Function url
                .url("http://192.168.0.116/ServizioService/TabService.ashx/GetTableList")
                //Add parameters to API in the form of JSONObject
                //.params(getParams())
                //Response Listener to get return values from API
                .listener(this)
                //Add a number to identify the request
                //declared as 'public static final REQUEST_CODE = 101';
                .requestCode(REQUEST_CODE)
                //and finally send this request t server
                .send();
    }

    @Override
    public void onResponseSuccess(int RequestCode, JSONArray result) {
        Toast.makeText(this, "Success !", Toast.LENGTH_SHORT).show();
        //TedToast.show(this,"Success !",true);
        TedToast.get(this)
                .font(TedToast.SANS_RGLR)
                .gravity(Gravity.BOTTOM)
                .yoffset(100)
                .message("Success!")
                .islong(false)
                .drawable(R.drawable.ic_android)
                .textsize(20)
                .show();
    }

    @Override
    public void onResponseError(int RequestCode, int ErrorCode, String Error) {
        Toast.makeText(this, "Oops! " + Error, Toast.LENGTH_SHORT).show();
    }
}
