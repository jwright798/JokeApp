package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.test.AndroidTestCase;
import android.util.Pair;

import java.util.concurrent.CountDownLatch;

//using AndroidTestCase because of personal preference :)
public class ApplicationTest extends AndroidTestCase {

    //using CountDownLatch to know when the task is done
    CountDownLatch signal = new CountDownLatch(1);
    String testJoke = null;

    public void testJokeTask() throws  InterruptedException{
        //need to override the onPostExecute to get the joke from the task
        JokeTask task= new JokeTask(){
            @Override
            protected void onPostExecute(String joke) {
                //assign the joke and start the countdown
                testJoke = joke;
                signal.countDown();
            }
        };

        task.execute(new Pair<Context, String>(getContext(), ""));

        //wait for the countdown
        signal.await();

        //test that the joke is not null or empty
        assertTrue(testJoke != null && !testJoke.isEmpty());
    }
}