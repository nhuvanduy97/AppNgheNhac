package nvduy1997gmail.com.appnghenhac.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import nvduy1997gmail.com.appnghenhac.adapter.DanhSachPlayListAdapter;
import nvduy1997gmail.com.appnghenhac.model.PlayList;
import nvduy1997gmail.com.appnghenhac.R;
import nvduy1997gmail.com.appnghenhac.service.APIService;
import nvduy1997gmail.com.appnghenhac.service.DataService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhSachPlayListActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private RecyclerView recyclerViewDanhSachCacPlayList;
    private DanhSachPlayListAdapter danhSachPlayListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_play_list);
        anhxa();
        init();
        getData();
    }

    private void getData() {
        DataService dataService = APIService.getService();
        Call<List<PlayList>> callback = dataService.getDanhSachPlayList();
        callback.enqueue(new Callback<List<PlayList>>() {
            @Override
            public void onResponse(Call<List<PlayList>> call, Response<List<PlayList>> response) {
                ArrayList<PlayList> playListArrayList = (ArrayList<PlayList>) response.body();
                danhSachPlayListAdapter = new DanhSachPlayListAdapter(DanhSachPlayListActivity.this, playListArrayList);
                recyclerViewDanhSachCacPlayList.setLayoutManager(new GridLayoutManager(DanhSachPlayListActivity.this, 2));
                recyclerViewDanhSachCacPlayList.setAdapter(danhSachPlayListAdapter);
            }

            @Override
            public void onFailure(Call<List<PlayList>> call, Throwable t) {

            }
        });
    }

    private void init() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Play List");
        toolbar.setTitleTextColor(getResources().getColor(R.color.mautim));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void anhxa() {
        toolbar = findViewById(R.id.toolbarDanhSachCacPlayList);
        recyclerViewDanhSachCacPlayList = findViewById(R.id.recyclerviewDanhSachPlayList);
    }
}
