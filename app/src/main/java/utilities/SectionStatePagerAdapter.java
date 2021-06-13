package utilities;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SectionStatePagerAdapter extends FragmentStatePagerAdapter {

    private final List<Fragment> mFragmentlist= new ArrayList<>();
    private final HashMap<Fragment , Integer> mFragments = new HashMap<>();
    private final HashMap<String , Integer> mFragmentNumbers = new HashMap<>();
    private final HashMap<Integer , String> mFragmentNames = new HashMap<>();

    public SectionStatePagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    public SectionStatePagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return mFragmentlist.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentlist.size() ;
    }

    public void addFragment(Fragment fragment, String fragmentName){
        mFragmentlist.add(fragment);
        mFragments.put(fragment , mFragmentlist.size()-1);
        mFragmentNumbers.put(fragmentName , mFragmentlist.size()-1);
        mFragmentNames.put(mFragmentlist.size()-1, fragmentName);
    }

    public Integer getFragmentNumber(String fragmentName){
        if(mFragmentNumbers.containsKey(fragmentName)){
            return mFragmentNumbers.get(fragmentName);
        }
        else
        {
            return null;
        }
    }

    public Integer getFragmentNumber(Fragment fragment){
        if(mFragments.containsKey(fragment)){
            return mFragments.get(fragment);
        }
        else
        {
            return null;
        }
    }

    public String getFragmentName(Integer fragment_position){
        if(mFragmentNames.containsKey(fragment_position)){
            return mFragmentNames.get(fragment_position);
        }
        else
        {
            return null;
        }
    }
}
