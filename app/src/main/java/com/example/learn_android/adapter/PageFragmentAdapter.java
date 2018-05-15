package com.example.learn_android.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;

import java.util.List;

/**
 * Created by 爱冒险的小鸡仔 on 2018/5/15.
 */

public class PageFragmentAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragmentList;
    private FragmentManager fm;
    public PageFragmentAdapter(FragmentManager fm, List<Fragment> fragmentList){
        super(fm);
        this.fragmentList = fragmentList;
        this.fm = fm;
    }

    @Override
    public Fragment getItem(int idx){
        return fragmentList.get(idx%fragmentList.size());
    }

    @Override
    public int getCount(){
        return fragmentList.size();
    }

    @Override
    public int getItemPosition(Object object){
        return POSITION_NONE;
    }

    public void setFragments(List<Fragment> fragments){
        if (this.fragmentList != null){
            FragmentTransaction ft = fm.beginTransaction();
            for (Fragment f:this.fragmentList){
                ft.remove(f);
            }
            ft.commit();
            ft = null;
            fm.executePendingTransactions();
        }
        this.fragmentList = fragments;
        notifyDataSetChanged();
    }
}
