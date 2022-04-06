package com.example.journeyjournal.presentation.dashboard.adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.journeyjournal.R;
import com.example.journeyjournal.framework.database.User;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import de.hdodenhof.circleimageview.CircleImageView;

public class FactRecyclerAdapter extends FirebaseRecyclerAdapter<User, FactRecyclerAdapter.myViewHolder> {


    public FactRecyclerAdapter(@NonNull FirebaseRecyclerOptions<User> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull User model) {
        holder.title.setText(model.getTitle());
        holder.description.setText(model.getDescription());

        Glide.with(holder.img.getContext()).load(model.getSurl())
                .placeholder(R.drawable.ic_launcher_foreground)
                .circleCrop().error(R.drawable.ic_baseline_person_24)
                .into(holder.img);

    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_fact_layout,parent,false);
        return null;
    }

    class myViewHolder extends RecyclerView.ViewHolder{

        CircleImageView img;
        TextView title, description;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            img = (CircleImageView) itemView.findViewById(R.id.img1);
            title = (TextView) itemView.findViewById(R.id.title_text_card);
            description = (TextView) itemView.findViewById(R.id.description_card);


        }
    }
}
