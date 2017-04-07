package com.itheima.tourhelper.ui.fragment;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.itheima.tourhelper.R;
import com.itheima.tourhelper.adapter.HomeListViewAdapter;
import com.itheima.tourhelper.adapter.PagerSelectedAdapter;
import com.itheima.tourhelper.adapter.ShouYePagerAdapter;
import com.itheima.tourhelper.utils.CommentUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShouyeFragment extends BaseFragment {

    @BindView(R.id.lv_shouye)
    ListView rvShouye;
    ViewPager vpTour;
    LinearLayout llPointContainer;

    private int[] pagerPics = {R.drawable.p1, R.drawable.p2, R.drawable.p3, R.drawable.p4};
    private int prePostion =0;

    Handler handler =new Handler() {
        @Override
        public void handleMessage(Message msg) {
            vpTour.setCurrentItem(vpTour.getCurrentItem() +1);
            handler.sendEmptyMessageDelayed(0,3000);
        }
    };
    private View mHeaderView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_shouye, container, false);
        initView();
        ButterKnife.bind(this, view);
        return view;
    }

    private void initView() {
        mHeaderView = View.inflate(getContext(), R.layout.layout_con,null);
        vpTour = (ViewPager) mHeaderView.findViewById(R.id.vp_tour);
        llPointContainer = (LinearLayout) mHeaderView.findViewById(R.id.ll_point_container);
    }

    @Override
    public void initData() {
        //轮播图
        vpTour.setAdapter(new ShouYePagerAdapter(pagerPics));
        initLlPoint(pagerPics.length,llPointContainer);
        vpTour.addOnPageChangeListener(new PagerSelectedAdapter() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                llPointContainer.getChildAt(prePostion).setEnabled(false);
                llPointContainer.getChildAt(position % pagerPics.length).setEnabled(true);
                prePostion =position % pagerPics.length;
            }
        });
        handler.sendEmptyMessageDelayed(0,3000);

        //填充RecycleView布局
        rvShouye.setAdapter(new HomeListViewAdapter(getContext()));
        rvShouye.addHeaderView(mHeaderView);
    }


    @Override
    public void onPause() {
        super.onPause();
        handler.removeMessages(0);
    }

    private void initLlPoint(int size , LinearLayout ll) {
        LinearLayout.LayoutParams params;
        ImageView v;
        for (int i = 0; i < size; i++) {
            v = new ImageView(getContext());
            v.setBackgroundResource(R.drawable.point_selector);
            int px = CommentUtils.dip2px(getContext(),10);
            params = new LinearLayout.LayoutParams(px,px);
            if (i != 0) {
                params.leftMargin = px;
                v.setEnabled(false);
            }
            v.setLayoutParams(params);
            ll.addView(v);
        }
    }
}
