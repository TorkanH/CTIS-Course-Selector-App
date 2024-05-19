package com.seyyedehtorkanhesari.seyyedehtorkanhesarihw2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomRecyclerViewAdapter extends RecyclerView.Adapter<CustomRecyclerViewAdapter.ItemViewHolder> {
    Context context;

    public CustomRecyclerViewAdapter(Context context, ArrayList<SocialMedia> data) {
        this.context = context;
        Commons.data = (ArrayList<SocialMedia>) data;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.recyclerview_layout, parent, false);
        return new ItemViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        //BIND DATA
        final SocialMedia med = Commons.data.get(position);
        holder.tvName.setText(med.getMediaName() + "\n");
        if (med.getMediaName().equalsIgnoreCase("Web"))
            holder.icon.setImageResource(R.drawable.ctis256);
        else if (med.getMediaName().equalsIgnoreCase("Data Analysis"))
            holder.icon.setImageResource(R.drawable.ctis365);
        else if (med.getMediaName().equalsIgnoreCase("Software"))
            holder.icon.setImageResource(R.drawable.ctis359);
        else if (med.getMediaName().equalsIgnoreCase("Mobile"))
            holder.icon.setImageResource(R.drawable.ctis487);
        else
            holder.icon.setImageResource(R.drawable.noimage);
        holder.btnDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String msg = " Course Code: " + med.getId()
                        + "\n Course Title: " + med.getMediaName()
                        + "\n Section Number: " + med.getNumOfLike()
                        + "\n Number Of Credits: " + med.getNumOfComment();
                ((SecondActivity) context).displayDialog(msg);
            }
        });
    }

    @Override
    public int getItemCount() {
        return Commons.data.size();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder{
        ImageButton btnDetail;
        TextView tvName;
        ImageView icon;
        ItemViewHolder(View viewItem){
            super(viewItem);
            tvName = viewItem.findViewById(R.id.tvName);
            icon = viewItem.findViewById(R.id.imgIcon);
            btnDetail = viewItem.findViewById(R.id.btnDetail);
        }
    }


}
