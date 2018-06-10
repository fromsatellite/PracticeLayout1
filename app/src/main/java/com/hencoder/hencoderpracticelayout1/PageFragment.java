package com.hencoder.hencoderpracticelayout1;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.Button;

import com.fragment.interfaces.FunctionManager;
import com.fragment.interfaces.FunctionNotFoundException;
import com.satellite.activity.ReflectResActivity;

public class PageFragment extends BaseFragment {
    @LayoutRes int sampleLayoutRes;
    @LayoutRes int practiceLayoutRes;
    int position;
    private Button btn_click;
    private Button btn_recycle;

    public static int REQUESTCODE_NEEDBACK = 0x11;
    public static final String INTERFACE = PageFragment.class.getName() + "NPNR";

    public static PageFragment newInstance(@LayoutRes int sampleLayoutRes, @LayoutRes int practiceLayoutRes,
                                           int position) {
        PageFragment fragment = new PageFragment();
        Bundle args = new Bundle();
        args.putInt("sampleLayoutRes", sampleLayoutRes);
        args.putInt("practiceLayoutRes", practiceLayoutRes);
        args.putInt("position", position);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Log.d("@@@", "onCreate()");
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {
            sampleLayoutRes = args.getInt("sampleLayoutRes");
            practiceLayoutRes = args.getInt("practiceLayoutRes");
            position = args.getInt("position", 0);
            // 让当前fragment在加载的时候，就将通信接口注入
            baseActicity.setFunctionsForFragment("page"+position);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d("@@@", "onCreateView()");
        View view = inflater.inflate(R.layout.fragment_page, container, false);

        ViewStub sampleStub = (ViewStub) view.findViewById(R.id.sampleStub);
        sampleStub.setLayoutResource(sampleLayoutRes);
        sampleStub.inflate();

        ViewStub practiceStub = (ViewStub) view.findViewById(R.id.practiceStub);
        practiceStub.setLayoutResource(practiceLayoutRes);
        practiceStub.inflate();

        btn_click = (Button) view.findViewById(R.id.btn_click);
        btn_recycle = (Button) view.findViewById(R.id.btn_recycle);
        btn_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(getActivity(), RulerActivity.class);
//                Bundle bundle = new Bundle();
//                bundle.putInt("position", position);
//                intent.putExtra("BundlePage", bundle);
//                getActivity().startActivityForResult(intent, REQUESTCODE_NEEDBACK);

                // 调用组装接口
                try {
                    if (position == 0){
                        FunctionManager.getInstance().invokeFunction(INTERFACE);
                    }
                } catch (FunctionNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
        btn_recycle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(getActivity(), HomeActicity.class);
                Intent intent = new Intent(getActivity(), ReflectResActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }

    public void refresh(){
        Log.d("@@@", "refresh()-----------------------------");
    }

    @Override
    public void onResume() {
        Log.d("@@@", "onResume()");
        super.onResume();
    }

    @Override
    public void onPause() {
        Log.d("@@@", "onPause()");
        super.onPause();
    }

    @Override
    public void onDestroy() {
        Log.d("@@@", "onDestroy()");
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        Log.d("@@@", "onDetach()");
        super.onDetach();
    }
}
