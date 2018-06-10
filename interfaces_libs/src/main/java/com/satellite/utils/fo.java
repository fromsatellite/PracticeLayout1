package com.satellite.utils;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewStub;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by leador_yang on 2018/6/9.
 */

public class fo extends ContextThemeWrapper {
    private Resources a = ResourcesUtil.a();
    private LayoutInflater b;
    private ClassLoader c;
    private static final String[] d = new String[]{"android.widget", "android.webkit", "android.app"};
    private fo.a e = new fo.a();
    private LayoutInflater.Factory f = new LayoutInflater.Factory() {
        public View onCreateView(String var1, Context var2, AttributeSet var3) {
            return fo.this.a(var1, var2, var3);
        }
    };

    public fo(Context var1, int var2, ClassLoader var3) {
        super(var1, var2);
        this.c = var3;
    }

    public Resources getResources() {
        return this.a != null?this.a:super.getResources();
    }

    public Object getSystemService(String var1) {
        if("layout_inflater".equals(var1)) {
            if(this.b == null) {
                LayoutInflater var2 = (LayoutInflater)super.getSystemService(var1);
                this.b = var2.cloneInContext(this);
                this.b.setFactory(this.f);
                this.b = this.b.cloneInContext(this);
            }

            return this.b;
        } else {
            return super.getSystemService(var1);
        }
    }

    private final View a(String var1, Context var2, AttributeSet var3) {
        if(this.e.a.contains(var1)) {
            return null;
        } else {
            Constructor var4 = (Constructor)this.e.b.get(var1);
            if(var4 == null) {
                Class var5 = null;
                boolean var6 = false;

                try {
                    if(var1.contains("api.navi")) {
                        var5 = this.c.loadClass(var1);
                    } else {
                        String[] var7 = d;
                        int var8 = var7.length;
                        int var9 = 0;

                        while(var9 < var8) {
                            String var10 = var7[var9];

                            try {
                                var5 = this.c.loadClass(var10 + "." + var1);
                                break;
                            } catch (Throwable var14) {
                                ++var9;
                            }
                        }
                    }

                    if(var5 != null && var5 != ViewStub.class && var5.getClassLoader() == this.c) {
                        var6 = true;
                    }
                } catch (Throwable var15) {
                    ;
                }

                if(!var6) {
                    this.e.a.add(var1);
                    return null;
                }

                try {
                    var4 = var5.getConstructor(new Class[]{Context.class, AttributeSet.class});
                    this.e.b.put(var1, var4);
                } catch (Throwable var13) {
                    ;
                }
            }

            try {
                View var16 = (View)var4.newInstance(new Object[]{var2, var3});
                return var16;
            } catch (Throwable var12) {
                return null;
            }
        }
    }

    public class a {
        public HashSet<String> a = new HashSet();
        public HashMap<String, Constructor<?>> b = new HashMap();

        public a() {
        }
    }
}