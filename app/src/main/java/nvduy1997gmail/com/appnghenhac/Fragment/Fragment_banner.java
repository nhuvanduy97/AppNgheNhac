package nvduy1997gmail.com.appnghenhac.Fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;
import nvduy1997gmail.com.appnghenhac.Adapter.BannerAdapter;
import nvduy1997gmail.com.appnghenhac.Model.QuangCao;
import nvduy1997gmail.com.appnghenhac.R;
import nvduy1997gmail.com.appnghenhac.Service.APIService;
import nvduy1997gmail.com.appnghenhac.Service.DataService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_banner extends Fragment {

    View view;
    ViewPager viewPager;
    CircleIndicator circleIndicater;
    BannerAdapter bannerAdapter;
    Runnable runnable;
    Handler handler;
    int currentItem;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_banner,container,false);
        anhxa();
        getData();
        return view;
    }
    private void anhxa() {
        viewPager =getActivity().findViewById(R.id.viewpager);
        circleIndicater = getActivity().findViewById(R.id.indicatordefault);

    }

    private void getData() {
        DataService dataService = APIService.getService();
        Call<List<QuangCao>> callBack = dataService.getDataBanner();
        callBack.enqueue(new Callback<List<QuangCao>>() {
            @Override
            public void onResponse(Call<List<QuangCao>> call, Response<List<QuangCao>> response) {
                ArrayList<QuangCao> banners = (ArrayList<QuangCao>) response.body();
                anhxa();
                bannerAdapter = new BannerAdapter(getActivity(),banners);
                viewPager.setAdapter(bannerAdapter);
                circleIndicater.setViewPager(viewPager);
                handler = new Handler();
                runnable = new Runnable() {
                    @Override
                    public void run() {
                           currentItem = viewPager.getCurrentItem();
                           currentItem++;
                           if (currentItem >= viewPager.getAdapter().getCount()){
                               currentItem =0;
                           }
                           viewPager.setCurrentItem(currentItem,true);
                           handler.postDelayed(runnable,4500);

                    }
                };
                handler.postDelayed(runnable,4500);

            }

            @Override
            public void onFailure(Call<List<QuangCao>> call, Throwable t) {

            }
        });
    }
}
