package nvduy1997gmail.com.appnghenhac.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import nvduy1997gmail.com.appnghenhac.adapter.MainViewPagerAdapter;
import nvduy1997gmail.com.appnghenhac.fragment.Fragment_Tim_Kiem;
import nvduy1997gmail.com.appnghenhac.fragment.Fragment_Trang_Chu;
import nvduy1997gmail.com.appnghenhac.R;

public class MainActivity extends AppCompatActivity {

    private TabLayout tableLayout;
    private ViewPager viewPager;


    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        anhxa();
        init();

    }

    private void init() {
        MainViewPagerAdapter mainViewPagerAdapter = new MainViewPagerAdapter(getSupportFragmentManager());
        mainViewPagerAdapter.addFragment(new Fragment_Trang_Chu(),"Trang Chủ");
        mainViewPagerAdapter.addFragment(new Fragment_Tim_Kiem(),"Tìm Kiếm");

        viewPager.setAdapter(mainViewPagerAdapter);
        tableLayout.setupWithViewPager(viewPager);
        tableLayout.getTabAt(0).setIcon(R.drawable.icontrangchu);
        tableLayout.getTabAt(1).setIcon(R.drawable.iconsearch);
    }

    private void anhxa() {
        tableLayout = findViewById(R.id.myTabLayout);
        viewPager = findViewById(R.id.myviewpager);
    }


}
