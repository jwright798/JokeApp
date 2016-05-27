package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Pair;

import com.example.jeremywright.myapplication.jokebackend.myApi.MyApi;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.jeremywright.jokeandroidlib.JokeActivity;

import java.io.IOException;

/**
 * Created by jeremywright on 5/1/16.
 * Code referenced from the example in the project steps
 */
public class JokeTask extends AsyncTask<Pair<Context,String>, Void, String> {

    private static MyApi myApiService = null;
    private Context context;

    @Override
    protected String doInBackground(Pair<Context,String>... params) {

        if(myApiService == null) {
            //Code taken from github example mentioned in instructions
            //Fun fact; if you mistype the url, when you execute the task, it will return the url instead of the service
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)

                    //using the emulator IP address
                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });

            myApiService = builder.build();
        }

        context = params[0].first;

        try{
            return myApiService.getJoke().execute().getData();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    //We don't want our UI to launch an intent to
    // display the joke until we actually get one...
    // so we'll do the intent to show the joke in the Async Task using the context we pass in

    @Override
    protected void onPostExecute(String joke) {
        Intent jokeIntent = new Intent(context, JokeActivity.class);
        jokeIntent.putExtra("joke", joke);
        context.startActivity(jokeIntent);
    }
}
