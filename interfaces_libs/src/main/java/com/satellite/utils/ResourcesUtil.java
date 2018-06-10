package com.satellite.utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.Resources.Theme;
import android.content.res.XmlResourceParser;
import android.os.Environment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by leador_yang on 2018/6/9.
 */
//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//
//package com.amap.api.mapcore.util;
//        import android.app.Activity;
//        import android.content.Context;
//        import android.content.res.AssetManager;
//        import android.content.res.Configuration;
//        import android.content.res.Resources;
//        import android.content.res.XmlResourceParser;
//        import android.os.Environment;
//        import android.util.DisplayMetrics;
//        import android.view.LayoutInflater;
//        import android.view.View;
//        import android.view.ViewGroup;
//        import com.amap.api.mapcore.util.fo;
//        import com.amap.api.mapcore.util.gr;
//        import java.io.File;
//        import java.io.FileOutputStream;
//        import java.io.FilenameFilter;
//        import java.io.IOException;
//        import java.io.InputStream;
//        import java.io.OutputStream;
//        import java.lang.reflect.Constructor;
//        import java.lang.reflect.Field;
//        import java.lang.reflect.Method;

public class ResourcesUtil {
    //}
//public class fp {
    private static AssetManager b = null;
    private static Resources c = null;
    private static Resources d = null;
    private static boolean e = true;
    private static Context f;
    private static String g = "amap_resource";
    private static String h = "1_0_0";
    private static String i = ".png";
    private static String j = ".jar";
    private static String k;
    private static String l;
    private static String m;
    private static String n;
    private static Theme o;
    private static Theme p;
    private static Field q;
    private static Field r;
    private static Activity s;
    public static int a;

    public ResourcesUtil() {
    }

    public static boolean init(Context var0) {
        try {
            f = var0;
            m = b(f).getAbsolutePath() + "/";
            n = m + k;
            if(!e) {
                return true;
            }

            if(!c(var0)) {
                return false;
            }

            b = a(n);
            c = a(var0, b);
        } catch (Throwable var2) {
            var2.printStackTrace();
        }

        return true;
    }

    private static File b(Context var0) {
        File var1 = null;

        try {
            File var2;
            if(var0 != null) {
                if(Environment.getExternalStorageState().equals("mounted")) {
                    var1 = Environment.getExternalStorageDirectory();
                    if(!var1.canWrite()) {
                        var1 = var0.getFilesDir();
                    } else {
                        var1 = var0.getExternalFilesDir("LBS");
                    }
                } else {
                    var1 = var0.getFilesDir();
                }

                var2 = var1;
                return var2;
            }

            var2 = null;
            return var2;
        } catch (Exception var6) {
            var6.printStackTrace();
        } finally {
            if(var1 == null) {
                return var0.getFilesDir();
            }

        }

        return null;
    }

    public static Resources a() {
        return c == null?f.getResources():c;
    }

    private static Resources a(Context var0, AssetManager var1) {
        DisplayMetrics var2 = new DisplayMetrics();
        var2.setToDefaults();
        Configuration var3 = var0.getResources().getConfiguration();
        return new Resources(var1, var2, var3);
    }

    private static AssetManager a(String var0) {
        AssetManager var1 = null;

        try {
            Class var2 = Class.forName("android.content.res.AssetManager");
            Constructor var3 = var2.getConstructor((Class[])null);
            var1 = (AssetManager)var3.newInstance((Object[])null);
            Method var4 = var2.getDeclaredMethod("addAssetPath", new Class[]{String.class});
            var4.invoke(var1, new Object[]{var0});
        } catch (Throwable var5) {
//            gr.b(var5, "ResourcesUtil", "getAssetManager(String apkPath)");
            Log.e("ResourcesUtil", "getAssetManager(String apkPath)");
            var5.printStackTrace();
        }

        return var1;
    }

    private static boolean c(Context var0) {
        d(var0);
        InputStream var1 = null;
        OutputStream var2 = null;

        boolean var3;
        try {
            var1 = var0.getResources().getAssets().open(l);
            if(!b(var1)) {
                e();
                var2 = a(var1);
                return true;
            }

            var3 = true;
        } catch (Throwable var15) {
            var15.printStackTrace();
//            gr.b(var15, "ResourcesUtil", "copyResourceJarToAppFilesDir(Context ctx)");
            Log.e("ResourcesUtil", "copyResourceJarToAppFilesDir(Context ctx)");
            boolean var4 = false;
            return var4;
        } finally {
            try {
                if(var1 != null) {
                    var1.close();
                }

                if(var2 != null) {
                    var2.close();
                }
            } catch (IOException var14) {
                var14.printStackTrace();
//                gr.b(var14, "ResourcesUtil", "copyResourceJarToAppFilesDir(Context ctx)");
                Log.e("ResourcesUtil", "copyResourceJarToAppFilesDir(Context ctx)");
            }

        }

        return var3;
    }

    private static OutputStream a(InputStream var0) throws IOException {
        File var1 = new File(m, k);
        FileOutputStream var2 = new FileOutputStream(var1);
        byte[] var3 = new byte[1024];

        int var4;
        while((var4 = var0.read(var3)) > 0) {
            var2.write(var3, 0, var4);
        }

        return var2;
    }

    private static boolean b(InputStream var0) throws IOException {
        File var1 = new File(n);
        long var2 = var1.length();
        int var4 = var0.available();
        if(var1.exists() && var2 == (long)var4) {
            var0.close();
            return true;
        } else {
            return false;
        }
    }

    private static void e() {
        File var0 = new File(m);
        File[] var1 = var0.listFiles(new ResourcesUtil.a());
        if(var1 != null && var1.length > 0) {
            File[] var2 = var1;
            int var3 = var1.length;

            for(int var4 = 0; var4 < var3; ++var4) {
                File var5 = var2[var4];
                if(var5.delete()) {
                    ;
                }
            }
        }

    }

    private static void d(Context var0) {
        m = var0.getFilesDir().getAbsolutePath();
        n = m + "/" + k;
    }

    public static View a(Context var0, int var1, ViewGroup var2) {
        View var3 = null;
        XmlResourceParser var4 = a().getXml(var1);
        if(!e) {
            var3 = LayoutInflater.from(var0).inflate(var4, var2);
            return var3;
        } else {
            try {
                fo var5 = new fo(var0, a == -1?0:a, ResourcesUtil.class.getClassLoader());
                var3 = LayoutInflater.from(var5).inflate(var4, var2);
            } catch (Throwable var9) {
                var9.printStackTrace();
//                gr.b(var9, "ResourcesUtil", "selfInflate(Activity activity, int resource, ViewGroup root)");
                Log.e("ResourcesUtil", "selfInflate(Activity activity, int resource, ViewGroup root)");
            } finally {
                var4.close();
            }

            return var3;
        }
    }

    static {
        k = g + h + j;
        l = g + h + i;
        m = "";
        n = m + k;
        o = null;
        p = null;
        q = null;
        r = null;
        s = null;
        a = -1;
    }

    static class a implements FilenameFilter {
        a() {
        }

        public boolean accept(File var1, String var2) {
            String var3 = ResourcesUtil.h + ResourcesUtil.j;
            return var2.startsWith(ResourcesUtil.g) && !var2.endsWith(var3);
        }
    }
}

