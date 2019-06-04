package com.kingde.atm;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kingde.atm.ViewHolder.FeatureViewHolder;

public class FeatureAdapter extends RecyclerView.Adapter<FeatureViewHolder> {
    private final String[] features;
    Context context;

    public FeatureAdapter(Context context) {
        this.context = context;
        features = context.getResources().getStringArray(R.array.features);
    }

    @NonNull
    @Override
    public FeatureViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(
                android.R.layout.simple_list_item_1, viewGroup, false
        );
            return new FeatureViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FeatureViewHolder featureViewHolder, int i) {
        featureViewHolder.getNameText().setText(features[i]);
    }

    @Override
    public int getItemCount() {
        return features.length;
    }
}
