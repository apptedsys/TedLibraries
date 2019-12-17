# Ted Libraries
Ted Libraries initial release is just a library that simplifies the API interactions with application. 
it usues android Volley library 

Add it in your root build.gradle at the end of repositories:

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
Step 2. Add the dependency

	dependencies {
	        implementation 'com.github.apptedsys:TedLibraries:Tag'
	}
  
Step 3. Implement Response Listener

	public class MainActivity extends AppCompatActivity implements ResponseListener {
	        @Override
          protected void onCreate(Bundle savedInstanceState) {
                //oncreate
          }
	}
  
Step 4. Now we can add API function in another function

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
  
  Step 5. Get Responses from APIs

    @Override
    public void onResponseSuccess(int RequestCode, JSONArray result) {
         //You will get the result as JSON array 
         //your code here
    }
    
    @Override
    public void onResponseError(int RequestCode, int ErrorCode, String Error) {
        //Your code on Error
        Toast.makeText(this, Error, Toast.LENGTH_SHORT).show();
    }

Not this Library supports API communication through JSON only and the response should be in the followed pattern

	{
        "Code": "200",
        "Result": [
              {   /*put your response as json use 200 for success and 500 for error*/
                  " YOUR RESPONSE DATA HERE ": "Code 200 is cusomized success and use 500 to throw an error response"
              }
        ]
	}
  
  This is just an initial approch to simplifying java codes in android apps.
  Thank you all :)
  
