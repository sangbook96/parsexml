package vn.sangdv.simplexml.connection;

import android.os.AsyncTask;
import android.util.Log;

import java.util.Collections;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import okhttp3.ConnectionSpec;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class RequestAsynTask {
    public String bodyData;
    public String URL ="";
    private Callback onListenerCallBack;
    private static String TAG="RequestAsynTask";
    private final MediaType mediaType = MediaType.parse("text/xml; charset=utf-8");

    public RequestAsynTask( String URL,String bodyData, Callback onListenerCallBack) {
        this.bodyData = bodyData;
        this.URL = URL;
        this.onListenerCallBack = onListenerCallBack;
    }

    public class AsyncTaskRequest extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... strings) {
//              String text = String.format(makePaymentPostData, "4000", "5234", "324");
                String text = bodyData;
                Log.d(TAG, "doInBackground: " + text);
                Log.d(TAG, "doInBackground: " + URL);
                return post(URL, text);
        }

        @Override
        protected void onPostExecute(String response) {
            try {
                onListenerCallBack.onSuccessResponse(response);

            } catch (Exception e) {
                e.printStackTrace();
                Log.e(TAG, "Failed to connect#", e);
                onListenerCallBack.onError(e.getMessage());
                return;
            }
        }
    }

    public String post(String url, String postData) {
        Log.d(TAG, "URL: "+url);
        Log.d(TAG, "POST DATA: "+postData);
        String resBody ="";
        RequestBody body = RequestBody.create(mediaType, postData);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        try {
            ConnectionSpec spec = new ConnectionSpec.Builder(ConnectionSpec.COMPATIBLE_TLS)
                    .allEnabledCipherSuites().allEnabledTlsVersions()
                    .build();
            OkHttpClient client = new OkHttpClient.Builder()
                    .hostnameVerifier(new HostnameVerifier() {
                        @Override
                        public boolean verify(String hostname, SSLSession session) {
                            return true;
                        }
                    })
                    .connectionSpecs(Collections.singletonList(spec))
                    .build();
            Response response = client.newCall(request).execute();
            final int code = response.code();
            if (response.isSuccessful()){
                resBody = response.body().string();
                response.body().close();
            }else {
                onListenerCallBack.onError(response.code()+" - "+response.message());
            }

        }

        catch (Exception e) {
            e.printStackTrace();
            onListenerCallBack.onError(e.getMessage());
        }
        return resBody;
    }

    public interface Callback{
        void onSuccessResponse(String response);
        void onError(String message);
    }
}
