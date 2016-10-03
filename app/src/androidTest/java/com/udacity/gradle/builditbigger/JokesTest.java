package com.udacity.gradle.builditbigger;

import android.test.AndroidTestCase;

/**
 * Created by aki on 04-Aug-16.
 */

public class JokesTest extends AndroidTestCase implements FetchJokes.Callback{
    public void testJokeLoaded(){
        String testJoke = new FetchJokes(this).doInBackground();
        assertFalse(testJoke.isEmpty());
    }

    @Override
    public void loadingStart() {

    }

    @Override
    public void loadingComplete(String joke) {

    }
}
