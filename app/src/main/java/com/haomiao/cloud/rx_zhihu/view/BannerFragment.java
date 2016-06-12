package com.haomiao.cloud.rx_zhihu.view;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.haomiao.cloud.rx_zhihu.model.ZhiHuBanner;
import com.haomiao.cloud.rx_zhihu.R;
import com.haomiao.cloud.rx_zhihu.utils.PicassoUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class BannerFragment extends Fragment {


    @Bind(R.id.tv_daf)
    TextView tvDaf;
    @Bind(R.id.imageView3)
    ImageView imageView3;

    public BannerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_blank, null);
        ButterKnife.bind(this, inflate);
        ZhiHuBanner item = (ZhiHuBanner) getArguments().getSerializable("1");
        tvDaf.setText(item.getTitle());
        PicassoUtils.displayCacheImage(item.getImages(), imageView3);
        return inflate;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
