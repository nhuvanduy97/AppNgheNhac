package nvduy1997gmail.com.appnghenhac.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import nvduy1997gmail.com.appnghenhac.activity.DanhSachBaiHatActivity;
import nvduy1997gmail.com.appnghenhac.activity.DanhSachPlayListActivity;
import nvduy1997gmail.com.appnghenhac.adapter.PlayList_Adapter;
import nvduy1997gmail.com.appnghenhac.model.PlayList;
import nvduy1997gmail.com.appnghenhac.R;
import nvduy1997gmail.com.appnghenhac.service.APIService;
import nvduy1997gmail.com.appnghenhac.service.DataService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_PlayList extends Fragment {

    private View view;
    private ListView listView;
    private TextView txtviewTitlePlayList, txtviewXemThemPlayList;
    private PlayList_Adapter playList_adapter;
    private ArrayList<PlayList> playListArrayList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_playlist, container, false);
        anhxa1();
        getData();
        return view;
    }

    private void anhxa1() {
        listView = view.findViewById(R.id.listviewplaylist);
        txtviewTitlePlayList = view.findViewById(R.id.txtTitlePlayList);
        txtviewXemThemPlayList = view.findViewById(R.id.textviewmoreplaylist);

        txtviewXemThemPlayList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DanhSachPlayListActivity.class);
                startActivity(intent);
            }
        });
    }

    private void getData() {
//ông chạy tôi xem lỗi như nào cái
        DataService dataService = APIService.getService();
        Call<List<PlayList>> callback = dataService.getDataPlayList();
        callback.enqueue(new Callback<List<PlayList>>() {
            @Override
            public void onResponse(Call<List<PlayList>> call, Response<List<PlayList>> response) {
                playListArrayList = (ArrayList<PlayList>) response.body();
                anhxa1();
                playList_adapter = new PlayList_Adapter(getActivity(), android.R.layout.simple_list_item_1, playListArrayList);
                listView.setAdapter(playList_adapter);
                setListViewHeightBasedOnChildren(listView);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(getActivity(), DanhSachBaiHatActivity.class);
                        intent.putExtra("itemplaylist", playListArrayList.get(position));
                        startActivity(intent);
                    }
                });
            }

            @Override
            public void onFailure(Call<List<PlayList>> call, Throwable t) {

            }
        });
    }

    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }

        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
        listView.requestLayout();
    }
}
