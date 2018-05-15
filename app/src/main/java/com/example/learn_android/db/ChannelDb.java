package com.example.learn_android.db;

import java.util.ArrayList;
import java.util.List;

import com.example.learn_android.entity.ChannelD;

/**
 * Created by 爱冒险的小鸡仔 on 2018/5/15.
 */

public class ChannelDb {
    private static List<ChannelD> selectedChannel = new ArrayList<ChannelD>();
    static{
        selectedChannel.add(new ChannelD("", "广场",0,"",""));
        selectedChannel.add(new ChannelD("","动态",0,"",""));
    }
    public static List<ChannelD> getSelectedChannel(){
        return selectedChannel;
    }
}
