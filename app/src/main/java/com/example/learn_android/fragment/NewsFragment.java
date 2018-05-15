package com.example.learn_android.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by 爱冒险的小鸡仔 on 2018/5/15.
 */

public class NewsFragment extends Fragment {
    private String weburl;
    private String channelName;

    @Override
    public void onAttach(Activity activity){
        super.onAttach(activity);
    }

    private View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
       if (view == null){
           TextView tvTitle = new TextView(getActivity());
           tvTitle.setText(channelName);
           tvTitle.setTextSize(16);
           tvTitle.setGravity(Gravity.CENTER);
           tvTitle.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
           view = tvTitle;
       }
       ViewGroup parent = (ViewGroup)view.getParent();
       if (parent != null){
           parent.removeView(view);
       }
       return view;
    }

    @Override
    public void setArguments(Bundle bundle){
        weburl = bundle.getString("weburl");
        channelName = bundle.getString("name");
    }
}
