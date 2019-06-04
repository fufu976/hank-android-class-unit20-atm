package com.kingde.atm.ViewHolder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class FeatureViewHolder extends RecyclerView.ViewHolder{
    //Use a TextView in the ViewHolder to show the feature name
    TextView nameText;
    public FeatureViewHolder(@NonNull View itemView) {
        super(itemView);
        nameText = itemView.findViewById(android.R.id.text1);
    }

    public TextView getNameText() {
        return nameText;
    }

    public void setNameText(TextView nameText) {
        this.nameText = nameText;
    }
}