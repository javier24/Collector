package com.android.collector;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;


public class ShowCromoActivityENd extends ActionBarActivity implements Communicator {
    FragmentManager manager;
    Button camera_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_cromo_activity_end);
        manager=getFragmentManager();
        Message.message(this, getIntent().getStringExtra(ShowCromoFragment.ShowCromoID));

        if (savedInstanceState == null) {
            // Create the detail fragment and add it to the activity
            // using a fragment transaction.
            Bundle arguments = new Bundle();
            arguments.putString(ShowCromoFragment.ShowCromoID,getIntent().getStringExtra(ShowCromoFragment.ShowCromoID));
            ShowCromoFragment fragment5 = new ShowCromoFragment();
            fragment5.setArguments(arguments);
            FragmentTransaction transaction=manager.beginTransaction();
            transaction.replace(R.id.ContainerCromo, fragment5,"A");
            transaction.commit();
        }

    }
    public void camera(View view) {
        camera_button = (Button)findViewById(R.id.camera_button);
        Intent intent = new Intent(this, Activity_Camera.class);
        startActivity(intent);

    }


    @Override
    public void respond(String ImageID) {

    }

    @Override
    public String[] send_string() {
        return null;
    }


}
