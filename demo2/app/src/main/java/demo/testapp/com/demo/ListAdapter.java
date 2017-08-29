package demo.testapp.com.demo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by sawkat.ali on 8/29/2017.
 */

public class ListAdapter extends BaseAdapter {
    private List<FlightDetails> flightDetailsList;
    private Context context;

    public ListAdapter(List<FlightDetails> flightDetailsList, Context context){
        this.flightDetailsList = flightDetailsList;
        this.context = context;


    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public int getCount() {
        return flightDetailsList.size();
    }

    @Override
    public View getView(int pos, View view, ViewGroup viewGroup) {
        //Avoiding creating view instanch on scroll
        EventHolder holder;
        if(view==null){
            view = LayoutInflater.from(context).inflate(R.layout.flight_item,viewGroup,false);
            holder = new EventHolder();

            holder.flight_number = (TextView)view.findViewById(R.id.flight_number);
            holder.flight_launch = (TextView)view.findViewById(R.id.flight_launch);

            view.setTag(holder);
        }else {
            holder = (EventHolder) view.getTag();
        }
        FlightDetails flightDetails = flightDetailsList.get(pos);
        holder.flight_number.setText(flightDetails.getFlight_number());
        holder.flight_launch.setText(flightDetails.getLaunch_year());
        return view;
    }

    public static class EventHolder{
        private TextView flight_number,flight_launch;
    }
}
