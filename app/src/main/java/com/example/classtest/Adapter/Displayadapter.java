package com.example.classtest.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.classtest.EarthQuakUtils;
import com.example.classtest.R;
import com.example.classtest.pojo.Feature;
import com.example.classtest.pojo.Properties;
import com.example.classtest.pojo.ResponseModel;

import java.util.List;

public class Displayadapter extends RecyclerView.Adapter<Displayadapter.DisplayViewHolder> {
    private Context context;
    private List<Feature>featureList;
    private final String TAg="monjur";


    public Displayadapter(Context context, List<Feature> featureList) {
        this.context = context;
        this.featureList = featureList;
        Log.e(TAg,"on Constructor"+featureList.size());
    }

    @NonNull
    @Override
    public DisplayViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView= LayoutInflater.from(context).inflate(R.layout.row,parent,false);
        return new DisplayViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull DisplayViewHolder holder, int position) {
        Log.e(TAg,"on Bind View");
        holder.placeName.setText(featureList.get(position).getProperties().getPlace());
        holder.date.setText(EarthQuakUtils.getFormattedDateorTime(featureList.get(position).getProperties().getTime(),"hh:mm a,MMM dd yyyy"));
        holder.magnitude.setText(featureList.get(position).getProperties().getMag());

    }

    @Override
    public int getItemCount() {
        if (featureList.size()==0){
            Toast.makeText(context, "No Data found", Toast.LENGTH_LONG).show();
        }
        return featureList.size();
    }

    class DisplayViewHolder extends RecyclerView.ViewHolder{
        TextView placeName,date,magnitude;


        public DisplayViewHolder(@NonNull View itemView) {
            super(itemView);
            placeName=itemView.findViewById(R.id.placeNameTV);
            date=itemView.findViewById(R.id.dateTV);
            magnitude=itemView.findViewById(R.id.magnitudeTV);
        }
    }




}
