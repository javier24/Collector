package com.android.collector;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity2 extends FragmentActivity implements Communicator{
    private Boolean mTwoPane;
    String[] data=new String[2];
    FragmentManager manager;
    Button camera_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //  setContentView(R.layout.activity_main_activity2);

        int screenSize = getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK;
        switch(screenSize) {
            case Configuration.SCREENLAYOUT_SIZE_LARGE:
                mTwoPane=true;
                setContentView(R.layout.activity_main_activity2_twopane);
                Toast.makeText(this, "Large screen", Toast.LENGTH_LONG).show();
                break;
            case Configuration.SCREENLAYOUT_SIZE_NORMAL:
                mTwoPane=true;
                setContentView(R.layout.activity_main_activity2_twopane);
                Toast.makeText(this, "Normal screen",Toast.LENGTH_LONG).show();
                break;
            case Configuration.SCREENLAYOUT_SIZE_SMALL:
                mTwoPane=false;
                setContentView(R.layout.activity_main_activity2);
                Toast.makeText(this, "Small screen",Toast.LENGTH_LONG).show();
                break;
            default:
                Toast.makeText(this, "Screen size is neither large, normal or small" , Toast.LENGTH_LONG).show();

        }

        Message.message(this," "+mTwoPane);
        manager=getFragmentManager();

    }
    public String[] send_string(){
        data[0] = getIntent().getExtras().getString("ALBUM_ID");
        data[1]=  getIntent().getExtras().getString("NUMERO_CROMOS");
        return data;
    }
    public void camera(View view) {
        camera_button = (Button)findViewById(R.id.camera_button);
        Intent intent = new Intent(this, Activity_Camera.class);
        startActivity(intent);

    }


    @Override
    public void respond(String ImageID) {
        if ( mTwoPane) {
            Bundle arguments = new Bundle();
            arguments.putString(ShowCromoFragment.ShowCromoID, ImageID);
            ShowCromoFragment fragment4=new ShowCromoFragment();
            fragment4.setArguments(arguments);
            FragmentTransaction transaction=manager.beginTransaction();
            transaction.replace(R.id.container, fragment4,"A");
            transaction.commit();

        }else{
            Intent intent = new Intent(this, ShowCromoActivityENd.class);
            intent.putExtra(ShowCromoFragment.ShowCromoID, ImageID);
            startActivity(intent);

        }
    }
}
