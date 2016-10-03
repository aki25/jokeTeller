package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.util.Pair;

import com.example.aki.myapplication.backend.myApi.MyApi;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import java.io.IOException;

public class FetchJokes extends AsyncTask<Pair<Context, String>, Void, String> {
    private static MyApi myApiService = null;
    private Context context;
    private Callback callingActivity;

    public FetchJokes(Callback callingActivity) {
        this.callingActivity = callingActivity;
    }

    interface Callback{
        void loadingStart();
        void loadingComplete(String joke);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        callingActivity.loadingStart();
    }

    @Override
protected String doInBackground(Pair<Context, String>... params) {
        if(myApiService == null) {  // Only do this once
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                    .setRootUrl("http://10.0.2.2:8080/_ah/api/") // 10.0.2.2 is localhost's IP address in Android emulator
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });  // end options for devappserver
            myApiService = builder.build();
        }

        try {
                return myApiService.fetchAJoke().execute().getData();
        } catch (IOException e) {
                return e.getMessage();
        }
        }

@Override
protected void onPostExecute(String result) {
    callingActivity.loadingComplete(result);
        //Toast.makeText(context, result, Toast.LENGTH_LONG).show();
        }
}