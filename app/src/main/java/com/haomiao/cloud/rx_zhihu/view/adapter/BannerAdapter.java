package com.haomiao.cloud.rx_zhihu.view.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;

import com.haomiao.cloud.rx_zhihu.model.ZhiHuBanner;
import com.haomiao.cloud.rx_zhihu.view.BannerFragment;

/**
 * Project RX_Zhihu.
 * PackageName com.haomiao.cloud.rx_zhihu.view.adapter.
 * Created by Cloud on 16/6/7.
 * Instruction
 */
public class BannerAdapter extends FragmentPagerAdapter<ZhiHuBanner> {

    public BannerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        BannerFragment bannerFragment = new BannerFragment();
        Bundle args = new Bundle();
        Log.d("BannerAdapter", "position:" + position);
        Log.d("BannerAdapter", "itemList.size():" + itemList.size());
        if(itemList.size() > 0){
            args.putSerializable("1", itemList.get(position % itemList.size()));
        }
        bannerFragment.setArguments(args);
        return bannerFragment;
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }
}
