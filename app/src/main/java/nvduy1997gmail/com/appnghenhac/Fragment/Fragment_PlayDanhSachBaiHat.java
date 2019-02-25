package nvduy1997gmail.com.appnghenhac.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import nvduy1997gmail.com.appnghenhac.Activity.PlayNhacActivity;
import nvduy1997gmail.com.appnghenhac.Adapter.PlayNhacAdapter;
import nvduy1997gmail.com.appnghenhac.R;

public class Fragment_PlayDanhSachBaiHat extends Fragment {
//ông vừa click vào widget nao nhi. va no nam o
    View view;
    RecyclerView recyclerViewPlayNhac;
    PlayNhacAdapter playNhacAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_playdanhsachbaihat,container,false);
        recyclerViewPlayNhac = view.findViewById(R.id.recyclerviewPlayBaiHat);
        if (PlayNhacActivity.mangBaiHat.size() >0){
            playNhacAdapter = new PlayNhacAdapter(getActivity(),PlayNhacActivity.mangBaiHat);
            recyclerViewPlayNhac.setLayoutManager(new LinearLayoutManager(getActivity()));
            recyclerViewPlayNhac.setAdapter(playNhacAdapter);
        }
        return view;
    }
}
