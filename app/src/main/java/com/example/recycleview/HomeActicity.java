package com.example.recycleview;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.ToastUtil;
import com.hencoder.hencoderpracticelayout1.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by leador_yang on 2018/4/19.
 */

public class HomeActicity extends Activity {

    private RecyclerView recyclerView;
    private List<String> mDatas;
    private HomeAdapter adapter;
//    private List<DataTree<TreeAdapter.DemoDetails, TreeAdapter.DemoDetails>> dataTrees;
//    private TreeAdapter treeAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_recyclerview);

        recyclerView = (RecyclerView) findViewById(R.id.id_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        initData();
        adapter = new HomeAdapter(mDatas);
//        treeAdapter = new TreeAdapter(this);
//        treeAdapter.setDataTrees(dataTrees);
        recyclerView.setAdapter(adapter);
//        recyclerView.setAdapter(treeAdapter);
        adapter.setOnItemClickLitener(new HomeAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                ToastUtil.show(HomeActicity.this, "onItemClick pos "+position);
            }

            @Override
            public void onItemLongClick(View view, int position) {
                ToastUtil.show(HomeActicity.this, "onItemLongClick pos "+position);
            }
        });
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
    }

    protected void initData(){
        mDatas = new ArrayList<String>();
        for (int i = 'A'; i <= 'Z'; i++){
            mDatas.add("" + (char)i);
        }

//        dataTrees = new ArrayList<>();
//        for (int i = 'A'; i <= 'Z'; i++){
//            String group = "" + (char)i;
//            List<String> childList = new ArrayList<>();
//            childList.add(group + "001");
//            childList.add(group + "002");
//            childList.add(group + "003");
//            DataTree<String, String> dataTree = new DataTree<>(group, childList);
//            dataTrees.add(dataTree);
//        }

        // 二级列表
//        DataTree<TreeAdapter.DemoDetails, TreeAdapter.DemoDetails> dataTree;
//        List<TreeAdapter.DemoDetails> demoDetailses;
//        TreeAdapter.DemoDetails group;
//
//        // 标准版功能
//        group = new TreeAdapter.DemoDetails(R.string.list_item_title_group_standrad, 0, null);
//        dataTree = new DataTree<>(group, null);
//        dataTrees.add(dataTree);
//        // 地图展示
//        group = new TreeAdapter.DemoDetails(R.string.list_item_title_group_showmap,0,null);
//        demoDetailses = new ArrayList<>();
//        demoDetailses.add(new TreeAdapter.DemoDetails(R.string.list_item_title_child_standradmap,0,
//                BasicMapActivity.class));
//        demoDetailses.add(new TreeAdapter.DemoDetails(R.string.list_item_title_child_traffic,0,
//                TrafficActivity.class));
//        demoDetailses.add(new TreeAdapter.DemoDetails(R.string.list_item_title_child_grid,0,
//                GridUrlActivity.class));
//        demoDetailses.add(new TreeAdapter.DemoDetails(R.string.list_item_title_child_mutimaps,0,
//                TwoMapActivity.class));
//        demoDetailses.add(new TreeAdapter.DemoDetails(R.string.list_item_title_child_style,0,
//                MapStyleActivity.class));
//        dataTree = new DataTree<>(group, demoDetailses);
//        dataTrees.add(dataTree);
//        // 覆盖物
//        group = new TreeAdapter.DemoDetails(R.string.list_item_title_group_overlay,0,null);
//        demoDetailses = new ArrayList<>();
//        demoDetailses.add(new TreeAdapter.DemoDetails(R.string.list_item_title_child_marker,0,
//                MarkerActivity.class));
//        demoDetailses.add(new TreeAdapter.DemoDetails(R.string.list_item_title_child_polyline,0,
//                PolylineActivity.class));
//        demoDetailses.add(new TreeAdapter.DemoDetails(R.string.list_item_title_child_polygon,0,
//                PolygonActivity.class));
//        demoDetailses.add(new TreeAdapter.DemoDetails(R.string.list_item_title_child_circle,0,
//                CircleActivity.class));
//        demoDetailses.add(new TreeAdapter.DemoDetails(R.string.list_item_title_child_arc,0,
//                ArcActivity.class));
//        demoDetailses.add(new TreeAdapter.DemoDetails(R.string.list_item_title_child_groundoverlay,0,
//                GroundOverlayActivity.class));
//        demoDetailses.add(new TreeAdapter.DemoDetails(R.string.list_item_title_child_navigatearrow,0,
//                NavigateArrowOverlayActivity.class));
//        demoDetailses.add(new TreeAdapter.DemoDetails(R.string.list_item_title_child_text,0,
//                OpenglActivity.class));
//        demoDetailses.add(new TreeAdapter.DemoDetails(R.string.list_item_title_child_geojson,0,
//                GeojsonSearchActivity.class));
//        demoDetailses.add(new TreeAdapter.DemoDetails(R.string.list_item_title_child_heatmap,0,
//                HeatMapActivity.class));
//        demoDetailses.add(new TreeAdapter.DemoDetails(R.string.list_item_title_child_cluster,0,
//                ClusterActivity.class));
//        demoDetailses.add(new TreeAdapter.DemoDetails(R.string.list_item_title_child_location,0,
//                LocationModeSourceActivity.class));
//        dataTree = new DataTree<>(group, demoDetailses);
//        dataTrees.add(dataTree);
//        // 地图交互
//        group = new TreeAdapter.DemoDetails(R.string.list_item_title_group_mapevent,0,null);
//        demoDetailses = new ArrayList<>();
//        demoDetailses.add(new TreeAdapter.DemoDetails(R.string.list_item_title_child_uisettings,0,
//                UiSettingsActivity.class));
//        demoDetailses.add(new TreeAdapter.DemoDetails(R.string.list_item_title_child_gesture,0,
//                UiSettingsActivity.class));
//        demoDetailses.add(new TreeAdapter.DemoDetails(R.string.list_item_title_child_poiclick,0,
//                PoiClickActivity.class));
//        demoDetailses.add(new TreeAdapter.DemoDetails(R.string.list_item_title_child_events,0,
//                EventsActivity.class));
//        demoDetailses.add(new TreeAdapter.DemoDetails(R.string.list_item_title_child_animation,0,
//                CameraActivity.class));
//        dataTree = new DataTree<>(group, demoDetailses);
//        dataTrees.add(dataTree);
//        // 搜索服务
//        group = new TreeAdapter.DemoDetails(R.string.list_item_title_group_search,0,null);
//        demoDetailses = new ArrayList<>();
//        demoDetailses.add(new TreeAdapter.DemoDetails(R.string.list_item_title_child_poikeysearch,0,
//                PoiKeywordSearchActivity.class));
//        demoDetailses.add(new TreeAdapter.DemoDetails(R.string.list_item_title_child_poiaroundsearch,0,
//                PoiAroundSearchActivity.class));
//        demoDetailses.add(new TreeAdapter.DemoDetails(R.string.list_item_title_child_polygonsearch,0,
//                PoiKeywordSearchActivity.class));
//        demoDetailses.add(new TreeAdapter.DemoDetails(R.string.list_item_title_child_inputtips,0,
//                PoiKeywordSearchActivity.class));
//        demoDetailses.add(new TreeAdapter.DemoDetails(R.string.list_item_title_child_geocoder,0,
//                GeocoderActivity.class));
//        demoDetailses.add(new TreeAdapter.DemoDetails(R.string.list_item_title_child_districtsearch,0,
//                DistrictWithBoundaryActivity.class));
//        demoDetailses.add(new TreeAdapter.DemoDetails(R.string.list_item_title_child_bussearch,0,
//                BuslineActivity.class));
//        demoDetailses.add(new TreeAdapter.DemoDetails(R.string.list_item_title_child_routeline,0,
//                RouteActivity.class));
//        demoDetailses.add(new TreeAdapter.DemoDetails(R.string.list_item_title_child_cloudstorage,0,
//                CloudSaveActivity.class));
//        demoDetailses.add(new TreeAdapter.DemoDetails(R.string.list_item_title_child_cloudsearch,0,
//                CloudSearchListResultActivity.class));
//        dataTree = new DataTree<>(group, demoDetailses);
//        dataTrees.add(dataTree);
//        // 离线地图
//        group = new TreeAdapter.DemoDetails(R.string.list_item_title_group_offinemap,0,
//                OfflineMapActivity.class);
//        dataTree = new DataTree<>(group, null);
//        dataTrees.add(dataTree);
//        // 辅助功能
//        group = new TreeAdapter.DemoDetails(R.string.list_item_title_group_utils,0, null);
//        demoDetailses = new ArrayList<>();
//        demoDetailses.add(new TreeAdapter.DemoDetails(R.string.list_item_title_child_screenshot,0,
//                ScreenShotActivity.class));
//        demoDetailses.add(new TreeAdapter.DemoDetails(R.string.list_item_title_child_coordinateconvert,0,
//                ScreenShotActivity.class));
//        dataTree = new DataTree<>(group, demoDetailses);
//        dataTrees.add(dataTree);
//        // 专业版功能
//        group = new TreeAdapter.DemoDetails(R.string.list_item_title_group_professional,0, null);
//        dataTree = new DataTree<>(group, null);
//        dataTrees.add(dataTree);
//        // 本地poi关键字搜索
//        group = new TreeAdapter.DemoDetails(R.string.list_item_title_child_localsearch,0,
//                LocalPoiSearchActivity.class);
//        dataTree = new DataTree<>(group, null);
//        dataTrees.add(dataTree);

    }


}
