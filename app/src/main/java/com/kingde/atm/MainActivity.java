package com.kingde.atm;

import android.arch.core.util.Function;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_LOGIN = 100;
    private static final String TAG = MainActivity.class.getSimpleName();
    boolean logon = false;
    private List<features> featuresList;
    //    String[] features = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(!logon) {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
//            startActivity(intent);
            startActivityForResult(intent, REQUEST_LOGIN);
        }
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        //Recycler
        setupFeatures();
        RecyclerView recyclerView = findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);
//        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this)); //Linear排列 水平或是垂直
        recyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this, 3)); //需要多一個欄位數量的參數
        //Adaptor
//        FeatureAdapter adapter = new FeatureAdapter(MainActivity.this);
        IconAdapter adapter = new IconAdapter();
        recyclerView.setAdapter(adapter);
    }

    private void setupFeatures() {
        //先建立features類別的List
        featuresList = new ArrayList<>();
        String[] features = getResources().getStringArray(R.array.features);
        featuresList.add(new features(features[0], R.drawable.feature_transaction));
        featuresList.add(new features(features[1], R.drawable.feature_balance));
        featuresList.add(new features(features[2], R.drawable.feature_finance));
        featuresList.add(new features(features[3], R.drawable.feature_contact));
        featuresList.add(new features(features[4], R.drawable.feature_exit));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_LOGIN){
            if(resultCode!=RESULT_OK){
                finish();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public class IconAdapter extends RecyclerView.Adapter<IconAdapter.IconViewHolder> {
        @NonNull
        @Override
        public IconViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View view = getLayoutInflater().inflate(R.layout.item_icon, viewGroup, false);
            return new IconViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull IconViewHolder iconViewHolder, int i) {
            final features feature = featuresList.get(i);
            iconViewHolder.nameText.setText(feature.getName());
            iconViewHolder.iconImage.setImageResource(feature.getIcon());
            iconViewHolder.itemView.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {
                    iconClick(feature);
                }
            });
        }

        @Override
        public int getItemCount() {
            return featuresList.size();
        }

        public class IconViewHolder extends RecyclerView.ViewHolder {
            ImageView iconImage;
            TextView nameText;

            public IconViewHolder(@NonNull View itemView) {
                super(itemView);
                iconImage = itemView.findViewById(R.id.item_icon);
                nameText  = itemView.findViewById(R.id.item_name);
            }
        }
    }

    private void iconClick(features feature) {
        Log.d(TAG, "iconClick: " + feature.getName());
        switch (feature.getIcon()){
            case R.drawable.feature_transaction:
                break;
            case R.drawable.feature_balance:
                break;
            case R.drawable.feature_finance:
                break;
            case R.drawable.feature_contact:
                break;
            case R.drawable.feature_exit:
                finish();
                break;
        }
    }
}
