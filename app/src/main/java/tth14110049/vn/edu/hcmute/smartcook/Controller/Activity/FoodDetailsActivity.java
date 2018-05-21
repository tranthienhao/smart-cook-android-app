package tth14110049.vn.edu.hcmute.smartcook.Controller.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerFragment;
import com.google.android.youtube.player.YouTubePlayerView;
import com.squareup.picasso.Picasso;


import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tth14110049.vn.edu.hcmute.smartcook.Controller.Adapter.IngredientAdapter;
import tth14110049.vn.edu.hcmute.smartcook.Controller.Adapter.StepAdapter;
import tth14110049.vn.edu.hcmute.smartcook.Controller.Retrofit2.ApiClient;
import tth14110049.vn.edu.hcmute.smartcook.Controller.Retrofit2.ApiInterface;
import tth14110049.vn.edu.hcmute.smartcook.Model.Food;
import tth14110049.vn.edu.hcmute.smartcook.Model.Ingredient;
import tth14110049.vn.edu.hcmute.smartcook.Model.Step;
import tth14110049.vn.edu.hcmute.smartcook.R;
import tth14110049.vn.edu.hcmute.smartcook.YoutubeConfig;

/**
 * Created by Hao Tran Thien on 5/8/2018.
 */

public class FoodDetailsActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {
    ImageButton btnBack;
    int foodId;
    Button btnSubPerson, btnAddPerson;
    YouTubePlayerFragment youTubePlayerFragment;
    ToggleButton toggleStep, toggleVideo, toggleIngredient;
    LinearLayout layoutStep, layoutIngredient, layoutVideo;
    private RecyclerView recyclerStep;
    private RecyclerView recyclerIngredient;
    private StepAdapter stepAdapter;
    private IngredientAdapter ingredientAdapter;
    private TextView tvToolbarFoodName, tvFoodName, tvCategoryName, tvCookTime, tvDescription, tvNumberOfPeople;
    private ImageView ivFoodImage;
    private Food foodDetails;
    private List<Step> listStep = new ArrayList<>();
    private List<Ingredient> listIngredient = new ArrayList<>();
    private int numberOfPeople = 1; // số người ăn bữa
    private YouTubePlayer myYoutubePlayer;

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
        tvToolbarFoodName = findViewById(R.id.tv_toolbar_food_name);
        tvFoodName = findViewById(R.id.tv_food_name);
        tvCategoryName = findViewById(R.id.tv_category_name);
        tvCookTime = findViewById(R.id.tv_cook_time);
        tvDescription = findViewById(R.id.tv_food_description);
        tvNumberOfPeople = findViewById(R.id.tv_number_of_people);
        ivFoodImage = findViewById(R.id.iv_food_image);
        btnSubPerson = findViewById(R.id.btn_sub_person);
        btnAddPerson = findViewById(R.id.btn_add_person);

        //get extra
        Intent intent = getIntent();
        foodDetails = (Food) intent.getSerializableExtra("Food");
        if(foodDetails != null){
            listStep = foodDetails.getStep();
            listIngredient = foodDetails.getIngredient();
            //set data
            prepareAvailableData();
        }else {
            foodId = intent.getIntExtra("FoodId", -1);
            if(foodId > 0){
                prepareData();
            }
        }

        //youtube fragment
        youTubePlayerFragment = (YouTubePlayerFragment) getFragmentManager().findFragmentById(R.id.fragment_youtube_player);
        youTubePlayerFragment.initialize(YoutubeConfig.getApiKey(), this);

        //toggle click listener
        toggleStep.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    toggleIngredient.setChecked(false);
                    toggleVideo.setChecked(false);
                    layoutStep.setVisibility(View.VISIBLE);
                }
                if (!isChecked) {
                    layoutStep.setVisibility(View.GONE);
                }
            }
        });
        toggleIngredient.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    toggleStep.setChecked(false);
                    toggleVideo.setChecked(false);
                    layoutIngredient.setVisibility(View.VISIBLE);
                }
                if (!isChecked) {
                    layoutIngredient.setVisibility(View.GONE);
                }
            }
        });
        toggleVideo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    toggleIngredient.setChecked(false);
                    toggleStep.setChecked(false);
                    layoutVideo.setVisibility(View.VISIBLE);
                }
                if (!isChecked) {
                    layoutVideo.setVisibility(View.GONE);
                }
            }
        });

        //btn sub person click: Giảm số lượng người ăn
        btnSubPerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (numberOfPeople > 1) {
                    numberOfPeople--;
                    tvNumberOfPeople.setText("Cho " + numberOfPeople + " người");
                    ingredientAdapter = new IngredientAdapter(getBaseContext(), listIngredient, numberOfPeople);
                    recyclerIngredient.setAdapter(ingredientAdapter);
                }
            }
        });

        //btn add person click: Tăng số lượng người ăn
        btnAddPerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numberOfPeople++;
                tvNumberOfPeople.setText("Cho " + numberOfPeople + " người");
                ingredientAdapter = new IngredientAdapter(getBaseContext(), listIngredient, numberOfPeople);
                recyclerIngredient.setAdapter(ingredientAdapter);
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
    }
    // fuction set data nếu đã có sẵn Food
    private void prepareAvailableData() {
        //set text
        tvToolbarFoodName.setText(foodDetails.getName());
        tvFoodName.setText(foodDetails.getName());
        tvCategoryName.setText(foodDetails.getCategoryName());
        tvCookTime.setText(foodDetails.getCookingTime());
        tvDescription.setText(foodDetails.getDescription());

        //set food image
        if (foodDetails.getImage() != null)
            Picasso.with(getBaseContext())
                    .load(foodDetails.getImage())
                    .fit()
                    .centerCrop()
                    .into(ivFoodImage);

        listStep = foodDetails.getStep();
        listIngredient = foodDetails.getIngredient();

        //set Step Adapter
        recyclerStep.setLayoutManager(new GridLayoutManager(getBaseContext(), 1));
        stepAdapter = new StepAdapter(getBaseContext(), listStep);
        recyclerStep.setNestedScrollingEnabled(false);
        recyclerStep.setAdapter(stepAdapter);

        //set Ingredient Adapter
        recyclerIngredient.setLayoutManager(new GridLayoutManager(getBaseContext(), 1));
        ingredientAdapter = new IngredientAdapter(getBaseContext(), listIngredient, numberOfPeople);
        recyclerIngredient.setNestedScrollingEnabled(false);
        recyclerIngredient.setAdapter(ingredientAdapter);
    }
    //function get và set data từ server
    private void prepareData() {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<List<Food>> call = apiService.getFoodById(foodId);
        call.enqueue(new Callback<List<Food>>() {
            @Override
            public void onResponse(Call<List<Food>> call, Response<List<Food>> response) {
                foodDetails = response.body().get(0);
                //set data when get success
                prepareAvailableData();
                //set video player
                myYoutubePlayer.cueVideo(foodDetails.getVideo());
            }

            @Override
            public void onFailure(Call<List<Food>> call, Throwable t) {
                // Log error here since request failed
                Log.e("GET FOOD BY ID ERROR", t.toString());
                Toast.makeText(getBaseContext(),t.toString(),Toast.LENGTH_LONG).show();
            }
        });
    }
    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        myYoutubePlayer = youTubePlayer;
        //get youtube link
        if(foodDetails != null)
            youTubePlayer.cueVideo(foodDetails.getVideo());
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
