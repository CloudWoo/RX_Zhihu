package com.haomiao.cloud.rx_zhihu.test;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.haomiao.cloud.rx_zhihu.R;
import com.haomiao.cloud.rx_zhihu.utils.TransitionHelper;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SamplesRecyclerAdapter extends RecyclerView.Adapter<SamplesRecyclerAdapter.SamplesViewHolder> {
    private final Activity activity;

    public SamplesRecyclerAdapter(Activity activity) {
        this.activity = activity;
    }

    @Override
    public SamplesViewHolder onCreateViewHolder(ViewGroup parent, int position) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemview, parent, false);
        return  new SamplesViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final SamplesViewHolder viewHolder, final int position) {
        viewHolder.textView.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View v) {
                final Pair<View, String>[] pairs = TransitionHelper.createSafeTransitionParticipants(activity, true);
                ActivityOptionsCompat transitionActivityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, pairs);
                activity.startActivity(new Intent(activity, Main4Activity.class), transitionActivityOptions.toBundle());

            }
        });
    }

    @Override
    public int getItemCount() {
        return 20;
    }


    public class SamplesViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.textView)
        TextView textView;
        public SamplesViewHolder(View rootView) {
            super(rootView);
            ButterKnife.bind(this, rootView);

        }
    }
}
