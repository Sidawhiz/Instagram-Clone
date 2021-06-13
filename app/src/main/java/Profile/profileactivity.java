package Profile;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.no4.R;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import java.util.ArrayList;

import utilities.BottomNavigationViewHelper;
import utilities.UniversalImageLoader;
import utilities.gridImageAdapter;

public class profileactivity extends AppCompatActivity {
    private static final String TAG = "profileActivity";
    private static final int ACTIVITY_NUM = 4;
    private Context mContext = profileactivity.this;
    private ProgressBar mProgressBar;
    private ImageView mProfilePhoto;
    private GridView gridView;
    private static final int GRID_NUM_COLUMNS = 2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Log.d(TAG, "onCreate search started");
        setActivityWidget();
        setupBottomNavigationView();
        setupToolbar();
        setProfileImage();
        tempGridSetup();
    }

    private void setupBottomNavigationView()
    {
        Log.d(TAG, "setupBottomNavigationView: setting up BottomNavigationView");
        BottomNavigationViewEx bottomNavigationViewEx = (BottomNavigationViewEx) findViewById(R.id.bottomNavViewBar);
        BottomNavigationViewHelper.setupBottomNavigationView(bottomNavigationViewEx);
        BottomNavigationViewHelper.enableNavigation(mContext, bottomNavigationViewEx);
        Menu menu = bottomNavigationViewEx.getMenu();
        MenuItem menuItem = menu.getItem(ACTIVITY_NUM);
        menuItem.setChecked(true);
    }

    // ---------- Responsible for setting up profile toolbar -----------
    @SuppressLint("NewApi")
    private void setupToolbar(){
        @SuppressLint({"NewApi", "LocalSuppress"}) Toolbar toolbar = (Toolbar) findViewById(R.id.profileToolBar);
        setSupportActionBar(toolbar);

        // This code is for Menu OPTION LIKE IN WHATSAPP , here we implemented imageview
        /**toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            //@SuppressLint("NonConstantResourceId")
            //@Override
            public boolean onMenuItemClick(MenuItem item) {
                Log.d(TAG, "onMenuItemClick: clicked menu item :" + item);
                switch (item.getItemId())
                {
                    case R.id.profile_menu:
                    Log.d(TAG, "onMenuItemClick: Navigating to profile settings");
                }
                return false;
            }
        }); */

        ImageView profile_menu = (ImageView) findViewById(R.id.profile_menu);
        profile_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: Navigating to Account Settings");
                Intent intent = new Intent(mContext, AccountSettingActivity.class);
                startActivity(intent);
            }
        });


    }

    private void setProfileImage(){
        Log.d(TAG, "setProfileImage: Setting Profile Image");
        String imgUrl = "https://www.pixsy.com/wp-content/uploads/2021/04/ben-sweet-2LowviVHZ-E-unsplash-1.jpeg";
        UniversalImageLoader.setImage(imgUrl, mProfilePhoto, mProgressBar,"");
    }

    private void setActivityWidget(){
        Log.d(TAG, "setActivityWidget: Update all activities");
        mProgressBar = (ProgressBar) findViewById(R.id.profile_progress_bar);
        mProgressBar.setVisibility(View.GONE);
        mProfilePhoto = (ImageView) findViewById((R.id.profile_image));
        gridView = (GridView) findViewById(R.id.gridview);
    }
    private void tempGridSetup()
    {
        ArrayList<String> imgURLs = new ArrayList<>();
        imgURLs.add("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRefq1lsSSoo2is2Y1k_W2PrcSOjGejtXWzMA&usqp=CAU");
        imgURLs.add("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS78gTgE9ShDQa3xec8HkCvh8wWMd0fhKA_YMibB_Om8sCq0D1xeAJlH6jUme5-U8UEq9Q&usqp=CAU");
        imgURLs.add("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQYJSVRPKEn4zQP2r6UTm8Ci7Tq4bfEvgzfnrtZMHeuN6nr1yc0Mtc8sAzqvWHncHVudn8&usqp=CAU");
        imgURLs.add("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRa5ELkKwCgpZLhhHzbxctkgzCaYLct0BVarpfC70qqsRU0r1Wz8GLUyPRAPYxUN-w9FLw&usqp=CAU");
        imgURLs.add("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRa__SlLplWo4maoQthKDhP4CiWY4DZWsc_Ow&usqp=CAU");
        imgURLs.add("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT7OlchWHpCgXUKNNapnkKxdU-gwGwewoZwPg&usqp=CAU");
        imgURLs.add("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQnsTTN9utsYz3yUc-yHN19bGKTc0pSkOWhAg&usqp=CAU");
        imgURLs.add("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ1Au2mHUvlcQ8EKMpMpHf-fbou6mEEyx-Gmg&usqp=CAU");
        imgURLs.add("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRPDCmY93Pbm1anrnuTE39tgSPyJumpSj79iQ&usqp=CAU");
        imgURLs.add("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRl9zP1zQ6T7KDAPNo-g72VIcohZBEFwLCjcg&usqp=CAU");

        setupImageGrid(imgURLs);

    }
    private void setupImageGrid(ArrayList<String> imgURLs){
        GridView gridView = (GridView) findViewById(R.id.gridview);
        gridImageAdapter adapter = new gridImageAdapter(mContext, R.layout.layout_grid_imageview, "", imgURLs);
        gridView.setAdapter(adapter);
        int gridWidth = getResources().getDisplayMetrics().widthPixels;
        int imageWidth = gridWidth / GRID_NUM_COLUMNS;
        gridView.setColumnWidth(imageWidth);
    }



    // If toolbar isn't working you haven't added this override
    //@Override
    //public boolean onCreateOptionsMenu(Menu menu) {
    //    getMenuInflater().inflate(R.menu.profile_menu,menu);
    //    return true;
    //}
}
