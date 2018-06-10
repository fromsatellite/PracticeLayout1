package com.example.recycleview;

import java.util.List;

/**
 * Created by leador_yang on 2018/4/20.
 */

public class DataTree<K, V> {
    private K groupItem;
    private List<V> subItems;

    public DataTree(K groupItem, List<V> subItems) {
        this.groupItem = groupItem;
        this.subItems = subItems;
    }

    public K getGroupItem() {
        return groupItem;
    }

    public List<V> getSubItems() {
        return subItems;
    }
}
