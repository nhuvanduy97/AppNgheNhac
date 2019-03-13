package nvduy1997gmail.com.appnghenhac.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import nvduy1997gmail.com.appnghenhac.adapter.DanhSachChuDeAdapter;
import nvduy1997gmail.com.appnghenhac.model.ChuDe;
import nvduy1997gmail.com.appnghenhac.R;
import nvduy1997gmail.com.appnghenhac.service.APIService;
import nvduy1997gmail.com.appnghenhac.service.DataService;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhSachChuDeActivity extends AppCompatActivity {
    private RecyclerView recyclerViewAllChuDe;
    private Toolbar toolbarAllChuDe;
    private DanhSachChuDeAdapter danhSachChuDeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_chu_de);
        anhxa();
        getDaTa();
    }

    private void getDaTa() {
        DataService dataService = APIService.getService();
        retrofit2.Call<List<ChuDe>> callback = dataService.getAllChuDe();
        callback.enqueue(new Callback<List<ChuDe>>() {
            @Override
            public void onResponse(retrofit2.Call<List<ChuDe>> call, Response<List<ChuDe>> response) {
                ArrayList<ChuDe> chuDeArrayList = (ArrayList<ChuDe>) response.body();
                danhSachChuDeAdapter = new DanhSachChuDeAdapter(DanhSachChuDeActivity.this, chuDeArrayList);
                recyclerViewAllChuDe.setLayoutManager(new GridLayoutManager(DanhSachChuDeActivity.this, 1));
                recyclerViewAllChuDe.setAdapter(danhSachChuDeAdapter);


            }

            @Override
            public void onFailure(retrofit2.Call<List<ChuDe>> call, Throwable t) {

            }
        });
    }

    private void anhxa() {
        toolbarAllChuDe = findViewById(R.id.toolbarDanhSachChuDe);
        recyclerViewAllChuDe = findViewById(R.id.recyclerviewAllChuDe);
        setSupportActionBar(toolbarAllChuDe);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Tất Cả Chủ Đề");
        toolbarAllChuDe.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
