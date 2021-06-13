package Profile;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.example.no4.R;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import java.util.ArrayList;

import utilities.BottomNavigationViewHelper;
import utilities.SectionStatePagerAdapter;

public class AccountSettingActivity extends AppCompatActivity {
    private static final String TAG = "AccountSettingActiv";
    private Context mContext = AccountSettingActivity.this;
    private static final int ACTIVITY_NUM = 4;
    private SectionStatePagerAdapter pagerAdapter;
    private ViewPager mViewpager;
    private RelativeLayout mRelativeLayout;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: started");
        setContentView(R.layout.activity_account_settings);
        mViewpager = (ViewPager) findViewById(R.id.container);
        mRelativeLayout = (RelativeLayout) findViewById(R.id.relLayout1_usable);
        setupBottomNavigationView();
        setupToolbar();
        setupFragments();
        setupSettingsList();
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

    private void setupSettingsList()
    {
        Log.d(TAG, "setupSettingsList: Initialising account settings list");
        ListView listView = (ListView) findViewById(R.id.listview_account_settings);
        ArrayList<String> options = new ArrayList<>();
        options.add(getString(R.string.edit_profile));
        options.add(getString(R.string.log_out));

        ArrayAdapter adapter = new ArrayAdapter<>(mContext, android.R.layout.simple_list_item_1, options);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d(TAG, "onItemClick: navigating to fragment#" + position);
                setupViewPager(position);
            }
        });
    }

    private void setupFragments()
    {
        pagerAdapter = new SectionStatePagerAdapter(getSupportFragmentManager());
        pagerAdapter.addFragment(new EditProfileFragment(),getString(R.string.edit_profile)); //fragment 0
        pagerAdapter.addFragment(new LogOutFragment(),getString(R.string.log_out));//fragment 1
        //ViewPager viewPager = (ViewPager) findViewById(R.id.container);
    }

    private void setupViewPager(int fragmentnumber)
    {
        mRelativeLayout.setVisibility(View.GONE); // when sets up earlier screen should go and be replaced by fragment screen
        Log.d(TAG, "setupViewPager: navigating to fragment no" + fragmentnumber);
        System.out.println(pagerAdapter);
        mViewpager.setAdapter(pagerAdapter);
        mViewpager.setCurrentItem(fragmentnumber);
    }




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

        ImageView profile_menu2 = (ImageView) findViewById(R.id.profile_menu2);
        profile_menu2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: Navigating to Account Settings");
                Intent intent = new Intent(mContext, profileactivity.class);
                startActivity(intent);
            }
        });
    }

}
