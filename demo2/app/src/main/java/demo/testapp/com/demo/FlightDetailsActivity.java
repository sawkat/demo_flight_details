package demo.testapp.com.demo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class FlightDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flight_details);

        TextView links = (TextView)findViewById(R.id.links);

        Intent intent = getIntent();
        if(intent!=null){
            ArrayList<String> linksVal = intent.getStringArrayListExtra("LINKS");
            String linksDetails = "";
            for(int i =0; i<linksVal.size();i++){
                linksDetails += linksVal.get(i)+"\n\n";
            }
            links.setText(linksDetails);
        }
    }
}
