package com.haomiao.cloud.rx_zhihu.view.activity;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.IntentCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.transition.Slide;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.bartoszlipinski.recyclerviewheader2.RecyclerViewHeader;
import com.haomiao.cloud.mvp_base.factory.RequiresPresenter;
import com.haomiao.cloud.rx_zhihu.R;
import com.haomiao.cloud.rx_zhihu.event.Events;
import com.haomiao.cloud.rx_zhihu.event.RxBus;
import com.haomiao.cloud.rx_zhihu.model.ZhiHuBanner;
import com.haomiao.cloud.rx_zhihu.model.ZhiHuDailyItem;
import com.haomiao.cloud.rx_zhihu.presenter.MainPresenter;
import com.haomiao.cloud.rx_zhihu.utils.AutoScrollObservable;
import com.haomiao.cloud.rx_zhihu.utils.LocalDisplay;
import com.haomiao.cloud.rx_zhihu.utils.SPUtils;
import com.haomiao.cloud.rx_zhihu.view.ActionManger;
import com.haomiao.cloud.rx_zhihu.view.adapter.BannerAdapter;
import com.haomiao.cloud.rx_zhihu.view.adapter.MainAdapter;
import com.haomiao.cloud.rx_zhihu.view.widget.AutoSwipeRefreshLayout;
import com.haomiao.cloud.rx_zhihu.view.widget.RxViewPager.RxViewPager;
import com.haomiao.cloud.rx_zhihu.view.widget.RxViewPager.ViewPagerPageChangeEvent;
import com.haomiao.cloud.rx_zhihu.view.widget.ViewPagerIndicator;
import com.jakewharton.rxbinding.support.v7.widget.RecyclerViewScrollEvent;
import com.jakewharton.rxbinding.support.v7.widget.RxRecyclerView;
import com.jakewharton.rxbinding.view.RxView;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.functions.Action1;


@RequiresPresenter(MainPresenter.class)
public class Main2Activity extends BaseActivity<MainPresenter>
        implements NavigationView.OnNavigationItemSelectedListener, AutoSwipeRefreshLayout.OnRefreshListener, MainAdapter.OnLoadMoreListener {

    @Bind(R.id.recyclerview)
    RecyclerView recyclerview;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.swipeRefreshLayout)
    AutoSwipeRefreshLayout swipeRefreshLayout;
    @Bind(R.id.fab)
    FloatingActionButton fab;
    @Bind(R.id.nav_view)
    NavigationView navView;
    @Bind(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @Bind(R.id.container)
    CoordinatorLayout container;
    @Bind(R.id.head)
    RecyclerViewHeader head;
    private MainAdapter adapter;
    private int lastVisibleItemPosition;
    private int firstVisibleItemPosition;
    private boolean isLoadMore;
    private boolean isLoading;
    private String oldTitle = "";
    private int pageNum = 0;
    public static final String ACTION_MODE_CHANGE = "actionmode_change";
    private ViewPagerIndicator vpi;
    private  ViewPager vp;
    AutoScrollObservable autoScrollObservable;
    private BannerAdapter bannerAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        setupWindowAnimations();
        if (savedInstanceState == null) {
            getPresenter().request(pageNum, MainPresenter.STATE_REFRESH);
            getPresenter().requestBanner();
        }


        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.autoRefresh();

        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        adapter = new MainAdapter(this, getSupportFragmentManager());
        recyclerview.setLayoutManager(layoutManager);
        recyclerview.setItemAnimator(new DefaultItemAnimator());
        recyclerview.setAdapter(adapter);
        adapter.setonLoadMoreListener(this);
        vp = (ViewPager) head.findViewById(R.id.viewpager);
        vpi = (ViewPagerIndicator) head.findViewById(R.id.viewPagerIndicator);
        head.attachTo(recyclerview);
        FragmentManager fm = getSupportFragmentManager();
        bannerAdapter = new BannerAdapter(fm);
//        vp.setAdapter(bannerAdapter);
        AutoScrollObservable.start(vp, this);
//        autoScrollObservable = new AutoScrollObservable(vp, this);
//        vp.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
//            @Override
//            public int getCount() {
//                return Integer.MAX_VALUE;
//            }
//
//            @Override
//            public Fragment getItem(int position) {
//                BannerFragment bannerFragment = new BannerFragment();
//                Bundle args = new Bundle();
////                args.putSerializable("1", zhiHuBanners.get(position % zhiHuBanners.size()));
//                args.putInt("1",position);
//                bannerFragment.setArguments(args);
//                return bannerFragment;
//            }
//        });

        RxView.clicks(fab).throttleWithTimeout(1, TimeUnit.SECONDS).subscribe(new Action1<Void>() {
            @Override
            public void call(Void aVoid) {

            }
        });

        RxViewPager.pageScrollStateChangeEvents(vp).subscribe(new Action1<Boolean>() {
            @Override
            public void call(Boolean isDragging) {
                RxBus.getInstance().send(Events.BANNER_DRAG_STATE, isDragging);
            }
        });

        RxRecyclerView.scrollEvents(recyclerview).subscribe(new Action1<RecyclerViewScrollEvent>() {
            @Override
            public void call(RecyclerViewScrollEvent recyclerViewScrollEvent) {
                firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();
                if (!(-(head.getY()) < LocalDisplay.dp2px(300))) {
                    RxBus.getInstance().send(Events.BANNER_VISIBILITY, false);
                    if (!oldTitle.equals(adapter.getList().get(firstVisibleItemPosition).getDate())) {
                        toolbar.setTitle(adapter.getList().get(firstVisibleItemPosition).getDate());
                        oldTitle = adapter.getList().get(firstVisibleItemPosition).getDate();
                    }
                } else {
                    RxBus.getInstance().send(Events.BANNER_VISIBILITY, true);
                    oldTitle = "首页";
                    toolbar.setTitle("首页");
                }
            }
        });

//
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).show();
//                Snackbar snackbar =
//                        Snackbar.make(container, "SnackbarTest", Snackbar.LENGTH_LONG).setAction("Action", new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v) {
//                                Snackbar snackbar =
//                                        Snackbar.make(container, "ActionClick", Snackbar.LENGTH_LONG);
//                                setSnackbarMessageTextColor(snackbar, getResources().getColor(R.color.colorPrimaryDark));
//                                snackbar.show();
//                            }
//                        });
//                setSnackbarMessageTextColor(snackbar, Color.parseColor("#FFFFFF"));
//                snackbar.show();
//            }
//        });

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }


    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void setupWindowAnimations() {
        // Re-enter transition is executed when returning to this activity
        Slide slideTransition = new Slide();
        slideTransition.setSlideEdge(Gravity.LEFT);
        slideTransition.setDuration(500);
        getWindow().setReenterTransition(slideTransition);
        getWindow().setExitTransition(slideTransition);
    }

    public static void setSnackbarMessageTextColor(Snackbar snackbar, int color) {
        View view = snackbar.getView();
        Snackbar.SnackbarLayout ve = (Snackbar.SnackbarLayout) snackbar.getView();
        ve.setBackgroundColor(color);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        switch (item.getItemId()) {
            case R.id.action_settings:
                break;
            case R.id.action_mode:
                if (item.getTitle().equals("夜间模式")) {
                    item.setTitle("日间模式");
                    SPUtils.getInstance().setTheme(SPUtils.THEME_DARK);
                } else {
                    item.setTitle("夜间模式");
                    SPUtils.getInstance().setTheme(SPUtils.THEME_LIGHT);
                }
                Intent intent = getIntent();
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | IntentCompat.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                overridePendingTransition(R.anim.enter, R.anim.exit);

//                Intent intent = new Intent();
//                intent.setAction(ACTION_MODE_CHANGE);
//                Main2Activity.this.sendBroadcast(intent);
                break;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    public void setData(final ArrayList<ZhiHuDailyItem> data) {
        adapter.setmState(MainAdapter.STATE_LOAD_MORE);
        if (isLoadMore) {
            isLoading = false;
            adapter.addItems(data);
        } else {
            adapter.setItems(data);
        }
        adapter.setOnItemClickListener(new MainAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int id) {
                ActionManger.toDailyDetail(Main2Activity.this, id);
            }
        });
        swipeRefreshLayout.setRefreshing(false);

    }

    public void onError(final int state) {
        swipeRefreshLayout.setRefreshing(false);
        Snackbar.make(container, "网络出错", Snackbar.LENGTH_INDEFINITE).setAction("adfad", new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                swipeRefreshLayout.setRefreshing(true);
                getPresenter().request(pageNum, state);
            }
        }).show();
        isLoading = false;
        adapter.setmState(MainAdapter.STATE_LOAD_MORE);
        adapter.notifyItemRemoved(adapter.getItemCount());
    }

    @Override
    public void onRefresh() {
        isLoadMore = false;
        pageNum = 0;
        getPresenter().request(pageNum, MainPresenter.STATE_REFRESH);
        getPresenter().requestBanner();
    }


    @Override
    public void onLoadMore() {
        isLoadMore = true;
        pageNum++;
        getPresenter().request(pageNum, MainPresenter.STATE_LOADMORE);
    }



    public void initBanner(final ArrayList<ZhiHuBanner> zhiHuBanners){
        vpi.setNum(zhiHuBanners.size());
        bannerAdapter.setItems(zhiHuBanners);

        if(vp.getAdapter() == null){
            vp.setAdapter(bannerAdapter);
        }
        RxViewPager.pageChangeEvents(vp).subscribe(new Action1<ViewPagerPageChangeEvent>() {
            @Override
            public void call(ViewPagerPageChangeEvent event) {

                vpi.move(event.positionOffset(), event.position() % zhiHuBanners.size());
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
            }
        });

    }
}
