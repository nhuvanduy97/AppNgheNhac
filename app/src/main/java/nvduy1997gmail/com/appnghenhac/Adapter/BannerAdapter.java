package nvduy1997gmail.com.appnghenhac.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import nvduy1997gmail.com.appnghenhac.Activity.DanhSachBaiHatActivity;
import nvduy1997gmail.com.appnghenhac.Model.QuangCao;
import nvduy1997gmail.com.appnghenhac.R;

public class BannerAdapter extends PagerAdapter {
    Context context;
    ArrayList<QuangCao> arrayListBanner;

    public BannerAdapter(Context context, ArrayList<QuangCao> arrayListBanner) {
        this.context = context;
        this.arrayListBanner = arrayListBanner;
    }

    @Override
    public int getCount() {
        return arrayListBanner.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        final LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_bannner,null);
        ImageView imgBackGroundBanner = view.findViewById(R.id.imgbackgraoundbanner);
        ImageView imgBanner = view.findViewById(R.id.imageviewbanner);
        TextView txtTitleSongBanner = view.findViewById(R.id.txtViewTitleSong);
        TextView txtNoiDung = view.findViewById(R.id.txtNoiDung);

        Picasso.with(context).load(arrayListBanner.get(position).getHinhAnh()).into(imgBackGroundBanner);
        Picasso.with(context).load(arrayListBanner.get(position).getHinhBaiHat()).into(imgBanner);

        txtTitleSongBanner.setText(arrayListBanner.get(position).getTenBaiHat());
        txtNoiDung.setText(arrayListBanner.get(position).getNoiDung());

        container.addView(view);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,DanhSachBaiHatActivity.class);
                intent.putExtra("banner",arrayListBanner.get(position));
                context.startActivity(intent);
            }
        });
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
