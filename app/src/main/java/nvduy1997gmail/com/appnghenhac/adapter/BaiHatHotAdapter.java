package nvduy1997gmail.com.appnghenhac.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import nvduy1997gmail.com.appnghenhac.activity.PlayNhacActivity;
import nvduy1997gmail.com.appnghenhac.model.BaiHat;
import nvduy1997gmail.com.appnghenhac.R;
import nvduy1997gmail.com.appnghenhac.service.APIService;
import nvduy1997gmail.com.appnghenhac.service.DataService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BaiHatHotAdapter extends RecyclerView.Adapter<BaiHatHotAdapter.ViewHolder> {

    private Context context;
    private ArrayList<BaiHat> mangBaiHat;

    public BaiHatHotAdapter(Context context, ArrayList<BaiHat> mangBaiHat) {
        this.context = context;
        this.mangBaiHat = mangBaiHat;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.dong_baihathot, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        BaiHat baiHat = mangBaiHat.get(position);
        holder.textViewTenBh.setText(baiHat.getTenbaihat());
        holder.textViewTenCasi.setText(baiHat.getTenbaihat());
        Picasso.with(context).load(baiHat.getHinhbaihat()).into(holder.imgHinhBaiHat);
    }

    @Override
    public int getItemCount() {
        return mangBaiHat.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewTenBh, textViewTenCasi;
        ImageView imgHinhBaiHat, imgLike;

        public ViewHolder(View itemView) {
            super(itemView);
            textViewTenBh = itemView.findViewById(R.id.txtViewTenBaiHatHot);
            textViewTenCasi = itemView.findViewById(R.id.txtViewTenCaSiBaihatHot);
            imgHinhBaiHat = itemView.findViewById(R.id.imgViewBaiHatHot);
            imgLike = itemView.findViewById(R.id.imgviewlike);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context,PlayNhacActivity.class);
                    intent.putExtra("cakhuc",mangBaiHat.get(getPosition()));
                    Log.e("BaiHatHotAdapter", "onClick: "+mangBaiHat.get(getPosition()).getTenbaihat() );
                    context.startActivity(intent);
                }
            });
            imgLike.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    imgLike.setImageResource(R.drawable.iconloved);
                    DataService dataService = APIService.getService();
                    Call<String> callback = dataService.upDateLuotThich("1", mangBaiHat.get(getPosition()).getIdbaihat());
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
        }
    }
}
