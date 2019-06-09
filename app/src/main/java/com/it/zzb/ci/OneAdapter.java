package com.it.zzb.ci;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.it.zzb.ci.gson.OneData;

import java.util.List;

/**
 * Created by zzb on 2017/12/30.
 */

public class OneAdapter extends RecyclerView.Adapter<OneAdapter.ViewHolder> {

    private Context context;

    private List<OneData> dataList;

    static class ViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        TextView textView;
        TextView time;
        public ViewHolder(View view){
            super(view);
            cardView = (CardView) view;
            textView = view.findViewById(R.id.info);
            time = view.findViewById(R.id.time);
        }
    }
    public OneAdapter(List<OneData> data){
        dataList = data;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (context == null){
            context = parent.getContext();
        }
        View view = LayoutInflater.from(context).inflate(R.layout.item,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        OneData oneData = dataList.get(position);
        holder.textView.setText(oneData.content);
        holder.time.setText(oneData.origin);

    }


    @Override
    public int getItemCount() {
        return dataList.size();
    }
}
