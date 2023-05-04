package com.ritik.whetherapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class WeatherRYAdapter extends RecyclerView.Adapter<WeatherRYAdapter.ViewHolder> {
    private Context context;
    private ArrayList<WeatherRYModel> weatherRYModelArrayList;

    public WeatherRYAdapter(Context context, ArrayList<WeatherRYModel> weatherRYModelArrayList) {
        this.context = context;
        this.weatherRYModelArrayList = weatherRYModelArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.weather_rv_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        WeatherRYModel model  = weatherRYModelArrayList.get(position);
        holder.temperatureTV.setText(model.getTemperature()+" °c");
//        Picasso.get().load("http:".concat(model.getIcon())).into(holder.conditionTV);
        if (holder.conditionTV != null) {
            Picasso.get().load("http:".concat(model.getIcon())).into(holder.conditionTV);
        }
        holder.windTV.setText(model.getWindSpeed()+"Km/h");
        SimpleDateFormat input = new SimpleDateFormat("yyy-MM-dd hh:mm");
        SimpleDateFormat output = new SimpleDateFormat("hh:mm aa");
        try {
            Date t = input.parse(model.getTime());
            holder.timeTV.setText(output.format(t));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return weatherRYModelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView windTV,temperatureTV,timeTV;
        private ImageView conditionTV;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            windTV=itemView.findViewById(R.id.idTVWindSpeed);
            temperatureTV=itemView.findViewById(R.id.idTVTemperature);
            timeTV=itemView.findViewById(R.id.idTVTime);
            conditionTV=itemView.findViewById(R.id.idTVCondition);

        }
    }
}
