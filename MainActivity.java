package com.example.garorasu.onceagain;
/**
 * Created by Rohit Nagpal on 15/5/16.
 * Email: rohitrfactor@gmail.com
 */
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.net.URLEncoder;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ProgressDialog progressDialog;
    private TextView txtPostList;
    private List<Movie> movieArrayList;
    private StringBuffer movieList;
    private TextView re,txtTitle,txtCast,txtDirector,txtReleaseYear,txtWriter,txtProductionCompany;
    private String[] listLocations = new String[10];
    private AutoCompleteTextView actv;
    private String title, cast,director,writer,productionCompany;
    private Integer releaseYear;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtPostList = (TextView)findViewById(R.id.movies);
        txtTitle = (TextView) findViewById(R.id.title);
        txtCast = (TextView) findViewById(R.id.cast);
        txtDirector = (TextView) findViewById(R.id.director);
        txtReleaseYear = (TextView) findViewById(R.id.releaseYear);
        txtWriter = (TextView) findViewById(R.id.writer);
        txtProductionCompany = (TextView) findViewById(R.id.productionCompany);
        //re = (TextView) findViewById(R.id.result);
        actv = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView1);
        String[] countries = getResources().getStringArray(R.array.list_of_movies);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,countries);
        actv.setAdapter(adapter);
        actv.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // If the event is a key-down event on the "enter" button
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    // Perform action on key press
                    result(v);
                    //Toast.makeText(MainActivity.this, urlText.getText(), Toast.LENGTH_SHORT).show();
                    return true;
                }
                return false;
            }
        });


        fab = (FloatingActionButton) findViewById(R.id.plot_on_map);
        fab.setEnabled(false);

    }
    public void showMap(View view){
        Intent map = new Intent(this,MapsActivity.class);
        Bundle b=new Bundle();
        System.out.println("List sent by rohit"+listLocations);
        b.putStringArray("LOCATIONS_LIST", listLocations);
        b.putString("TITLE",title);
        map.putExtras(b);
        startActivity(map);
    }

    public void result(View view){

        //put keyboard down
        InputMethodManager inputManager = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);

        inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);
        // make a toas
        Toast.makeText(MainActivity.this,"Searching for movie : "+actv.getText()+". . .", Toast.LENGTH_SHORT).show();
        //Fetch movie name to be searched
        String stringUrl = actv.getText().toString();
        String url ="https://data.sfgov.org/resource/wwmu-gmzc.json?$$app_token=EZYRhhwbJf7tNsxEcnBI3g4dF&title=";
        //url encoding for eliminating spaces
        try {
              stringUrl = URLEncoder.encode(stringUrl, "UTF-8");
              System.out.println(stringUrl);
        }catch(Exception e){
              System.out.println("Exception : "+ e);
        }
        //make final url string
        url += stringUrl;
        //Check network state either internet is available or not
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            //run async task and fetch data for particular movie
            new fetchData().execute(url);
            //re.setText("");
        } else {
            //internet connection not available notify user via snack bar
            Snackbar.make(view, "Internet Connection not available", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }
    }
   private class fetchData extends AsyncTask<String,Void,Void>{
        //background networking function async task to fetch data
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setMessage("Loading...");
            progressDialog.show();

        }

        @Override
        protected Void doInBackground(String... urls) {
            //call API class getData method to fetch data & store in reader stream
            Reader reader=API.getData(urls[0]);
            //Parse multiple objects in Gson ( from json to java object)
            Type listType = new TypeToken<ArrayList<Movie>>(){}.getType();
            GsonBuilder gsonBuilder = new GsonBuilder();
            //gsonBuilder.registerTypeAdapter(listType, new MultimediaDeserializer());
            //Deserializing json
            try {
                Gson gson = new GsonBuilder().serializeNulls().create();
                movieArrayList = gson.fromJson(reader, listType);
            }catch(Exception e){
                System.out.println("exception" +e);
            }
            //Read data in list not used, changed with array list objects
            movieList=new StringBuffer();

            //read json objects
            int i = 0;
            for(Movie movie: movieArrayList){
                title = movie.getTitle();
                cast = movie.getCast();
                director = movie.getDirector();
                productionCompany = movie.getProduction_company();
                releaseYear = movie.getRelease_year();
                writer = movie.getWriter();
                listLocations[i]= movie.getLocations();
                i++;
                //Not used further
                movieList.append("\nLocation : "+movie.getLocations());
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            //on post method
            progressDialog.dismiss();
            txtTitle.setText(title);
            txtCast.setText(cast);
            txtDirector.setText(director);
            txtProductionCompany.setText(productionCompany);
            txtWriter.setText(writer);
            txtReleaseYear.setText(releaseYear.toString());

            //Enable google map button
            if(title !=null) {fab.setEnabled(true);}
            //list of locations
            txtPostList.setText(movieList);
            System.out.println(listLocations);
        }
    }

}
