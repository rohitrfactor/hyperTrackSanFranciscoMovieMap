package com.example.garorasu.onceagain;

/**
 * Created by Rohit Nagpal on 15/5/16.
 * Email: rohitrfactor@gmail.com
 */
import com.google.gson.annotations.SerializedName;
public class Movie {
    @SerializedName("actor_1")
    private String actor_1;
    @SerializedName("actor_2")
    private String actor_2;
    @SerializedName("actor_3")
    private String actor_3;
    @SerializedName("director")
    private String director;
    @SerializedName("locations")
    private String locations;
    @SerializedName("production_company")
    private String production_company;
    @SerializedName("release_year")
    private Integer release_year;
    @SerializedName("title")
    private String title;
    @SerializedName("writer")
    private String writer;


    public void Movie(String actor_1,String actor_2,String actor_3,String director,String locations,
                      String production_company,int release_year,String title,String writer){
        this.actor_1=actor_1;
        this.actor_2=actor_2;
        this.actor_3=actor_3;
        this.director=director;
        this.locations=locations;
        this.production_company= production_company;
        this.release_year = release_year;
        this.title=title;
        this.writer=writer;
    }



    String getCast(){
        return actor_1+" , "+actor_2+" , "+actor_3;
    }
    String getDirector(){
        return director;
    }
    String getLocations(){
        return locations;
    }
    String getProduction_company(){
        return production_company;
    }
    Integer getRelease_year(){
        return release_year;
    }
    String getTitle(){
        return title;
    }
    String getWriter(){
        return writer;
    }
}
