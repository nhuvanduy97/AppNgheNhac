package nvduy1997gmail.com.appnghenhac.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import nvduy1997gmail.com.appnghenhac.Adapter.DanhSachTheLoaiTheoChuDeAdapter;
import nvduy1997gmail.com.appnghenhac.Model.ChuDe;
import nvduy1997gmail.com.appnghenhac.Model.TheLoai;
import nvduy1997gmail.com.appnghenhac.R;
import nvduy1997gmail.com.appnghenhac.Service.APIService;
import nvduy1997gmail.com.appnghenhac.Service.DataService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhSachTheLoaiTheoChuDeActivity extends AppCompatActivity {

    ChuDe chuDe;
    RecyclerView recyclerViewTheLoaiTheoChuDe;
    Toolbar toolbarTheLoaiTheChuDe;
    DanhSachTheLoaiTheoChuDeAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_the_loai_theo_chu_de);
        GetIntent();
        anhxa();
        getData(chuDe.getIdchude());

    }

    private void getData(String idChude) {
        DataService dataService = APIService.getService();
        Call<List<TheLoai>> callback =dataService.getTheLoaiTheChuDe(idChude);
        callback.enqueue(new Callback<List<TheLoai>>() {
            @Override
            public void onResponse(Call<List<TheLoai>> call, Response<List<TheLoai>> response) {
                ArrayList<TheLoai> theLoaiArrayList = (ArrayList<TheLoai>) response.body();
                adapter = new DanhSachTheLoaiTheoChuDeAdapter(DanhSachTheLoaiTheoChuDeActivity.this,theLoaiArrayList);
                recyclerViewTheLoaiTheoChuDe.setLayoutManager(new GridLayoutManager(DanhSachTheLoaiTheoChuDeActivity.this,2));
                recyclerViewTheLoaiTheoChuDe.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<TheLoai>> call, Throwable t) {

            }
        });
    }

    private void anhxa() {
        recyclerViewTheLoaiTheoChuDe = findViewById(R.id.recyclerviewTheLoaiTheoChuDe);
        toolbarTheLoaiTheChuDe = findViewById(R.id.toolbarTheLoaiTheoChuDe);
        setSupportActionBar(toolbarTheLoaiTheChuDe);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(chuDe.getTenchude());
        toolbarTheLoaiTheChuDe.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void GetIntent() {
        Intent intent= getIntent();
        if (intent.hasExtra("idchude")){
            chuDe = (ChuDe) intent.getSerializableExtra("idchude");
        }
    }
}
