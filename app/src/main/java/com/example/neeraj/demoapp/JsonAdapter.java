package com.example.neeraj.demoapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by neeraj on 6/12/17.
 */

public class JsonAdapter extends RecyclerView.Adapter<JsonAdapter.MyViewHolder> {
    private  ArrayList<PojoClass> arrayList;

    public JsonAdapter(ArrayList<PojoClass> arrayList) {
        this.arrayList = arrayList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_layout, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
       holder.tv_name.setText(arrayList.get(position).getName());
       holder.tv_email.setText(arrayList.get(position).getEmail());
       holder.tv_mobile.setText(arrayList.get(position).getMobile());
    }
   //hi
    @Override
    public int getItemCount() {
        return arrayList.size();
    }


    public static final class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_name,tv_email,tv_mobile;
        public MyViewHolder(View itemView) {
            super(itemView);

            tv_name= itemView.findViewById(R.id.name);
            tv_email= itemView.findViewById(R.id.email);
            tv_mobile= itemView.findViewById(R.id.mobile);

        }
    }
}
