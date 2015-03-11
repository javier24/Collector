package com.android.collector;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {

    private TextView ListViewText;
    private ListView ListViewEx;
    private ListElements[] Options;
    private ListAdapter Adapter;
    //DataBase Object
    DataBaseAdapter helper;
    Cursor cursor;
    String[]columns={DataBaseContract.Albums._ID, DataBaseContract.Albums.ALBUM_NAME, DataBaseContract.Albums.ALBUM_IMAGE, DataBaseContract.Albums.NUMBER_CROMOS};
    String[][]information;
    String[] columnsBeers = {DataBaseContract.Beers.BEER_NAME, DataBaseContract.Beers.BEER_SHOW};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //initizlize the DataBaseAdapter object, if not  the helper.get_data will return null pointer exception
        helper = new DataBaseAdapter(this);
      //  Drawables in android are dinamics, so we need to store the name of the drawable in our DataBaae and after to retrive
      //  the id of the Drawable with the function
      //  Resources resources = getResources();
      //  String name = resources.getResourceEntryName(R.drawable.cerveza); Retrieve image name from the Drawable
      //  int resId = resources.getIdentifier(name, "drawable", getPackageName()); Retrive Id from name, tipe and package name

        ListViewText = (TextView)findViewById(R.id.AdvListViewText);
        ListViewEx = (ListView)findViewById(R.id.AdvListViewEx);

     // Right now the number of album are fixed but the idea is to download the number of albums that the user wants
     // so we would have to go through the Data Base looking for what albums does the user have?


        information=helper.get_data(DataBaseContract.Albums.TABLE_NAME,columns);
     //We have called the method get_data that returns an array with the information of the colums we asked. =)

        Options = new ListElements[information.length];

        for (int i = 0; i < Options.length; i++)
        {
            Options[i] = new ListElements(information[i][1], information[i][3], information[i][2]);
        }


       /* Options = new ListElements[] {
                new ListElements("Album 1","29-06-2015","cerveza"),
                new ListElements("Album 2","29-06-2015","buildings")
        };*/

        Adapter = new ListAdapter(this,Options);
        ListViewText.setText("Nothing selected yet");
        ListViewEx.setAdapter(Adapter);
        ListViewEx.setFastScrollEnabled(true);

        ListViewEx.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                //Acciones necesarias al hacer click.
                ListViewText.setText("Selected " + Options[position].getTitle());//Options[position].getTitle());
                Intent intent = new Intent(getBaseContext(), MainActivity2.class);
                intent.putExtra("ALBUM_ID", Options[position].getTitle());
                intent.putExtra("NUMERO_CROMOS", Options[position].getDateCreated());
                startActivity(intent);
            }
        });
    }

   @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}