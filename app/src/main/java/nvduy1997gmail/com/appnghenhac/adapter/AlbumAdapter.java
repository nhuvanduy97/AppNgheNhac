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
import nvduy1997gmail.com.appnghenhac.model.Album;
import nvduy1997gmail.com.appnghenhac.R;

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.viewHolder>{

    private Context context;
    private ArrayList<Album> albumArrayList;

    public AlbumAdapter(Context context, ArrayList<Album> albumArrayList) {
        this.context = context;
        this.albumArrayList = albumArrayList;
    }

    @Override
    public viewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_album,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(viewHolder holder, int position) {
        Album album = albumArrayList.get(position);
        holder.txtTenAlbum.setText(album.getTen());
        holder.txtTenCasi.setText(album.getCasi());
        Picasso.with(context).load(album.getHinh()).into(holder.imgHinhAlbum);

    }

    @Override
    public int getItemCount() {
        return albumArrayList.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{
        ImageView imgHinhAlbum;
        TextView txtTenAlbum, txtTenCasi;

        public viewHolder(View itemView) {
            super(itemView);
            imgHinhAlbum = itemView.findViewById(R.id.imgviewalbum);
            txtTenAlbum = itemView.findViewById(R.id.txtviewTenalbum);
            txtTenCasi = itemView.findViewById(R.id.txtviewTencasi);
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
