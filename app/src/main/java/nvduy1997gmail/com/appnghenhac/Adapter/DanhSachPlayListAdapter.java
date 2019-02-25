package nvduy1997gmail.com.appnghenhac.Adapter;

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

import nvduy1997gmail.com.appnghenhac.Activity.DanhSachBaiHatActivity;
import nvduy1997gmail.com.appnghenhac.Activity.DanhSachPlayListActivity;
import nvduy1997gmail.com.appnghenhac.Model.PlayList;
import nvduy1997gmail.com.appnghenhac.R;

public class DanhSachPlayListAdapter extends RecyclerView.Adapter<DanhSachPlayListAdapter.viewHolder>{

    Context context;
    ArrayList<PlayList> playListArrayList;

    public DanhSachPlayListAdapter(Context context, ArrayList<PlayList> playListArrayList) {
        this.context = context;
        this.playListArrayList = playListArrayList;
    }

    @Override
    public viewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_danhsachplaylist,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(viewHolder holder, int position) {
        PlayList playList = playListArrayList.get(position);
        holder.txtTenPlayList.setText(playList.getTen());
        Picasso.with(context).load(playList.getHinhAnh()).into(holder.imgHinhNen);

    }

    @Override
    public int getItemCount() {
        return playListArrayList.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        ImageView imgHinhNen;
        TextView txtTenPlayList;

        public viewHolder(View itemView) {
            super(itemView);
            imgHinhNen = itemView.findViewById(R.id.imgviewDanhSachPlayList);
            txtTenPlayList = itemView.findViewById(R.id.txtViewTenDanhSachCacPlayList);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context,DanhSachBaiHatActivity.class);
                    intent.putExtra("itemplaylist",playListArrayList.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
