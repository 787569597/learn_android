package com.example.learn_android;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.widget.HorizontalScrollView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.learn_android.adapter.PageFragmentAdapter;
import com.example.learn_android.db.ChannelDb;
import com.example.learn_android.entity.ChannelD;
import com.example.learn_android.fragment.NewsFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 爱冒险的小鸡仔 on 2018/5/15.
 */

public class MainActivity extends FragmentActivity implements ViewPager.OnPageChangeListener {
    private ViewPager viewPager;
    private RadioGroup rgChannel = null;
    private HorizontalScrollView hvChannel;
    private PageFragmentAdapter adapter=null;
    private List<Fragment> fragmentList = new ArrayList<android.support.v4.app.Fragment>();

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        initView();
    }

    private void initView(){
        rgChannel = (RadioGroup)findViewById(R.id.rgChannel);
        hvChannel = (HorizontalScrollView) findViewById(R.id.hvChannel);
        viewPager = (ViewPager) findViewById(R.id.vpNewsList);
        rgChannel.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                viewPager.setCurrentItem(checkedId);
            }
        });

        viewPager.setOnPageChangeListener(this);
//        动态产生RadioButton
        initTab();
        initViewPager();
        rgChannel.check(0);
    }
/*向RadioGroup动态添加RadioButton
    导航按钮数据来源于ChannelDb
 */
    private void initTab(){
        List<ChannelD> channelList = ChannelDb.getSelectedChannel();
        for(int i=0; i<channelList.size();i++){
            RadioButton rb = (RadioButton) LayoutInflater.from(this).inflate(R.layout.tab_rb,null);
            rb.setId(i);
            rb.setText(channelList.get(i).getName());
            RadioGroup.LayoutParams params = new RadioGroup.LayoutParams(RadioGroup.LayoutParams.WRAP_CONTENT, RadioGroup.LayoutParams.WRAP_CONTENT);
            rgChannel.addView(rb, params);
        }
    }

    private void initViewPager(){
        List<ChannelD> channelList = ChannelDb.getSelectedChannel();
        for (int i = 0; i<channelList.size(); i++){
            NewsFragment frag = new NewsFragment();
            Bundle bundle = new Bundle();
            bundle.putString("weburl", channelList.get(i).getWeburl());
            bundle.putString("name",channelList.get(i).getName());
//            向Fragment传入数据
            frag.setArguments(bundle);
            fragmentList.add(frag);
        }
        adapter = new PageFragmentAdapter(super.getSupportFragmentManager(),fragmentList);
        viewPager.setAdapter(adapter);
//        viewPager.setOffscreenPageLimit(0);
    }

    /**
     * 滑动ViewPager时调整ScroollView的位置以便显示按钮
     */
    private void setTab(int idx){
        RadioButton rb = (RadioButton)rgChannel.getChildAt(idx);
        rb.setChecked(true);
        int left = rb.getLeft();
        int width = rb.getMeasuredWidth();
        DisplayMetrics metrics = new DisplayMetrics();
        super.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int screenWidth = metrics.widthPixels;
        int len = left+width/2-screenWidth/2;
//        滑动ScroollView
        hvChannel.smoothScrollTo(len, 0);
    }

    @Override
    public void onPageScrollStateChanged(int arg0){

    }

    @Override
    public void onPageScrolled(int arg0, float arg1, int arg2){

    }

    @Override
    public void onPageSelected(int position){
        setTab(position);
    }
}
