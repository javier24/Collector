package com.android.collector;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;


public class CromosAlbum2 extends Fragment {
    private TextView ListViewText;
    private ListView ListViewEx;
    private ListElements[] Options;
    private ListAdapter Adapter;
    Communicator comm;
    String[][] information;
    DataBaseAdapter helper;



    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        //I need to implement onAttacch to initialize the DataBaseAdapter object in this fragment is the only way I have to qet
        //the context of the activity
        helper = new DataBaseAdapter(activity);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_cromos_album2, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    //just for to make sure the UI of the MainActivity is already created.
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        Context context;
        comm = (Communicator) getActivity();
        initAlbums();
    }

    void initAlbums() {
        String Album1 = "Beers";
        String Album2 = "Album 2";
        String Album3 = "Album 3";
        String Album4 = "Album 4";

        //Depend of the album the user select we are gonna need different columns
        String[]columns={DataBaseContract.Albums._ID, DataBaseContract.Albums.ALBUM_NAME, DataBaseContract.Albums.ALBUM_IMAGE, DataBaseContract.Albums.NUMBER_CROMOS};

        String[] columnsBeers = {DataBaseContract.Beers.BEER_NAME, DataBaseContract.Beers.BEER_SHOW};
//        String[]columnsBuildings={DataBaseContract.Albums._ID, DataBaseContract.Albums.ALBUM_NAME, DataBaseContract.Albums.ALBUM_IMAGE, DataBaseContract.Albums.NUMBER_CROMOS};


        String[] data;

        ListViewText = (TextView) getView().findViewById(R.id.AdvListViewText);
        ListViewEx = (ListView) getView().findViewById(R.id.AdvListViewEx);
        data = comm.send_string();
        int converted = Integer.parseInt(data[1]);
        // recive the name of the Table in the database (Alumb, beers,buildings),and the leght of the album,
        // generate the array with that amount of cromos, and then fill the array with the information from data base
    /*


        NEED A PIECE OF CODE HERE


     */


        information=helper.get_data(DataBaseContract.Beers.TABLE_NAME,columnsBeers);
        if (data[0].equals(Album1)) {
            Options = new ListElements[information.length];

                for (int i = 0; i < Options.length; i++)
                    {
                        Options[i] = new ListElements(information[i][0], information[i][0], information[i][1]);
                    }
            }

            Adapter = new ListAdapter(getActivity(), Options);
            ListViewText.setText(data[0]);
            ListViewEx.setAdapter(Adapter);
            ListViewEx.setFastScrollEnabled(true);

            ListViewEx.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                    //Acciones necesarias al hacer click.
                    ListViewText.setText("Selected " + Options[position].getTitle());
//                    Uri uri = Uri.parse("android.resource://" + getActivity().getApplicationContext().getPackageName() + "/drawable/" + Options[position].getImageId());
                    comm.respond(Options[position].getImageId());

                }
            });
        }
    }
