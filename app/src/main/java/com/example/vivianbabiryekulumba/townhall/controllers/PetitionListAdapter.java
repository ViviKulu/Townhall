package com.example.vivianbabiryekulumba.townhall.controllers;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.vivianbabiryekulumba.townhall.R;
import com.example.vivianbabiryekulumba.townhall.database.Petitions;

import java.util.Collections;
import java.util.List;

public class PetitionListAdapter extends RecyclerView.Adapter<PetitionListAdapter.PetitionListViewHolder> {

    private static final String TAG = "PetitionListAdapter";

    public class PetitionListViewHolder extends RecyclerView.ViewHolder {

        TextView petition_title;
        TextView petition_content;

        public PetitionListViewHolder(View itemView) {
            super(itemView);
            petition_title = itemView.findViewById(R.id.petition_list_title);
            petition_content = itemView.findViewById(R.id.petition_list_content);
        }
    }

    private LayoutInflater mInflater;
    private List<Petitions> mPetitions = Collections.emptyList();

    public PetitionListAdapter(Context context) { mInflater = LayoutInflater.from(context); }

    @NonNull
    @Override
    public PetitionListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.petition_list_item_view, parent, false);
        return new PetitionListViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PetitionListViewHolder holder, int position) {
        Petitions currentPetition = mPetitions.get(position);
        holder.petition_title.setText(currentPetition.getPetition_title());
        holder.petition_content.setText(currentPetition.getPetition_content());
        Log.d(TAG, "onBindViewHolder: " + currentPetition);
    }

    public void setPetitions(List<Petitions> petitions) {
        mPetitions = petitions;
        notifyDataSetChanged();
        Log.d(TAG, "setPetitions: " + mPetitions);
    }

    @Override
    public int getItemCount() {
        Log.d(TAG, "getItemCount: " + mPetitions.size());
        return mPetitions.size();
    }
}