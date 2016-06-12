package com.haomiao.cloud.rx_zhihu.view.activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.haomiao.cloud.mvp_base.factory.RequiresPresenter;
import com.haomiao.cloud.mvp_base.view.NucleusFragmentActivity;
import com.haomiao.cloud.rx_zhihu.model.ZhiHuDaily.StoriesEntity;
import com.haomiao.cloud.rx_zhihu.presenter.MainPresenter;
import com.haomiao.cloud.rx_zhihu.R;
import com.haomiao.cloud.rx_zhihu.view.adapter.DailyAdapter;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


@RequiresPresenter(MainPresenter.class)
public class MainActivity extends NucleusFragmentActivity<MainPresenter> {
    @Bind(R.id.listView)
    ListView listView;
    @Bind(R.id.progressBar)
    ProgressBar progressBar;
    @Bind(R.id.button)
    Button button;
    private DailyAdapter dailyAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
//        mMainPresenter.onCreate();
        dailyAdapter = new DailyAdapter(getApplicationContext());
        listView.setAdapter(dailyAdapter);
//        getPresenter().request();
//        mMainPresenter.getData("20160511");


        Log.d("MainActivity", "oncreate");

        if (savedInstanceState == null) {
            Log.d("MainActivity", "null");
//            getPresenter().request(0);
        } else {
//            Log.d("MainActivity", savedInstanceState.getString("presenter_id"));

        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
//        if (mMainPresenter != null) {
//            mMainPresenter.onDestroy();
//        }
    }

//    @Override
//    public void setData(ArrayList data) {
//        super.setData(data);
//        dailyAdapter.setItems(data);
//    }

    //    @Override
//    public void onRequestStart() {
//        progressBar.setVisibility(View.VISIBLE);
//    }
//
//    @Override
//    public void onRequestComplete() {
//        progressBar.setVisibility(View.GONE);
//
//    }
//
//    @Override
    public void setData(ArrayList<StoriesEntity> data) {
        dailyAdapter.setItems(data);
    }

    @OnClick(R.id.button)
    public void onClick() {
//        getPresenter().request(0);
    }
////

}
