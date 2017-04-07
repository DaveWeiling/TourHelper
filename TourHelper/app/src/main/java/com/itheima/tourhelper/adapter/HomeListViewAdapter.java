package com.itheima.tourhelper.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.itheima.tourhelper.R;

import java.util.Random;

/**
 * Created by Administrator on 2017/3/21.
 */
public class HomeListViewAdapter extends BaseAdapter {

    Context mContext;

    public HomeListViewAdapter(Context context) {
        mContext = context;
    }

    String[] titles = {"巨石山", "西湖", "黄山", "天柱山", "野生动物园", "西溪湿地", "天安门广场"};
    int[] ids = {R.drawable.site1, R.drawable.site2, R.drawable.site3, R.drawable.site4,
            R.drawable.site5, R.drawable.site6, R.drawable.site7};
    private ViewHolder viewHolder;

    @Override
    public int getCount() {
        return 100;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        View view = null;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            view = View.inflate(mContext, R.layout.rv_home_item, null);
            viewHolder.imageView = (ImageView) view.findViewById(R.id.iv_site);
            viewHolder.tourSite = (TextView) view.findViewById(R.id.tour_site);
            viewHolder.tourNum = (TextView) view.findViewById(R.id.tour_num);
            viewHolder.tourLineNum = (TextView) view.findViewById(R.id.tour_line_num);
            view.setTag(viewHolder);
        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.setData();
        return view;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }


    class ViewHolder {

        TextView tourSite;
        TextView tourNum;
        TextView tourLineNum;
        ImageView imageView;

        public void setData() {
            Random r = new Random();
            int i = (int) (r.nextInt(7));
            tourSite.setText(titles[i]);
            imageView.setImageResource(ids[i]);
            tourNum.setText("已有" + r.nextInt(100000) + "人去过");
            tourLineNum.setText("已有" + (r.nextInt(300) + 200) + "人正在排队");
        }


    }
}
