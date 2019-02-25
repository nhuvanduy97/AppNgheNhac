package nvduy1997gmail.com.appnghenhac.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import nvduy1997gmail.com.appnghenhac.Adapter.AlalbumAdapter;
import nvduy1997gmail.com.appnghenhac.Model.Album;
import nvduy1997gmail.com.appnghenhac.R;
import nvduy1997gmail.com.appnghenhac.Service.APIService;
import nvduy1997gmail.com.appnghenhac.Service.DataService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhSachAlbumActivity extends AppCompatActivity {

    RecyclerView recyclerViewAlbum;
    Toolbar toolbarAlbum;
    AlalbumAdapter alalbumAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_album);
        anhxa();
        getData();
    }

    private void getData() {
        DataService dataService = APIService.getService();
        Call<List<Album>> callback = dataService.getAlOfAlbum();
        callback.enqueue(new Callback<List<Album>>() {
            @Override
            public void onResponse(Call<List<Album>> call, Response<List<Album>> response) {
                ArrayList<Album> albumArrayList = (ArrayList<Album>) response.body();
                alalbumAdapter = new AlalbumAdapter(DanhSachAlbumActivity.this,albumArrayList);
                recyclerViewAlbum.setLayoutManager(new GridLayoutManager(DanhSachAlbumActivity.this,2));
                recyclerViewAlbum.setAdapter(alalbumAdapter);
            }

            @Override
            public void onFailure(Call<List<Album>> call, Throwable t) {

            }
        });
    }

    private void anhxa() {
        recyclerViewAlbum = findViewById(R.id.recyclerviewDanhSachAlAlbum);
        toolbarAlbum = findViewById(R.id.toolbarAlbum);
        setSupportActionBar(toolbarAlbum);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Tất Cả Các Album");
        toolbarAlbum.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
