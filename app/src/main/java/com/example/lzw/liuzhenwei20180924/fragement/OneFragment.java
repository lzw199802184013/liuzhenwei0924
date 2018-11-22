package com.example.lzw.liuzhenwei20180924.fragement;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.lzw.liuzhenwei20180924.R;
import com.example.topgridlibrary.topgrid.ChannelActivity;

import java.util.ArrayList;
import java.util.List;

public class OneFragment extends Fragment {
    private TabLayout tablayout;
    private ImageView click;
    private ViewPager view_vp1;
    private  String [] dataUrl={"头条","订阅","合肥","热评","图集","视频"};
    private List<Fragment> fragmentList = new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_one, container, false);
        tablayout=(TabLayout)view.findViewById(R.id.tablayout);
       click=(ImageView)view.findViewById(R.id.click);
       view_vp1=(ViewPager)view.findViewById(R.id.view_vp1);
       click.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               startActivity(new Intent(getActivity(), ChannelActivity.class));
           }
       });
       for (int a=0;a<dataUrl.length;a++){
           fragmentList.add(new LeftFragment());
       }
       viewAdapter = new ViewAdapter(getChildFragmentManager());
       view_vp1.setAdapter(viewAdapter);
       tablayout.setupWithViewPager(view_vp1);
       tablayout.getTabAt(3).select();
        return  view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
    private  ViewAdapter viewAdapter;
    private class ViewAdapter extends FragmentPagerAdapter{

        public ViewAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return dataUrl.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return dataUrl[position];
        }
    }
}
