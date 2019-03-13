package nvduy1997gmail.com.appnghenhac.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import nvduy1997gmail.com.appnghenhac.activity.DanhSachBaiHatActivity;
import nvduy1997gmail.com.appnghenhac.activity.DanhSachChuDeActivity;
import nvduy1997gmail.com.appnghenhac.activity.DanhSachTheLoaiTheoChuDeActivity;
import nvduy1997gmail.com.appnghenhac.model.ChuDe;
import nvduy1997gmail.com.appnghenhac.model.ChuDeTheLoai;
import nvduy1997gmail.com.appnghenhac.model.TheLoai;
import nvduy1997gmail.com.appnghenhac.R;
import nvduy1997gmail.com.appnghenhac.service.APIService;
import nvduy1997gmail.com.appnghenhac.service.DataService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_TheLoai_ChuDe extends Fragment {

    private View view;
    private ChuDeTheLoai chuDeTheLoai = new ChuDeTheLoai();
    private HorizontalScrollView horizontalScrollView;
    private TextView textViewXemThem;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_chude_theloai, container, false);
        anhxa();
        getData();
        return view;
    }

    private void anhxa() {
        horizontalScrollView = view.findViewById(R.id.horizontalScollview);
        textViewXemThem = view.findViewById(R.id.txtviewxemthem);
        textViewXemThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DanhSachChuDeActivity.class);
                startActivity(intent);
            }
        });
    }

    private void getData() {
        DataService dataService = APIService.getService();
        Call<ChuDeTheLoai> callback = dataService.getChuDeTheLoai();
        callback.enqueue(new Callback<ChuDeTheLoai>() {
            @Override
            public void onResponse(Call<ChuDeTheLoai> call, Response<ChuDeTheLoai> response) {
                chuDeTheLoai = response.body();
                anhxa();
                final ArrayList<ChuDe> chuDes = new ArrayList<>();
                chuDes.addAll(chuDeTheLoai.getChuDe());

                final ArrayList<TheLoai> theLoais = new ArrayList<>();
                theLoais.addAll(chuDeTheLoai.getTheLoai());
                LinearLayout linearLayout = new LinearLayout(getActivity());
                linearLayout.setOrientation(linearLayout.HORIZONTAL);

                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(580, 250);
                layoutParams.setMargins(10, 20, 10, 30);


                for (int i = 0; i < chuDes.size(); i++) {
                    CardView cardView = new CardView(getActivity());
                    cardView.setRadius(10);
                    ImageView imageView = new ImageView(getActivity());
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                    if (chuDes.get(i).getHinhchude() != null) {
                        Picasso.with(getActivity()).load(chuDes.get(i).getHinhchude()).into(imageView);
                    }
                    cardView.setLayoutParams(layoutParams);
                    cardView.addView(imageView);
                    linearLayout.addView(cardView);
                    final int y = i;
                    imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getActivity(), DanhSachTheLoaiTheoChuDeActivity.class);
                            intent.putExtra("idchude", chuDes.get(y));
                            startActivity(intent);
                        }
                    });
                }

                for (int j = 0; j < theLoais.size(); j++) {
                    CardView cardView = new CardView(getActivity());
                    cardView.setRadius(10);
                    ImageView imageView = new ImageView(getActivity());
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                    if (theLoais.get(j).getHinhtheloai() != null) {
                        Picasso.with(getActivity()).load(theLoais.get(j).getHinhtheloai()).into(imageView);
                    }
                    cardView.setLayoutParams(layoutParams);
                    cardView.addView(imageView);
                    linearLayout.addView(cardView);
                    final int x = j;
                    imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getActivity(), DanhSachBaiHatActivity.class);
                            intent.putExtra("idtheloai", theLoais.get(x));
                            startActivity(intent);
                        }
                    });
                }
                horizontalScrollView.addView(linearLayout);
            }

            @Override
            public void onFailure(Call<ChuDeTheLoai> call, Throwable t) {

            }
        });
    }


}
