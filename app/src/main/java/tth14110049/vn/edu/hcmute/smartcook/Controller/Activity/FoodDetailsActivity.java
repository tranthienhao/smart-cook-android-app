package tth14110049.vn.edu.hcmute.smartcook.Controller.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ToggleButton;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerFragment;
import com.google.android.youtube.player.YouTubePlayerView;


import java.util.ArrayList;
import java.util.List;

import tth14110049.vn.edu.hcmute.smartcook.Controller.Adapter.IngredientAdapter;
import tth14110049.vn.edu.hcmute.smartcook.Controller.Adapter.StepAdapter;
import tth14110049.vn.edu.hcmute.smartcook.Model.Ingredient;
import tth14110049.vn.edu.hcmute.smartcook.Model.Step;
import tth14110049.vn.edu.hcmute.smartcook.R;
import tth14110049.vn.edu.hcmute.smartcook.YoutubeConfig;

/**
 * Created by Hao Tran Thien on 5/8/2018.
 */

public class FoodDetailsActivity  extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {
    ImageButton btnBack;
    YouTubePlayerFragment youTubePlayerFragment;
    ToggleButton toggleStep, toggleVideo, toggleIngredient;
    LinearLayout layoutStep, layoutIngredient, layoutVideo;
    private RecyclerView recyclerStep;
    private RecyclerView recyclerIngredient;
    private List<Step> listStep = new ArrayList<>();
    private List<Ingredient> listIngredient = new ArrayList<>();
    private StepAdapter stepAdapter;
    private IngredientAdapter ingredientAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_details);

        //set the view
        btnBack = findViewById(R.id.btn_back);
        recyclerStep = findViewById(R.id.list_step);
        recyclerIngredient = findViewById(R.id.list_ingredient);
        toggleStep = findViewById(R.id.toggle_step);
        toggleVideo = findViewById(R.id.toggle_video);
        toggleIngredient = findViewById(R.id.toggle_ingredient);
        layoutStep = findViewById(R.id.layout_step);
        layoutIngredient = findViewById(R.id.layout_ingredient);
        layoutVideo = findViewById(R.id.layout_video);

        //youtube fragment
        youTubePlayerFragment = (YouTubePlayerFragment) getFragmentManager().findFragmentById(R.id.fragment_youtube_player);
        youTubePlayerFragment.initialize(YoutubeConfig.getApiKey(), this);

        //toggle click listener
        toggleStep.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
               if(isChecked){
                   toggleIngredient.setChecked(false);
                   toggleVideo.setChecked(false);
                   layoutStep.setVisibility(View.VISIBLE);
               }
               if(!isChecked){
                   layoutStep.setVisibility(View.GONE);
               }
            }
        });
        toggleIngredient.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
               if(isChecked){
                   toggleStep.setChecked(false);
                   toggleVideo.setChecked(false);
                   layoutIngredient.setVisibility(View.VISIBLE);
               }
               if(!isChecked){
                   layoutIngredient.setVisibility(View.GONE);
               }
            }
        });
        toggleVideo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
               if(isChecked){
                   toggleIngredient.setChecked(false);
                   toggleStep.setChecked(false);
                   layoutVideo.setVisibility(View.VISIBLE);
               }
               if(!isChecked){
                   layoutVideo.setVisibility(View.GONE);
               }
            }
        });

        //btn back
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        toggleStep.setChecked(true);
        prepareData();
    }

    private void prepareData() {
        Step step = new Step();
        listStep.add(step);
        listStep.add(step);
        listStep.add(step);
        listStep.add(step);
        recyclerStep.setLayoutManager(new GridLayoutManager(getBaseContext(), 1));
        stepAdapter = new StepAdapter(getBaseContext(),listStep);
        recyclerStep.setNestedScrollingEnabled(false);
        recyclerStep.setAdapter(stepAdapter);

        Ingredient ingredient = new Ingredient();
        listIngredient.add(ingredient);
        listIngredient.add(ingredient);
        listIngredient.add(ingredient);
        listIngredient.add(ingredient);
        recyclerIngredient.setLayoutManager(new GridLayoutManager(getBaseContext(), 1));
        ingredientAdapter = new IngredientAdapter(getBaseContext(),listIngredient);
        recyclerIngredient.setNestedScrollingEnabled(false);
        recyclerIngredient.setAdapter(ingredientAdapter);
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        youTubePlayer.cueVideo("K2c0DGzYRdc");
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        if (youTubeInitializationResult.isUserRecoverableError()) {
            youTubeInitializationResult.getErrorDialog(FoodDetailsActivity.this, 1);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1: {
                getYoutubePlayerProvider().initialize(YoutubeConfig.getApiKey(), this);
            }
        }
    }
    protected YouTubePlayer.Provider getYoutubePlayerProvider() {
        return (YouTubePlayerView) findViewById(R.id.fragment_youtube_player);
    }
}
