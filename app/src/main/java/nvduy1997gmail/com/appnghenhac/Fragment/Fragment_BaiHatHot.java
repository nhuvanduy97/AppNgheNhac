package nvduy1997gmail.com.appnghenhac.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import nvduy1997gmail.com.appnghenhac.Adapter.BaiHatHotAdapter;
import nvduy1997gmail.com.appnghenhac.Model.BaiHat;
import nvduy1997gmail.com.appnghenhac.R;
import nvduy1997gmail.com.appnghenhac.Service.APIService;
import nvduy1997gmail.com.appnghenhac.Service.DataService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_BaiHatHot extends Fragment {
    View view;
    RecyclerView recyclerViewBaihatHot;
    BaiHatHotAdapter baiHatHotAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_baihathot,container,false);
        anhxa();
        getData();
        return view;
    }

    private void anhxa() {
        recyclerViewBaihatHot = getActivity().findViewById(R.id.recyclerviewBaiHatHot);
    }

    private void getData() {
        DataService dataService = APIService.getService();
        Call<List<BaiHat>> callback = dataService.getListBaiHatHot();
        callback.enqueue(new Callback<List<BaiHat>>() {
            @Override
            public void onResponse(Call<List<BaiHat>> call, Response<List<BaiHat>> response) {
                ArrayList<BaiHat> baiHatArrayList = (ArrayList<BaiHat>) response.body();
                anhxa();
                baiHatHotAdapter = new BaiHatHotAdapter(getActivity(),baiHatArrayList);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerViewBaihatHot.setLayoutManager(linearLayoutManager);
                recyclerViewBaihatHot.setAdapter(baiHatHotAdapter);
            }

            @Override
            public void onFailure(Call<List<BaiHat>> call, Throwable t) {

            }
        });
    }
}
