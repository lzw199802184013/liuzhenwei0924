package com.example.lzw.liuzhenwei20180924;

import android.app.Application;

import com.example.topgridlibrary.topgrid.app.AppApplication;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

public class App extends AppApplication {
    @Override
    public void onCreate() {
        super.onCreate();

        ImageLoaderConfiguration configuration   = ImageLoaderConfiguration.createDefault(this);
        ImageLoader.getInstance().init(configuration);
    }
}
