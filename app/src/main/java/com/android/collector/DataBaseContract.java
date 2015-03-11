package com.android.collector;

import android.provider.BaseColumns;


public class DataBaseContract {
    /**
     * Defines table and column names for the weather database.
     */

        /* Inner class that defines the table contents of the location table */
    public static final class Albums implements BaseColumns {

        // Table name
        public static final String TABLE_NAME = "albums";          // --TABLE NAME.
        // name of the album                                        ___________________________________________________________
        public static final String ALBUM_NAME = "album_name";      //  _ID......ALBUM_NAME.......ALBUM_IMAGE.....NUMERO_CROMOS
        // path to the image                                       //   1.......BEERS............PICTURE.............5......
        public static final String ALBUM_IMAGE = "album_image";
        public static final String NUMBER_CROMOS= "numero_cromos"; //___________________________________________________________

    }

    /* Inner class that defines the table contents of the beers table */
    public static final class Beers implements BaseColumns {

        // Table name
        public static final String TABLE_NAME = "beers";                // --TABLE NAME.
        // name of the album                                            ___________________________________________________________________
        public static final String BEER_NAME =  "beer_name";           //  _ID...BEER_NAME...BEER_IMAGE_GREY..BEER_IMAGE_OK.....OK........BEERS_SHOW....ALBUM_ID
        // path to the image                                            //   1....BEERS.........PICTURE_grey.......picture.... FALSE............PIC............._id
    //    public static final String BEER_IMAGE_GREY = "beer_image_grey"; //_________________________________________________________________________
    //    public static final String BEER_IMAGE_OK = "beer_image";
        public static final String BEER_SHOW="show_image";
    //    public static final String OK = "ok";

    }

    /* Inner class that defines the table contents of places table */
    public static final class Important_buildings implements BaseColumns {

        // Table name
        public static final String TABLE_NAME = "important_buildings";          // --TABLE NAME.
        // name of the album                                                    ___________________________________________________________________
        public static final String BUILDING_NAME =  "building_name";           //  _ID...BUILDING_NAME...BUILDING_IMAGE_GREY..BUILDING_IMAGE_OK..LATITUD....LONGITUD...OK.....ALBUM_ID
        // path to the image                                                    //   1....BUILDINGS.........PICTURE_grey.......picture............ 556........673.....FALSE......_id
        public static final String BUILDING_IMAGE_GREY = "building_image_grey"; //_________________________________________________________________________
        public static final String BUILDING_IMAGE_OK = "building_image";
        public static final String COOR_LAT = "latitud";
        public static final String COOR_LONG = "longitud";
        public static final String OK = "ok";
        public static final String ALBUM_ID = "album_id";
    }
}
