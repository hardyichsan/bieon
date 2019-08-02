package com.todev.bieon.BottomBars;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.todev.bieon.API.ApiInterface;
import com.todev.bieon.Adapter.InformasiAdapter;
import com.todev.bieon.Model.DataInformasi;
import com.todev.bieon.R;
import com.todev.bieon.Response.InformasiResponse;
import com.todev.bieon._sliders.FragmentSlider;
import com.todev.bieon._sliders.SliderIndicator;
import com.todev.bieon._sliders.SliderPagerAdapter;
import com.todev.bieon._sliders.SliderView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.todev.bieon.API.ApiClient.BASE_URL;

public class HomeFragment extends Fragment {

    private SliderPagerAdapter mAdapter;
    private SliderIndicator mIndicator;

    private SliderView sliderView;
    private LinearLayout mLinearLayout;

    Context context;

    View rootView;
    private ApiInterface getInterfaceService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        final ApiInterface mInterfaceService = retrofit.create(ApiInterface.class);
        return mInterfaceService;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView =  inflater.inflate(R.layout.fragment_home, container, false);

        final SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout)rootView.findViewById(R.id.swipe_refresh);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                ambildata(rootView);
            }
        });
        // Inflate the layout for this fragment
        sliderView = (SliderView) rootView.findViewById(R.id.sliderView);
        mLinearLayout = (LinearLayout) rootView.findViewById(R.id.pagesContainer);
        // jika internet aktfif

        setupSliderOfline();
        return rootView;
    }

    private void setupSliderOfline() {
        sliderView.setDurationScroll(800);
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(FragmentSlider.newInstance("slider2"));
        fragments.add(FragmentSlider.newInstance("slider6"));
        fragments.add(FragmentSlider.newInstance("slider7"));
        fragments.add(FragmentSlider.newInstance("slider8"));
        fragments.add(FragmentSlider.newInstance("slider9"));

        mAdapter = new SliderPagerAdapter(getFragmentManager(), fragments);
        sliderView.setAdapter(mAdapter);
        mIndicator = new SliderIndicator(getActivity(), mLinearLayout, sliderView, R.drawable.indicator_circle);
        mIndicator.setPageCount(fragments.size());
        mIndicator.show();
    }

    private void ambildata(final View view) {

        final SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout)view.findViewById(R.id.swipe_refresh);
        swipeRefreshLayout.setRefreshing(true);
        ApiInterface mApiService = this.getInterfaceService();
        Call<InformasiResponse> mService = mApiService.getinformasi("202cb962ac59075b964b07152d234b70");
        mService.enqueue(new Callback<InformasiResponse>() {
            @Override
            public void onResponse(Call<InformasiResponse> call, Response<InformasiResponse> response) {

                ListView recyclerView = (ListView) view.findViewById(R.id.recycleview);
                // Pasikan response Sukses
                if (response.isSuccessful()){
                    Log.d("response api", response.body().toString());
                    // tampung data response body ke variable
                    ArrayList<DataInformasi> dataInformasi = response.body().getData();
                    boolean status = response.body().isStatus();
                    // Kalau response status nya = true
                    if (status){
                        // Buat Adapter untuk recycler view
                        InformasiAdapter recycleAdapter = new InformasiAdapter(getActivity(), dataInformasi);
                        recyclerView.setAdapter(recycleAdapter);
                    } else {
                        // kalau tidak true
                        Toast.makeText(getActivity(),"Tidak ada berita untuk saat ini", Toast.LENGTH_SHORT).show();
                    }
                }
                swipeRefreshLayout.setRefreshing(false);
            }


            @Override
            public void onFailure(Call<InformasiResponse> call, Throwable t) {
                call.cancel();
                Toast.makeText(getActivity(), "Please check your network connection and internet permission", Toast.LENGTH_LONG).show();
            }


        });
    }

    @Override
    public void onViewCreated(View rootView, @Nullable Bundle savedInstanceState) {

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ambildata(rootView);
    }

}
