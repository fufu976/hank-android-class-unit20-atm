package com.kingde.atm;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class FeatureAdapter extends RecyclerView.Adapter<FeatureAdapter.FeatureViewHolder> {
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
        featureViewHolder.nameText.setText(features[i]);
    }

    @Override
    public int getItemCount() {
        return features.length;
    }

    public class FeatureViewHolder extends RecyclerView.ViewHolder{
        //Use a TextView in the ViewHolder to show the feature name
        TextView nameText;
        public FeatureViewHolder(@NonNull View itemView) {
            super(itemView);
            nameText = itemView.findViewById(android.R.id.text1);
        }
    }
}
