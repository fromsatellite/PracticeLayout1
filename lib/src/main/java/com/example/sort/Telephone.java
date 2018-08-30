package com.example.sort;

import java.util.ArrayList;
import java.util.List;

public class Telephone {

    String[] phoneStr = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
    StringBuffer stringBuffer = new StringBuffer();
    List<String> result = new ArrayList<>();

    public void start(String input){
        getInput(input, 0);
        for (String i : result){
            System.out.print(i + " ");
        }
        System.out.println();
    }

    public void getInput(String input, int startIndex){

        for (int i = 0;i<phoneStr[input.charAt(startIndex)-'0'].length();i++){
            stringBuffer.append(phoneStr[input.charAt(startIndex)-'0'].charAt(i));

            if (startIndex == input.length()-1){
                result.add(stringBuffer.toString());
                stringBuffer.deleteCharAt(stringBuffer.length()-1);
            } else {
                getInput(input, ++startIndex);

                // 递归逐层返回，都会执行下面的代码块
                startIndex--;
//                if (startIndex == 0){ // 一次性删除
//                    stringBuffer.delete(0, stringBuffer.length());
//                }
                // 逐个倒序删除
                stringBuffer.deleteCharAt(stringBuffer.length()-1);
            }

        }
    }
}
