package com.android.collector;

import android.app.Fragment;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;


public class ShowCromoFragment extends Fragment {
    ImageView imageView;
    View button;
    Communicator comm;

    public static final String ShowCromoID= "ID";
    String image;
    int resId;

    public ShowCromoFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle arguments = getArguments();
        if (arguments != null)
        {
            if (getArguments().containsKey(ShowCromoID)) {
                // Load the dummy content specified by the fragment
                // arguments. In a real-world scenario, use a Loader
                // to load content from a content provider.
                image = getArguments().getString(ShowCromoID);
                Resources resources = getResources();
                resId = resources.getIdentifier(image, "drawable", getActivity().getPackageName());
            }
            // then you have arguments
        } else {
            Message.message(getActivity(),"no data");
            // no arguments supplied...
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //   Bundle bundle = this.getArguments();
        //   myValue = bundle.getString("ID");

        View rootView = inflater.inflate(R.layout.showcromofragment, container, false);

        // Show the dummy content as text in a TextView.
        if (image != null) {
            ((ImageView) rootView.findViewById(R.id.imageView)).setImageResource(resId);
             button=rootView.findViewById(R.id.camera_button);
        }
        return rootView;
    }


    public void OnActivityCreated (Bundle SaveInstanceState){



    }

}


