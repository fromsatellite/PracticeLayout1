package com.example.recycleview;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hencoder.hencoderpracticelayout1.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by leador_yang on 2018/4/20.
 */

public class TreeAdapter extends RecyclerView.Adapter {

    private Context context;
    private List<DataTree<DemoDetails, DemoDetails>> dataTrees;
    private List<Boolean> groupItemStatus = new ArrayList<>();

    public TreeAdapter(Context context) {
        this.context = context;
    }

    //向外暴露设置显示数据的方法
    public void setDataTrees(List<DataTree<DemoDetails, DemoDetails>> dt) {
        this.dataTrees = dt;
        initGroupItemStatus(groupItemStatus);
        notifyDataSetChanged();
    }

    //设置初始值，所有 groupItem 默认为关闭状态
    private void initGroupItemStatus(List l) {
        for (int i = 0; i < dataTrees.size(); i++) {
            l.add(false);
        }
    }

    @Override
    public int getItemCount() {
        int itemCount = 0;

        if (groupItemStatus.size() == 0) {
            return 0;
        }

        for (int i = 0; i < dataTrees.size(); i++) {

            if (groupItemStatus.get(i)) {
                itemCount += dataTrees.get(i).getSubItems().size() + 1;
            } else {
                itemCount++;
            }

        }

        return itemCount;
    }

    @Override
    public int getItemViewType(int position) {
        return getItemStatusByPosition(position).getViewType();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        RecyclerView.ViewHolder viewHolder = null;

        if (viewType == ItemStatus.VIEW_TYPE_GROUPITEM) {

            v = LayoutInflater.from(parent.getContext()).inflate(R.layout
                    .item_home, parent, false);
            viewHolder = new GroupItemViewHolder(v);

        } else if (viewType == ItemStatus.VIEW_TYPE_SUBITEM) {

            v = LayoutInflater.from(parent.getContext()).inflate(R.layout
                    .item_tree_child, parent, false);
            viewHolder = new SubItemViewHolder(v);
        }

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final ItemStatus itemStatus = getItemStatusByPosition(position);

        final DataTree<DemoDetails, DemoDetails> dt = dataTrees.get(itemStatus.getGroupItemIndex());

        if ( itemStatus.getViewType() == ItemStatus.VIEW_TYPE_GROUPITEM ) {

            final DemoDetails groupItem = itemStatus.getDetails();
            final List<DemoDetails> subItems = dt.getSubItems();
            final int groupItemIndex = itemStatus.getGroupItemIndex();
            final GroupItemViewHolder groupItemVh = (GroupItemViewHolder) holder;

            groupItemVh.tv.setText(context.getResources().getString(groupItem.titleId));

            if (subItems == null || subItems.isEmpty()){
                groupItemVh.image_dir.setVisibility(View.GONE);
            } else {
                groupItemVh.image_dir.setVisibility(View.VISIBLE);
                if (groupItemStatus.get(groupItemIndex)){ // open
                    groupItemVh.image_dir.setImageResource(R.drawable.downarrow);
                } else { // close
                    groupItemVh.image_dir.setImageResource(R.drawable.rightarrow);
                }
            }

            // 加载groupItem，处理groupItem控件
            groupItemVh.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (subItems == null || subItems.isEmpty()){
                        if (groupItem.activityClass != null){
                            context.startActivity(new Intent(context.getApplicationContext(),
                                    groupItem.activityClass));
                        }
                    } else {
                        if ( !groupItemStatus.get(groupItemIndex) ) {
                            // groupItem由“关闭”状态到“打开”状态
                            groupItemStatus.set(groupItemIndex, true);
                            groupItemVh.image_dir.setImageResource(R.drawable.downarrow);
                            notifyItemRangeInserted(groupItemVh.getAdapterPosition() + 1, subItems.size());

                        } else {
                            // groupItem由“打开”状态到“关闭”状态
                            groupItemStatus.set(groupItemIndex, false);
                            groupItemVh.image_dir.setImageResource(R.drawable.rightarrow);
                            notifyItemRangeRemoved(groupItemVh.getAdapterPosition() + 1, subItems.size());

                        }
                    }
                }
            });

        } else if (itemStatus.getViewType() == ItemStatus.VIEW_TYPE_SUBITEM) {

            SubItemViewHolder subItemVh = (SubItemViewHolder) holder;
            final DemoDetails subItem = itemStatus.getDetails();
            subItemVh.tv_child.setText("         " + context.getResources().getString(subItem.titleId));

            // 加载subItem，处理subItem控件
            subItemVh.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                 // 点击subItem处理
                    if (subItem.activityClass != null){
                        context.startActivity(new Intent(context.getApplicationContext(),
                                subItem.activityClass));
                    }
                }
            });
        }
    }

    private ItemStatus getItemStatusByPosition(int position) {

        ItemStatus itemStatus = new ItemStatus();

        int count = 0;    //计算groupItemIndex = i 时，position最大值
        int i = 0;

        //轮询 groupItem 的开关状态
        for (i = 0; i < groupItemStatus.size(); i++ ) {

            //pos刚好等于计数时，item为groupItem
            if (count == position) {
                itemStatus.setViewType(ItemStatus.VIEW_TYPE_GROUPITEM);
                itemStatus.setGroupItemIndex(i);
                itemStatus.setDetails(dataTrees.get(i).getGroupItem());
                break;

                //pos小于计数时，item为groupItem(i - 1)中的某个subItem
            } else if (count > position) {

                itemStatus.setViewType(ItemStatus.VIEW_TYPE_SUBITEM);
                itemStatus.setGroupItemIndex(i - 1);
                int subItemIndex = position - ( count - dataTrees.get(i - 1).getSubItems().size());
                itemStatus.setSubItemIndex(subItemIndex);
                itemStatus.setDetails(dataTrees.get(i - 1).getSubItems().get(subItemIndex));
                break;

            }

            //无论groupItem状态是开或者关，它在列表中都会存在，所有count++
            count++;

            //当轮询到的groupItem的状态为“开”的话，count需要加上该groupItem下面的子项目数目
            List<DemoDetails> subItems = dataTrees.get(i).getSubItems();
            if (subItems != null && !subItems.isEmpty() && groupItemStatus.get(i)) {
                count += subItems.size();
            }
        }

        //简单地处理当轮询到最后一项groupItem的时候
        if (i >= groupItemStatus.size()) {
            itemStatus.setGroupItemIndex(i - 1);
            itemStatus.setViewType(ItemStatus.VIEW_TYPE_SUBITEM);
            int subItemIndex = position - ( count - dataTrees.get(i - 1).getSubItems().size());
            itemStatus.setSubItemIndex(subItemIndex);
            itemStatus.setDetails(dataTrees.get(i - 1).getSubItems().get(subItemIndex));
        }

        return itemStatus;
    }

    public static class GroupItemViewHolder extends RecyclerView.ViewHolder {
        TextView tv;
        ImageView image_dir;
        public GroupItemViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.id_num);
            image_dir = (ImageView) itemView.findViewById(R.id.image_dir);
        }
    }

    public static class SubItemViewHolder extends RecyclerView.ViewHolder {
        TextView tv_child;
        public SubItemViewHolder(View itemView) {
            super(itemView);
            tv_child = (TextView) itemView.findViewById(R.id.tv_child);
        }
    }

    private static class ItemStatus {

        public static final int VIEW_TYPE_GROUPITEM = 0;
        public static final int VIEW_TYPE_SUBITEM = 1;

        private int viewType;
        private int groupItemIndex = 0;
        private int subItemIndex = -1;
//        private String groupContent;
//        private String subContent;
        private DemoDetails details;

        public ItemStatus() {
        }

        public int getViewType() {
            return viewType;
        }

        public void setViewType(int viewType) {
            this.viewType = viewType;
        }

        public int getGroupItemIndex() {
            return groupItemIndex;
        }

        public void setGroupItemIndex(int groupItemIndex) {
            this.groupItemIndex = groupItemIndex;
        }

        public int getSubItemIndex() {
            return subItemIndex;
        }

        public void setSubItemIndex(int subItemIndex) {
            this.subItemIndex = subItemIndex;
        }

//        public String getGroupContent() {
//            return groupContent;
//        }

//        public void setGroupContent(String groupContent) {
//            this.groupContent = groupContent;
//        }

//        public String getSubContent() {
//            return subContent;
//        }

//        public void setSubContent(String subContent) {
//            this.subContent = subContent;
//        }

        public DemoDetails getDetails() {
            return details;
        }

        public void setDetails(DemoDetails details) {
            this.details = details;
        }
    }

    public static class DemoDetails {
        private final int titleId;
        private final int descriptionId;
        private final Class<? extends Activity> activityClass;

        public DemoDetails(int titleId, int descriptionId,
                           Class<? extends Activity> activityClass) {
            super();
            this.titleId = titleId;
            this.descriptionId = descriptionId;
            this.activityClass = activityClass;
        }
    }
}
