package in.tedsys.tedlibraries;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

import in.tedsys.tedutils.ResponseListener;
import in.tedsys.tedutils.TedRequest;
import in.tedsys.tedutils.TedToast;
import in.tedsys.tedutils.VolleyMultipartRequest;

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

//    @Override
//    public void onResponseSuccess(int RequestCode, JSONArray result) {
//        Toast.makeText(this, "Success !", Toast.LENGTH_SHORT).show();
//        //TedToast.show(this,"Success !",true);
//        TedToast.get(this)
//                .font(TedToast.SANS_RGLR)
//                .gravity(Gravity.BOTTOM)
//                .yoffset(100)
//                .message("Success!")
//                .islong(false)
//                .drawable(R.drawable.ic_android)
//                .textsize(20)
//                .show();
//    }

    @Override
    public void onResponseSuccess(int RequestCode, JSONObject result) {

    }

    @Override
    public void onResponseError(int RequestCode, int ErrorCode, String Error) {
        Toast.makeText(this, "Oops! " + Error, Toast.LENGTH_SHORT).show();
    }


//    private void uploadBitmap(final Bitmap bitmap) {
//
//        //getting the tag from the edittext
//       // final String tags = editTextTags.getText().toString().trim();
//
//        //our custom volley request
//        VolleyMultipartRequest volleyMultipartRequest = new VolleyMultipartRequest(Request.Method.POST, EndPoints.UPLOAD_URL,
//                new Response.Listener<NetworkResponse>() {
//                    @Override
//                    public void onResponse(NetworkResponse response) {
//                        try {
//                            JSONObject obj = new JSONObject(new String(response.data));
//                            Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
//                    }
//                }) {
//
//            /*
//             * If you want to add more parameters with the image
//             * you can do it here
//             * here we have only one parameter with the image
//             * which is tags
//             * */
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String, String> params = new HashMap<>();
//                params.put("tags", tags);
//                return params;
//            }
//
//            /*
//             * Here we are passing image by renaming it with a unique name
//             * */
//            @Override
//            protected Map<String, DataPart> getByteData() {
//                Map<String, DataPart> params = new HashMap<>();
//                long imagename = System.currentTimeMillis();
//                params.put("pic", new VolleyMultipartRequest.DataPart(imagename + ".png", getFileDataFromDrawable(bitmap)));
//                return params;
//            }
//        };
//
//        //adding the request to volley
//        Volley.newRequestQueue(this).add(volleyMultipartRequest);
//    }
//
//    /*
//     * The method is taking Bitmap as an argument
//     * then it will return the byte[] array for the given bitmap
//     * and we will send this array to the server
//     * here we are using PNG Compression with 80% quality
//     * you can give quality between 0 to 100
//     * 0 means worse quality
//     * 100 means best quality
//     * */
//    public byte[] getFileDataFromDrawable(Bitmap bitmap) {
//        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//        bitmap.compress(Bitmap.CompressFormat.PNG, 80, byteArrayOutputStream);
//        return byteArrayOutputStream.toByteArray();
//    }

}
