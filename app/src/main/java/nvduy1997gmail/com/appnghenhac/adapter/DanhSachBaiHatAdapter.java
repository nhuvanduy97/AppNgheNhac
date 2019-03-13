package nvduy1997gmail.com.appnghenhac.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import nvduy1997gmail.com.appnghenhac.activity.PlayNhacActivity;
import nvduy1997gmail.com.appnghenhac.model.BaiHat;
import nvduy1997gmail.com.appnghenhac.R;
import nvduy1997gmail.com.appnghenhac.service.APIService;
import nvduy1997gmail.com.appnghenhac.service.DataService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhSachBaiHatAdapter extends RecyclerView.Adapter<DanhSachBaiHatAdapter.viewHolder> {

    private Context context;
    private ArrayList<BaiHat> baiHatArrayList;

    public DanhSachBaiHatAdapter(Context context, ArrayList<BaiHat> baiHatArrayList) {
        this.context = context;
        this.baiHatArrayList = baiHatArrayList;
    }

    @Override
    public viewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_danhsachbaihat, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(viewHolder holder, int position) {
        BaiHat baiHat = baiHatArrayList.get(position);
        holder.txtViewTenCaSi.setText(baiHat.getCasi());
        holder.txtViewTenBaiHat.setText(baiHat.getTenbaihat());
        holder.txtViewIndex.setText(position + 1 + " ");


    }

    @Override
    public int getItemCount() {
        return baiHatArrayList.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {

        TextView txtViewIndex, txtViewTenBaiHat, txtViewTenCaSi;
        ImageView imgLike;

        public viewHolder(View itemView) {
            super(itemView);
            txtViewTenBaiHat = itemView.findViewById(R.id.txtViewTenBaiHatDanhSach);
            txtViewTenCaSi = itemView.findViewById(R.id.txtViewTenCaSiDanhSach);
            txtViewIndex = itemView.findViewById(R.id.txtViewIndex);
            imgLike = itemView.findViewById(R.id.imgViewLuotThichDanhSach);
            imgLike.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    imgLike.setImageResource(R.drawable.iconloved);
                    DataService dataService = APIService.getService();
                    Call<String> callback = dataService.upDateLuotThich("1", baiHatArrayList.get(getPosition()).getIdbaihat());
                    callback.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            String kq = response.body();
                            if (kq.equals("Success")) {
                                Toast.makeText(context, "Đã Thích", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(context, "Lỗi", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {

                        }
                    });
                    imgLike.setEnabled(false);
                }
            });
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context,PlayNhacActivity.class);
                    intent.putExtra("cakhuc",baiHatArrayList.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
