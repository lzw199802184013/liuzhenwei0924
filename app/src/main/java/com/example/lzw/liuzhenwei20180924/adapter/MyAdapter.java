package com.example.lzw.liuzhenwei20180924.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lzw.liuzhenwei20180924.R;
import com.example.lzw.liuzhenwei20180924.bean.NewsBean;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends BaseAdapter {
    private  DisplayImageOptions options;
    private List<NewsBean.DataBeanX.DataBean> beans = new ArrayList<>();
    private Context context;

    public MyAdapter( Context context) {
        this.context = context;
      options = new DisplayImageOptions.Builder().cacheOnDisk(true).cacheInMemory(true).build();
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        List<String> pics = beans.get(position).getPics();
        if (pics.size()==0){
            return 0;
        }
        return 1;
    }

    @Override
    public int getCount() {
        return beans.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        int type = getItemViewType(i);
        ViewHolder viewHolder;
        ViewHolder1 viewHolder1;
        switch (type){
            case  0:
                if (view==null){
                    viewHolder= new ViewHolder();
                    view=View.inflate(context, R.layout.layout_item1,null);
                    viewHolder.title1=view.findViewById(R.id.title1);
                    viewHolder.content1=view.findViewById(R.id.content1);
                    view.setTag(viewHolder);
                }else {
                    viewHolder= (ViewHolder) view.getTag();

                }
                NewsBean.DataBeanX.DataBean dataBean = beans.get(i);
                viewHolder.title1.setText(dataBean.getTitle());
                viewHolder.content1.setText(dataBean.getCatalog_name());
                break;

            case 1:
                    if (view==null){
                        viewHolder1 = new ViewHolder1();
                        view = View.inflate(context,R.layout.layout_item2,null);
                        viewHolder1.title2=view.findViewById(R.id.title2);
                        viewHolder1.content2=view.findViewById(R.id.content2);
                        viewHolder1.img = view.findViewById(R.id.img);
                        view.setTag(viewHolder1);
                    }else {
                        viewHolder1= (ViewHolder1) view.getTag();

                    }
                NewsBean.DataBeanX.DataBean dataBean1 = beans.get(i);
                    viewHolder1.title2.setText(dataBean1.getTitle());
                    viewHolder1.content2.setText(dataBean1.getCatalog_name());

                String s = dataBean1.getPics().get(0);
                if ("s".equals(s)){
                String imagUrl="http://img.365jia.cn/uploads/"+dataBean1.getPics().get(0);
                ImageLoader.getInstance().displayImage(imagUrl,viewHolder1.img,options);
            }else {

                String imagUrl2="http://img.365jia.cn/"+dataBean1.getPics().get(0);
                ImageLoader.getInstance().displayImage(imagUrl2,viewHolder1.img,options);
            }



                break;

        }
        return view;
    }

    public void setList(List<NewsBean.DataBeanX.DataBean> list) {
        this.beans = list;
        notifyDataSetChanged();
    }

    class  ViewHolder{
        TextView title1;
        TextView content1;
    }
    class ViewHolder1{
        TextView title2,content2;
        ImageView img;

    }
}
