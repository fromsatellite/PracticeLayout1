package com.example.algorithm;

/**
 * Created by leador_yang on 2018/5/9.
 */

public class BinaryCompute {

    public int countBitDiff(int m, int n){
        return Integer.bitCount(m ^ n);
    }
}
