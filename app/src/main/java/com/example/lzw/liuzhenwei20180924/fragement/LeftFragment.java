package com.example.lzw.liuzhenwei20180924.fragement;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.example.lzw.liuzhenwei20180924.R;
import com.example.lzw.liuzhenwei20180924.adapter.MyAdapter;
import com.example.lzw.liuzhenwei20180924.bean.NewsBean;
import com.example.lzw.liuzhenwei20180924.db.DBUtils;
import com.example.lzw.liuzhenwei20180924.net.HelpAsync;
import com.example.lzw.liuzhenwei20180924.net.NetworkUtils;
import com.example.lzw.liuzhenwei20180924.net.SharedPreUtils;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

public class LeftFragment extends Fragment {


    private PullToRefreshListView pulltorefreshlistview;
    private  int page =1;
    private  String newsUrl="http://365jia.cn/news/api3/365jia/news/categories/hotnews?page="+page;
    private List<NewsBean.DataBeanX.DataBean> beans = new ArrayList<>();
    private List<NewsBean.DataBeanX.DataBean> beansAll = new ArrayList<>();
    private MyAdapter adapter;
    private DBUtils dbUtils;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_left, container, false);
        pulltorefreshlistview=(PullToRefreshListView)view.findViewById(R.id.pulltorefreshlistview);
        dbUtils = new DBUtils(getActivity());

        return  view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        adapter = new MyAdapter(getActivity());
        pulltorefreshlistview.setAdapter(adapter);
        pulltorefreshlistview.setMode(PullToRefreshBase.Mode.BOTH);
        pulltorefreshlistview.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                beansAll.clear();
                page=1;
                doGet(page);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                    page++;
                    doGet(page);
            }
        });
        doGet(page);
    }

    private void doGet(int page) {
        boolean connected = NetworkUtils.isConnected(getActivity());
        if (connected){
            new HelpAsync().get(newsUrl).result(new HelpAsync.HttpListener() {
                @Override
                public void success(String data) {
                    dbUtils.insert(data);
//               SharedPreUtils.put(getActivity(),"dd",data);
                    Gson gson = new Gson();
                    NewsBean newsBean = gson.fromJson(data, NewsBean.class);
                    beans = newsBean.getData().getData();
                    beansAll.addAll(beans);
                    adapter.setList(beansAll);
                    pulltorefreshlistview.onRefreshComplete();
                }

                @Override
                public void fail() {

                }
            });

        }else {
            String query = dbUtils.query();
//            String dd = SharedPreUtils.getString(getActivity(), "dd");
            Gson gson = new Gson();
            NewsBean newsBean = gson.fromJson(query, NewsBean.class);
            beans = newsBean.getData().getData();
            beansAll.addAll(beans);
            adapter.setList(beansAll);
            pulltorefreshlistview.onRefreshComplete();
        }


    }
    public  void toast(String msg){

        Toast.makeText(getActivity(),msg,Toast.LENGTH_SHORT).show();
    }

}
