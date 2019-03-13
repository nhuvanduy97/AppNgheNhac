package nvduy1997gmail.com.appnghenhac.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import nvduy1997gmail.com.appnghenhac.model.BaiHat;
import nvduy1997gmail.com.appnghenhac.R;

public class PlayNhacAdapter extends RecyclerView.Adapter<PlayNhacAdapter.viewHolder>{
    private Context context;
    private ArrayList<BaiHat> mangbaihat;

    public PlayNhacAdapter(Context context, ArrayList<BaiHat> mangbaihat) {
        this.context = context;
        this.mangbaihat = mangbaihat;
    }

    @Override
    public viewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_playbaihat,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(viewHolder holder, int position) {
        BaiHat baiHat = mangbaihat.get(position);
        holder.textViewCasi.setText(baiHat.getCasi());
        holder.textViewIndex.setText(position + 1 + "");
        holder.textViewTenBaihat.setText(baiHat.getTenbaihat());
    }

    @Override
    public int getItemCount() {
        return mangbaihat.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {

        TextView textViewIndex, textViewTenBaihat,textViewCasi;
        public viewHolder(View itemView) {
            super(itemView);
            textViewCasi = itemView.findViewById(R.id.textviewPlayNhacTenCaSi);
            textViewIndex = itemView.findViewById(R.id.textviewPlayNhacInDex);
            textViewTenBaihat = itemView.findViewById(R.id.textviewPlayNhacTenBaiHat);
        }
    }
}
