package nvduy1997gmail.com.appnghenhac.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import nvduy1997gmail.com.appnghenhac.Model.PlayList;
import nvduy1997gmail.com.appnghenhac.R;

public class PlayList_Adapter extends ArrayAdapter<PlayList> {
    public PlayList_Adapter(@NonNull Context context, int resource, @NonNull List<PlayList> objects) {
        super(context, resource, objects);
    }

    class ViewHolder{
        TextView txtTenPlayList;
        ImageView imgBackground,imgPlayList;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null){
            LayoutInflater  inflater= LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.dong_playlist,null);
            viewHolder = new ViewHolder();
            viewHolder.txtTenPlayList = convertView.findViewById(R.id.txtviewtenplaylist);
            viewHolder.imgPlayList = convertView.findViewById(R.id.imgviewplaylist);
            viewHolder.imgBackground = convertView.findViewById(R.id.imgviewbackgroundplaylist);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        PlayList playList = getItem(position);
        Picasso.with(getContext()).load(playList.getHinhAnh()).into(viewHolder.imgBackground);
        Picasso.with(getContext()).load(playList.getIcon()).into(viewHolder.imgPlayList);
        viewHolder.txtTenPlayList.setText(playList.getTen());
        return convertView;
    }
}
