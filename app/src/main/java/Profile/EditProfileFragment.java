package Profile;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.no4.R;
import com.nostra13.universalimageloader.core.ImageLoader;

import utilities.UniversalImageLoader;

public class EditProfileFragment extends Fragment {
    private static final String TAG = "EditProfileFragment";
    private ImageView mProfilePhoto;
    
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_edit_profile,container,false);
        mProfilePhoto = (ImageView) view.findViewById((R.id.profile_photo));
        //initializeImageLoader(); ------moved to main_activity
        setProfileImage();

        ImageView back = (ImageView) view.findViewById(R.id.profile_menu);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: Navigating back to profile activity");
                getActivity().finish();

            }
        });
        return view;

    }

    //private void initializeImageLoader(){
    //    UniversalImageLoader universalImageLoader = new UniversalImageLoader(getActivity());
    //    ImageLoader.getInstance().init(universalImageLoader.getConfig());
    //}

    private void setProfileImage(){
        Log.d(TAG, "setProfileImage: Setting Profile Image");
        System.out.println(mProfilePhoto);
        String imgUrl = "https://www.pixsy.com/wp-content/uploads/2021/04/ben-sweet-2LowviVHZ-E-unsplash-1.jpeg";
        UniversalImageLoader.setImage(imgUrl, mProfilePhoto, null,"");
    }

}
