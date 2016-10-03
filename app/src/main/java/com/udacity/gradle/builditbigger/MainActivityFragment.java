package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;


import com.aki.jokeviewer.JokeDisplay;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements FetchJokes.Callback{

    private Button tellJoke;
    private ProgressBar progressBar;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        progressBar = (ProgressBar) root.findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);
        tellJoke = (Button) root.findViewById(R.id.tellJoke);
        tellJoke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // need aditional helper method to pass callback, passing from here passes click listener.
                tellJokeClicked();
            }
        });
        return root;
    }

    public void tellJokeClicked(){
        new FetchJokes(this).execute();
    }

    @Override
    public void loadingStart() {
        tellJoke.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void loadingComplete(String joke) {
        tellJoke.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
        Intent intent = new Intent(getContext(), JokeDisplay.class);
        intent.putExtra(Intent.EXTRA_TEXT,joke);
        startActivity(intent);
    }
}
