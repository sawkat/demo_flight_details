package demo.testapp.com.demo;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private ListAdapter listAdapter;
    private List<FlightDetails> flightDetailsList = new ArrayList<>();
    private ProgressDialog pd;
    private String url = "http://api.spacexdata.com/v1/launches";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Flight List");
        setSupportActionBar(toolbar);

        listView = (ListView) findViewById(R.id.mylist);
        //progress bar
        pd = new ProgressDialog(MainActivity.this);
        pd.setMessage("Loading....");
        //pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);

        // Asyntask
        new GetFlightDetails().execute();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                Intent mintent = new Intent(MainActivity.this,FlightDetailsActivity.class);
                FlightDetails fd = flightDetailsList.get(pos);
                mintent.putStringArrayListExtra("LINKS",fd.getLinks());
                startActivity(mintent);
            }
        });
    }

    public class GetFlightDetails extends AsyncTask<Void,Void,List<FlightDetails>>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //Show loader
            pd.show();
        }

        @Override
        protected List<FlightDetails> doInBackground(Void... voids) {
            //Http client
            JSONArray jsonArray = GetJsonArrayClass.getJSONArrayfromURL(url);
            flightDetailsList.clear();
            int len = jsonArray.length();
            FlightDetails fd;
            JSONObject jObject;
            ArrayList<String> links;
            for(int i=0; i<len;i++){
                fd = new FlightDetails();
                links = new ArrayList<>();
                try {
                    jObject = jsonArray.getJSONObject(i);
                    fd.setFlight_number(jObject.getString("flight_number"));
                    fd.setLaunch_year(jObject.getString("launch_year"));

                    JSONObject jObjLinks = jObject.getJSONObject("links");
                    links.add(jObjLinks.getString("mission_patch"));
                    links.add(jObjLinks.getString("article_link"));
                    links.add(jObjLinks.getString("video_link"));
                    fd.setLinks(links);

                    flightDetailsList.add(fd);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            return flightDetailsList;
        }

        @Override
        protected void onPostExecute(List<FlightDetails> flightDetailsList) {
            super.onPostExecute(flightDetailsList);
            if(pd.isShowing()){
                pd.dismiss();
            }
            //Hide loader and set adapter
            listAdapter = new ListAdapter(flightDetailsList,MainActivity.this);
            listView.setAdapter(listAdapter);
        }
    }
}
