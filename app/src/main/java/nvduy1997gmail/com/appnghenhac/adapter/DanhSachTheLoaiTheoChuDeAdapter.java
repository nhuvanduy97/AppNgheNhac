package nvduy1997gmail.com.appnghenhac.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import nvduy1997gmail.com.appnghenhac.activity.DanhSachBaiHatActivity;
import nvduy1997gmail.com.appnghenhac.model.TheLoai;
import nvduy1997gmail.com.appnghenhac.R;

public class DanhSachTheLoaiTheoChuDeAdapter extends RecyclerView.Adapter<DanhSachTheLoaiTheoChuDeAdapter.viewHolder>{

    private Context context;
    private ArrayList<TheLoai> theLoaiArrayList;

    public DanhSachTheLoaiTheoChuDeAdapter(Context context, ArrayList<TheLoai> theLoaiArrayList) {
        this.context = context;
        this.theLoaiArrayList = theLoaiArrayList;
    }

    @Override
    public viewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_danhsachtheloaithechude,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(viewHolder holder, int position) {
        TheLoai theLoai = theLoaiArrayList.get(position);
        Picasso.with(context).load(theLoai.getHinhtheloai()).into(holder.imgviewTheLoaiTheoChuDe);
        holder.txtViewTenTheLoai.setText(theLoai.getTentheloai());
    }

    @Override
    public int getItemCount() {
        return theLoaiArrayList.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {

        ImageView imgviewTheLoaiTheoChuDe;
        TextView txtViewTenTheLoai;
        public viewHolder(View itemView) {
            super(itemView);
            imgviewTheLoaiTheoChuDe = itemView.findViewById(R.id.imgviewTheLoaiTheoChuDe);
            txtViewTenTheLoai = itemView.findViewById(R.id.txtviewTheLoaiTheoChuDe);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context,DanhSachBaiHatActivity.class);
                    intent.putExtra("idtheloai",theLoaiArrayList.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
