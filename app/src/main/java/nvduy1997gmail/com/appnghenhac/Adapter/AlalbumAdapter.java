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
import java.util.ConcurrentModificationException;

import nvduy1997gmail.com.appnghenhac.Activity.DanhSachBaiHatActivity;
import nvduy1997gmail.com.appnghenhac.Model.Album;
import nvduy1997gmail.com.appnghenhac.R;

public class AlalbumAdapter extends RecyclerView.Adapter<AlalbumAdapter.viewHolder>{
    Context context;
    ArrayList<Album> albumArrayList;

    public AlalbumAdapter(Context context, ArrayList<Album> albumArrayList) {
        this.context = context;
        this.albumArrayList = albumArrayList;
    }

    @Override
    public viewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_alofalbum,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(viewHolder holder, int position) {
        Album album = albumArrayList.get(position);
        Picasso.with(context).load(album.getHinh()).into(holder.imageViewHinhAlbum);
        holder.textViewAlofAlbum.setText(album.getTen());
    }

    @Override
    public int getItemCount() {
        return albumArrayList.size();
    }

    class viewHolder extends RecyclerView.ViewHolder {
        ImageView imageViewHinhAlbum;
        TextView textViewAlofAlbum;
        public viewHolder(View itemView) {
            super(itemView);
            imageViewHinhAlbum = itemView.findViewById(R.id.imgviewDanhSachAlofAlbum);
            textViewAlofAlbum = itemView.findViewById(R.id.txtViewTenAlofAlbum);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context,DanhSachBaiHatActivity.class);
                    intent.putExtra("album",albumArrayList.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
