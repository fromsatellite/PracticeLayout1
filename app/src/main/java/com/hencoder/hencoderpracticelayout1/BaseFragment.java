package com.hencoder.hencoderpracticelayout1;

import android.content.Context;
import android.support.v4.app.Fragment;

/**
 * Created by leador_yang on 2018/5/28.
 */

public abstract class BaseFragment extends Fragment {

    protected MainActivity baseActicity;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof MainActivity){
            baseActicity = (MainActivity) context;
            // 让当前fragment在加载的时候，就将通信接口注入
//            baseActicity.setFunctionsForFragment(getTag());
        }
    }
}
