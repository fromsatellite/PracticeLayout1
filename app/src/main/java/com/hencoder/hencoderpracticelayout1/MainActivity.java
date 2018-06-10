package com.hencoder.hencoderpracticelayout1;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.StringRes;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;

import com.fragment.interfaces.FunctionManager;
import com.fragment.interfaces.FunctionWithNoParamNoResult;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager pager;
    List<PageModel> pageModels = new ArrayList<>();

    {
//        pageModels.add(new PageModel(R.layout.sample_square_image_view, R.string.title_square_image_view, R.layout.practice_square_image_view));
        pageModels.add(new PageModel(R.layout.practice_slideview, R.string.title_square_image_view, R.layout.practice_ruler));
        pageModels.add(new PageModel(R.layout.practice_slideview, R.string.title_square_image_view, R.layout.practice_slideview));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {

            @Override
            public Fragment getItem(int position) {
                PageModel pageModel = pageModels.get(position);
                return PageFragment.newInstance(pageModel.sampleLayoutRes, pageModel.practiceLayoutRes, position);
            }

            @Override
            public int getCount() {
                return pageModels.size();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return getString(pageModels.get(position).titleRes);
            }
        });

        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(pager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    public PageFragment getCurPage(int pos){
        PageFragment pageFragment = null;
        pageFragment = (PageFragment) ((FragmentPagerAdapter)pager.getAdapter()).getItem(pos);

        return pageFragment;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK){
            Log.d("@@@", "RESULT OK, DATA NULL");
        } else if (resultCode == RESULT_CANCELED){
            Log.d("@@@", "RESULT CANCELED, DATA NULL");
        } else if (resultCode == 2){
            if (requestCode == PageFragment.REQUESTCODE_NEEDBACK){
                final int pos = data.getBundleExtra("BundlePage").getInt("position");
                PageFragment curPage = getCurPage(pos);
                Log.d("@@@", "isVisible = "+curPage.isVisible() + ", isResumed = " + curPage.isResumed());
//                if (curPage.isResumed()){
//                }
                curPage.refresh();
            }
        }
    }

    /**
     * 由此方法组装接口
     * @param tag
     */
    public void setFunctionsForFragment(String tag){
        if ("page0".equals(tag)){
            FunctionManager.getInstance().addFunction(new FunctionWithNoParamNoResult(PageFragment.INTERFACE) {
                @Override
                public void function() {
                    Log.d("@@@", "I love barry!");
                }
            });
        }
    }

    private class PageModel {
        @LayoutRes int sampleLayoutRes;
        @StringRes int titleRes;
        @LayoutRes int practiceLayoutRes;

        PageModel(@LayoutRes int sampleLayoutRes, @StringRes int titleRes, @LayoutRes int practiceLayoutRes) {
            this.sampleLayoutRes = sampleLayoutRes;
            this.titleRes = titleRes;
            this.practiceLayoutRes = practiceLayoutRes;
        }
    }
}
