package nvduy1997gmail.com.appnghenhac.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import nvduy1997gmail.com.appnghenhac.activity.DanhSachTheLoaiTheoChuDeActivity;
import nvduy1997gmail.com.appnghenhac.model.ChuDe;
import nvduy1997gmail.com.appnghenhac.R;

public class DanhSachChuDeAdapter extends RecyclerView.Adapter<DanhSachChuDeAdapter.viewHolder>{
    private Context context;
    private ArrayList<ChuDe> chuDeArrayList;

    public DanhSachChuDeAdapter(Context context, ArrayList<ChuDe> chuDeArrayList) {
        this.context = context;
        this.chuDeArrayList = chuDeArrayList;
    }

    @Override
    public viewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_danhsachtatcachude,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(viewHolder holder, int position) {
        ChuDe chuDe = chuDeArrayList.get(position);
        Picasso.with(context).load(chuDe.getHinhchude()).into(holder.imageViewDanhSachChuDe);
    }

    @Override
    public int getItemCount() {
        return chuDeArrayList.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        ImageView imageViewDanhSachChuDe;
        public viewHolder(View itemView) {
            super(itemView);
            imageViewDanhSachChuDe = itemView.findViewById(R.id.imgViewDongDanhsachcachude);
            imageViewDanhSachChuDe.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context,DanhSachTheLoaiTheoChuDeActivity.class);
                    intent.putExtra("idchude",chuDeArrayList.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
