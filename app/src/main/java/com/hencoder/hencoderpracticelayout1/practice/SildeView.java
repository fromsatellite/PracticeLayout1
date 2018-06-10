package com.hencoder.hencoderpracticelayout1.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

/**
 * Created by leador_yang on 2018/4/13.
 */

public class SildeView extends View {

    private Paint mPaint;
    private Paint mTextPaint;
    /**
     * 自定义view初始化显示的X坐标
     */
    private int screenX;
    /**
     * 自定义view初始化显示的Y坐标
     */
    private int screenY;
    /**
     * 最后滑动的X坐标
     */
    private int lastX;
    /**
     * 最后滑动的Y坐标
     */
    private int lastY;

    public SildeView(Context context) {
        super(context);
    }

    public SildeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public SildeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.RED);
//        mPaint.setStrokeWidth(20);
//        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mPaint.setStyle(Paint.Style.FILL);
        //获取屏幕的宽高初始化view显示的位置
        WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
//        screenX = wm.getDefaultDisplay().getWidth() / 4;
//        screenY = wm.getDefaultDisplay().getHeight() / 4;
        screenX = 100;
        screenY = 100;

        mTextPaint = new Paint();
        //设置画笔抗锯齿
        mTextPaint.setAntiAlias(true);
        //设置文本居中
        mTextPaint.setTextAlign(Paint.Align.CENTER);
        //设置字体大小
        mTextPaint.setTextSize(36);
        //设置画笔颜色
        mTextPaint.setColor(Color.WHITE);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int measuredWidth = 200;
        int measuredHeight = 200;
        // 第二步，使用 resolveSize() 来让子 View 的计算结果符合父 View 的限制
        measuredWidth = resolveSize(measuredWidth, widthMeasureSpec);
        measuredHeight = resolveSize(measuredHeight, heightMeasureSpec);
        // 第三步，调用 setMeasuredDimension() 来保存新的结果。
        setMeasuredDimension(measuredWidth, measuredHeight);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        Log.d("@@@", "changed = "+changed+", l = "+left+", top = "+top+", right = "+right+", bottom = "+bottom);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // 获取当前手指的坐标
        int currentX = (int) event.getX();
        int currentY = (int) event.getY();
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                lastX = currentX;
                lastY = currentY;
                break;
            case MotionEvent.ACTION_MOVE:
                //获取当前偏移的坐标
                int biasX = currentX - lastX;
                int biasY = currentY - lastY;
                //重写layout方法改变控件所在屏幕的位置
                layout(getLeft()+biasX, getTop()+biasY, getRight()+biasX, getBottom()+biasY);
                break;
            case MotionEvent.ACTION_UP:
                Log.d("@@@", "slide action up");
                break;
        }

        return true;
//        return super.onTouchEvent(event);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.save();
        canvas.drawCircle(screenX, screenY, 100, mPaint);
        canvas.drawText("手势滑动按钮", screenX, screenY, mTextPaint);
        canvas.restore();
    }
}
