package nvduy1997gmail.com.appnghenhac.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import java.util.ArrayList;

import nvduy1997gmail.com.appnghenhac.Fragment.Fragment_DiaNhac;
import nvduy1997gmail.com.appnghenhac.Fragment.Fragment_PlayDanhSachBaiHat;

public class ViewPagerPlayListNhac extends FragmentPagerAdapter {
    public final ArrayList<Fragment> fragmentArrayList = new ArrayList<>();
    public ViewPagerPlayListNhac(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
//        switch (position){
//            case 0: return new Fragment_PlayDanhSachBaiHat();
//            case 1: return new Fragment_DiaNhac();
//        }
        return fragmentArrayList.get(position);
       // return null;
    }
    @Override
    public int getCount() {
        return fragmentArrayList.size();
       // return 2;
    }

    public void addFragment(Fragment fragment){
        fragmentArrayList.add(fragment);
    }
}
