package com.android.collector;

/**
 * Created by javi__000 on 13/02/2015.
 */
public class ListElements {

    private String Title;
    private String DateCreated;
    private String ImageId;


    public ListElements(String Title, String DateCreated, String ImageId){
        this.Title = Title;
        this.DateCreated = DateCreated;
        this.ImageId = ImageId;
    }



    public String getTitle(){
        return this.Title;
    }

    public String getDateCreated(){
        return this.DateCreated;
    }

    public String getImageId(){

        return this.ImageId;
    }
}