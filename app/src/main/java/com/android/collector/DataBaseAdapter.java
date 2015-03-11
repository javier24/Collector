package com.android.collector;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DataBaseAdapter {
    OpenDBHelper helper;
    int j=0;
    public DataBaseAdapter(Context context){
        //this constructor is neccesary because we need the context to initialize the object of OpenDBHelper
        helper=new OpenDBHelper(context);

    }
    public long insert_data(String albumName, String ImageAlbum){
        //we take the database object helper, and open in read and write mode
        SQLiteDatabase db=helper.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(DataBaseContract.Albums.ALBUM_NAME,albumName);
        contentValues.put(DataBaseContract.Albums.ALBUM_IMAGE,ImageAlbum);
        long id=db.insert(DataBaseContract.Albums.TABLE_NAME,null,contentValues);
        //the previous sentence return -1 if something went wrong, and the id of the last row inserted.
        return id;
    }

    public String View_Details(){
        SQLiteDatabase db=helper.getWritableDatabase();
        //select Album name , album image from Albums Table from Database
        String[] colums={DataBaseContract.Albums._ID, DataBaseContract.Albums.ALBUM_NAME, DataBaseContract.Albums.ALBUM_IMAGE};
        //The query it is gonna return a cursor object
        Cursor cursor=db.query(DataBaseContract.Albums.TABLE_NAME,colums,null,null,null,null,null);
        StringBuffer stringBuffer=new StringBuffer();
        while(cursor.moveToNext())
        {
            int cid=cursor.getInt(0);
            String name=cursor.getString(1);
            String image=cursor.getString(2);
            stringBuffer.append( cid +" "+name+ " " +image+"\n");
        }
        return stringBuffer.toString();
    }
    public String[][] get_data(String Table_Name, String[] columns) {
        //select album_name and Image_name from where name = 'JUAN'
        SQLiteDatabase db = helper.getWritableDatabase();
        //The query it is gonna return a cursor object
        Cursor cursor1 = db.rawQuery("select * from "+ Table_Name,null);
        int count =cursor1.getCount();

        Cursor cursor2 = db.query(Table_Name, columns, null
                , null, null, null, null);
       String[][]information=new String[count][columns.length];
        while (cursor2.moveToNext()) {
            for (int i = 0; i < columns.length; i++) {
                information[j][i] = cursor2.getString(cursor2.getColumnIndex(columns[i]));
            }
            j++;
        }
        return information;
    }


   /* public String get_data(String albumName){
        //select album_name and Image_name from where name = 'JUAN'
        SQLiteDatabase db=helper.getWritableDatabase();
        //select Album name , album image from Albums Table from Database
        String[] colums={DataBaseContract.Albums.ALBUM_NAME, DataBaseContract.Albums.ALBUM_IMAGE};
        //The query it is gonna return a cursor object
        Cursor cursor=db.query(DataBaseContract.Albums.TABLE_NAME,colums, DataBaseContract.Albums.ALBUM_NAME+ " = '" +albumName+"'"
                ,null,null,null,null);
        StringBuffer stringBuffer=new StringBuffer();
        while(cursor.moveToNext())
        {
            int index1=cursor.getColumnIndex(DataBaseContract.Albums.ALBUM_NAME);
            int index2=cursor.getColumnIndex(DataBaseContract.Albums.ALBUM_IMAGE);
            String name=cursor.getString(index1);
            String image=cursor.getString(index2);
            stringBuffer.append(image);
        }
        return stringBuffer.toString();
    }*/

    public int Update_Row(String old_name, String newImageName){

        // UPDATE albums  SET album_name='Juan' where album_name=? test
        SQLiteDatabase db=helper.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        //key name of the column we wanna update, and the value the value
        contentValues.put(DataBaseContract.Albums.ALBUM_NAME,newImageName);
        String[] whereArgs={old_name};

        // this function will return an integer of how many rows has been updated
        int count=db.update(DataBaseContract.Albums.TABLE_NAME,contentValues, DataBaseContract.Albums.ALBUM_NAME+" =? ",whereArgs);
        return count;
    }

    public int delete_Row(){
        //DELETE * FROM albums Where Name =
        SQLiteDatabase db=helper.getWritableDatabase();
        String[] whereArgs={"SAM5"};
        int count=db.delete(DataBaseContract.Albums.TABLE_NAME, DataBaseContract.Albums.ALBUM_NAME + " =? ",whereArgs);
        return count;
    }



    static class OpenDBHelper extends SQLiteOpenHelper {
        // If you change the database schema, you must increment the database version.
        private static final int DATABASE_VERSION =3;
        private static final String DATABASE_NAME = "album.db";
        private SQLiteDatabase sqLiteDatabase;

        public OpenDBHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
            this.sqLiteDatabase=this.getWritableDatabase();

            //  Message.message(context, "funciona");
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            // Create a table to hold locations.  A location consists of the string supplied in the
            // location setting, the city name, and the latitude and longitude
            final String SQL_CREATE_ALBUMS_TABLE = "CREATE TABLE " + DataBaseContract.Albums.TABLE_NAME + " (" +
                    DataBaseContract.Albums._ID + " INTEGER PRIMARY KEY," +
                    DataBaseContract.Albums.ALBUM_NAME + " TEXT UNIQUE NOT NULL, " +
                    DataBaseContract.Albums.ALBUM_IMAGE + " TEXT NOT NULL, " +
                    DataBaseContract.Albums.NUMBER_CROMOS + " INTEGER NOT NULL, " +
                    "UNIQUE (" + DataBaseContract.Albums.ALBUM_NAME +") ON CONFLICT IGNORE"+
                    " );";

            final String SQL_CREATE_BEERS_TABLE = "CREATE TABLE " + DataBaseContract.Beers.TABLE_NAME + " (" +
                    DataBaseContract.Beers._ID + " INTEGER PRIMARY KEY," +
                    DataBaseContract.Beers.BEER_NAME + " TEXT UNIQUE NOT NULL, " +
                    DataBaseContract.Beers.BEER_SHOW + " TEXT NOT NULL, " +
                    "UNIQUE (" + DataBaseContract.Beers.BEER_NAME +") ON CONFLICT IGNORE"+
                    " );";


            final String SQL_CREATE_IMPORTANT_BUILDINGS_TABLE = "CREATE TABLE " + DataBaseContract.Important_buildings.TABLE_NAME + " (" +
                    DataBaseContract.Important_buildings._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    DataBaseContract.Important_buildings.ALBUM_ID + " INTEGER NOT NULL, " +
                    DataBaseContract.Important_buildings.BUILDING_NAME + " TEXT NOT NULL, " +
                    DataBaseContract.Important_buildings.BUILDING_IMAGE_GREY + " TEXT NOT NULL, " +
                    DataBaseContract.Important_buildings.BUILDING_IMAGE_OK + " TEXT NOT NULL, " +
                    DataBaseContract.Important_buildings.COOR_LAT+ " TEXT NOT NULL, " +
                    DataBaseContract.Important_buildings.COOR_LONG + " TEXT NOT NULL, " +
                    DataBaseContract.Important_buildings.OK + " INTEGER NOT NULL," +
                    // Set up the location column as a foreign key to location table.
                    " FOREIGN KEY (" + DataBaseContract.Important_buildings.ALBUM_ID + ") REFERENCES " +
                    DataBaseContract.Albums.TABLE_NAME + " (" + DataBaseContract.Albums._ID + ") );";



                sqLiteDatabase.execSQL(SQL_CREATE_BEERS_TABLE);

                sqLiteDatabase.execSQL(SQL_CREATE_ALBUMS_TABLE);
                Initialize_Albums(sqLiteDatabase);

                sqLiteDatabase.execSQL(SQL_CREATE_IMPORTANT_BUILDINGS_TABLE);


        }

        public void Initialize_Albums(SQLiteDatabase sqLiteDatabase){
            sqLiteDatabase.execSQL("INSERT INTO "+ DataBaseContract.Albums.TABLE_NAME+" ("+ DataBaseContract.Albums.ALBUM_NAME+", "+ DataBaseContract.Albums.ALBUM_IMAGE+", "+ DataBaseContract.Albums.NUMBER_CROMOS+")values ('Beers','cerveza','7')");
            sqLiteDatabase.execSQL("INSERT INTO "+ DataBaseContract.Albums.TABLE_NAME+" ("+ DataBaseContract.Albums.ALBUM_NAME+", "+ DataBaseContract.Albums.ALBUM_IMAGE+", "+ DataBaseContract.Albums.NUMBER_CROMOS+")values ('Buildings','buildings','9')");

            sqLiteDatabase.execSQL("INSERT INTO "+ DataBaseContract.Beers.TABLE_NAME+" ("+ DataBaseContract.Beers.BEER_NAME+", "+ DataBaseContract.Beers.BEER_SHOW+")values ('MAHOU','mahou')");
            sqLiteDatabase.execSQL("INSERT INTO "+ DataBaseContract.Beers.TABLE_NAME+" ("+ DataBaseContract.Beers.BEER_NAME+", "+ DataBaseContract.Beers.BEER_SHOW+")values ('CORONA','corona')");
            sqLiteDatabase.execSQL("INSERT INTO "+ DataBaseContract.Beers.TABLE_NAME+" ("+ DataBaseContract.Beers.BEER_NAME+", "+ DataBaseContract.Beers.BEER_SHOW+")values ('HEINEKEN','heineken')");
            sqLiteDatabase.execSQL("INSERT INTO "+ DataBaseContract.Beers.TABLE_NAME+" ("+ DataBaseContract.Beers.BEER_NAME+", "+ DataBaseContract.Beers.BEER_SHOW+")values ('GUINESS','guiness')");


        }


        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DataBaseContract.Albums.TABLE_NAME);
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DataBaseContract.Beers.TABLE_NAME);
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DataBaseContract.Important_buildings.TABLE_NAME);
            onCreate(sqLiteDatabase);
        }


        public void Initialize_Beers(SQLiteDatabase sqLiteDatabase){
            sqLiteDatabase.execSQL("INSERT INTO "+ DataBaseContract.Beers.TABLE_NAME+" ("+ DataBaseContract.Beers.BEER_NAME+", "+ DataBaseContract.Beers.BEER_SHOW+")values ('Ammstel','aguilaammstel')");
            sqLiteDatabase.execSQL("INSERT INTO "+ DataBaseContract.Beers.TABLE_NAME+" ("+ DataBaseContract.Beers.BEER_NAME+", "+ DataBaseContract.Beers.BEER_SHOW+")values ('Coronita','corona')");
      /*      sqLiteDatabase.execSQL("INSERT INTO "+ DataBaseContract.Beers.TABLE_NAME+" ("+ DataBaseContract.Beers.BEER_NAME+", "+ DataBaseContract.Beers.BEER_SHOW+")values ('Cruzcampo','cruzcampo')");
            sqLiteDatabase.execSQL("INSERT INTO "+ DataBaseContract.Beers.TABLE_NAME+" ("+ DataBaseContract.Beers.BEER_NAME+", "+ DataBaseContract.Beers.BEER_SHOW+")values ('Estrella','estrelladam')");
            sqLiteDatabase.execSQL("INSERT INTO "+ DataBaseContract.Beers.TABLE_NAME+" ("+ DataBaseContract.Beers.BEER_NAME+", "+ DataBaseContract.Beers.BEER_SHOW+")values ('Guiness','guiness')");
            sqLiteDatabase.execSQL("INSERT INTO "+ DataBaseContract.Beers.TABLE_NAME+" ("+ DataBaseContract.Beers.BEER_NAME+", "+ DataBaseContract.Beers.BEER_SHOW+")values ('Heineken','heineken')");
            sqLiteDatabase.execSQL("INSERT INTO "+ DataBaseContract.Beers.TABLE_NAME+" ("+ DataBaseContract.Beers.BEER_NAME+", "+ DataBaseContract.Beers.BEER_SHOW+")values ('Mahou','mahou')");
*/
        }
    }
}
