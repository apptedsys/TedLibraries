//package in.tedsys.tedutils;
//
//import android.content.Context;
//import android.graphics.Bitmap;
//import android.widget.Toast;
//
//import com.android.volley.AuthFailureError;
//import com.android.volley.NetworkResponse;
//import com.android.volley.Request;
//import com.android.volley.Response;
//import com.android.volley.VolleyError;
//import com.android.volley.toolbox.Volley;
//
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.io.ByteArrayOutputStream;
//import java.util.HashMap;
//import java.util.Map;
//
//public class TedFileUpload {
//
//    private static TedFileUpload sSoleInstance;
//
//    private static Context context = null;
//    private static String path = null;
//    private static JSONObject params = null;
//    private static ResponseListener listener;
//    private static int request_code;
//
//    private TedFileUpload() {
//    }  //private constructor.
//
//    public static TedFileUpload get(Context context) {
//        if (sSoleInstance == null) { //if there is no instance available... create new one
//            sSoleInstance = new TedFileUpload();
//        }
//        sSoleInstance.context = context;
//        return sSoleInstance;
//    }
//
//    private void uploadBitmap(final Bitmap bitmap) {
//
//        //getting the tag from the edittext
//        final String tags = editTextTags.getText().toString().trim();
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
//
//}
