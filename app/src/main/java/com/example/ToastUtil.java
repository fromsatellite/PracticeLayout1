package com.example;

import android.content.Context;
import android.widget.Toast;

public class ToastUtil {

    public static void show(Context context, String info) {
        Toast.makeText(context, info, Toast.LENGTH_LONG).show();
    }

    public static void show(Context context, int info) {
        Toast.makeText(context, info, Toast.LENGTH_LONG).show();
    }

//    public static void showerror(Context context, int rCode){
//        try {
//            switch (rCode) {
//                case 21:
//                    throw new LeadorException(LeadorException.ERROR_IO);
//                case 22:
//                    throw new LeadorException(LeadorException.ERROR_SOCKET);
//                case 23:
//                    throw new LeadorException(LeadorException.ERROR_SOCKE_TIME_OUT);
//                case 24:
//                    throw new LeadorException(LeadorException.ERROR_INVALID_PARAMETER);
//                case 25:
//                    throw new LeadorException(LeadorException.ERROR_NULL_PARAMETER);
//                case 26:
//                    throw new LeadorException(LeadorException.ERROR_URL);
//                case 27:
//                    throw new LeadorException(LeadorException.ERROR_UNKNOW_HOST);
//                case 28:
//                    throw new LeadorException(LeadorException.ERROR_UNKNOW_SERVICE);
//                case 29:
//                    throw new LeadorException(LeadorException.ERROR_PROTOCOL);
//                case 30:
//                    throw new LeadorException(LeadorException.ERROR_CONNECTION);
//                case 31:
//                    throw new LeadorException(LeadorException.ERROR_UNKNOWN);
//
//            }
//        } catch (Exception e) {
//            Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
//        }
//    }
}
