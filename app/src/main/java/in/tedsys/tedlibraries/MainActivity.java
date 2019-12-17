package in.tedsys.tedlibraries;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;

import in.tedsys.tedutils.ResponseListener;
import in.tedsys.tedutils.TedRequest;

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
        TedRequest.get(this)
                .url("http://192.168.0.116/ServizioService/TabService.ashx/GetTableList")
                //.params(getParams())
                .listener(this)
                .requestCode(REQUEST_CODE)
                .send();
    }

    @Override
    public void onResponseSuccess(int RequestCode, JSONArray result) {
        Toast.makeText(this, "Success!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResponseError(int RequestCode, int ErrorCode, String Error) {
        Toast.makeText(this, "Oops! " + Error, Toast.LENGTH_SHORT).show();
    }
}
