package com.nasatech.favoritmovie;

import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.nasatech.favoritmovie.Adapter.MainFragmentPagerAdapter;
import com.nasatech.favoritmovie.Fragment.FavoritTVShow;
import com.nasatech.favoritmovie.Fragment.FavoriteMovie;
import com.nasatech.favoritmovie.Interface.LoadFavoriteCallback;

import java.lang.ref.WeakReference;

import static com.nasatech.favoritmovie.provider.DatabaseContract.FavoriteColumns.CONTENT_URI;

public class MainActivity extends AppCompatActivity {
    private static HandlerThread handlerThread;
    private DataObserver myObserver;
    private ViewPager viewPager;
    private TabLayout.Tab ActiveTab;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        InitViews();

        handlerThread = new HandlerThread("DataObserver");
        handlerThread.start();
        Handler handler = new Handler(handlerThread.getLooper());
        myObserver = new DataObserver(handler, this);
        getContentResolver().registerContentObserver(CONTENT_URI, true, myObserver);

    }

    private void InitViews() {
        viewPager = findViewById(R.id.viewPager1);
        SetUpViewPager(viewPager);

        tabLayout = findViewById(R.id.tbCatalog);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                ActiveTab = tab;
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        if (ActiveTab == null)
            ActiveTab = tabLayout.getTabAt(0);
    }

    private void SetUpViewPager(ViewPager vp) {
        MainFragmentPagerAdapter mainFragmentPagerAdapter = new MainFragmentPagerAdapter(getSupportFragmentManager());
        mainFragmentPagerAdapter.addFragment(new FavoriteMovie(), getString(R.string.movie));
        mainFragmentPagerAdapter.addFragment(new FavoritTVShow(), getString(R.string.tv_show));
        vp.setAdapter(mainFragmentPagerAdapter);

    }

    private static class LoadNoteAsync extends AsyncTask<Void, Void, Cursor> {

        private final WeakReference<Context> weakContext;
        private final WeakReference<LoadFavoriteCallback> weakCallback;

        private LoadNoteAsync(Context context, LoadFavoriteCallback callback) {
            weakContext = new WeakReference<>(context);
            weakCallback = new WeakReference<>(callback);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            weakCallback.get().preExecute();
        }

        @Override
        protected Cursor doInBackground(Void... voids) {
            Context context = weakContext.get();
            return context.getContentResolver().query(CONTENT_URI, null, null, null, null);
        }

        @Override
        protected void onPostExecute(Cursor notes) {
            super.onPostExecute(notes);
            weakCallback.get().postExecute(notes);
        }
    }

    public static class DataObserver extends ContentObserver {
        final Context context;

        public DataObserver(Handler handler, Context context) {
            super(handler);
            this.context = context;
        }

        @Override
        public void onChange(boolean selfChange) {
            super.onChange(selfChange);
            new LoadNoteAsync(context, (LoadFavoriteCallback) context).execute();
        }
    }
}
