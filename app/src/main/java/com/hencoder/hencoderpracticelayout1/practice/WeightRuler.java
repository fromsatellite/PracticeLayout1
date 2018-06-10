package com.hencoder.hencoderpracticelayout1.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.support.annotation.Px;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.OverScroller;

/**
 * 量体重的尺子.
 */

public class WeightRuler extends View {

    Paint paint = new Paint();
    Path path = new Path();
    private Paint textPaint = new Paint();
    private int screenW;
    private int centerX;
//    /**
//     * 最后滑动的X坐标
//     */
//    private int lastX;
//    /** 获取当前偏移的坐标 */
//    int biasX = 0;

    //当前刻度值
    private float mCurrentScale = 0;
    //最大刻度数
    private int mMaxLength = 0;
    //长度、最小可滑动值、最大可滑动值
    private int mLength, mMinPositionX = 0, mMaxPositionX = 0;
    //控制滑动
    private OverScroller mOverScroller;
    //记录落点
    private float mLastX = 0;
    //惯性最大最小速度
    private int mMaximumVelocity, mMinimumVelocity;
    //速度获取
    private VelocityTracker mVelocityTracker;
    //一半宽度
    private int mHalfWidth = 0;
    //一格大刻度多少格小刻度
    private int mCount = 10;
    //提前刻画量
    private int mDrawOffset = 0;

    public WeightRuler(Context context) {
        super(context);
        init(context);
    }

    public WeightRuler(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public WeightRuler(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        //获取屏幕的宽高初始化view显示的位置
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        screenW = wm.getDefaultDisplay().getWidth();
        centerX = (int)(screenW / 2);

        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setTextSize(40);
        mMaxLength = 650 - 490;
        mCurrentScale = 490;
        mOverScroller = new OverScroller(context);
        //配置速度
        mVelocityTracker = VelocityTracker.obtain();
        mMaximumVelocity = ViewConfiguration.get(context)
                .getScaledMaximumFlingVelocity();
        mMinimumVelocity = ViewConfiguration.get(context)
                .getScaledMinimumFlingVelocity();
        //第一次进入，跳转到设定刻度
        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                getViewTreeObserver().removeOnGlobalLayoutListener(this);
                goToScale(mCurrentScale);
            }
        });

    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Log.d("@@@", "onMeasure()");
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int measuredWidth = 200;
         measuredWidth = MeasureSpec.getSize(widthMeasureSpec);
        int measuredHeight = 160;
        // 第二步，使用 resolveSize() 来让子 View 的计算结果符合父 View 的限制
        measuredWidth = resolveSize(measuredWidth, widthMeasureSpec);
        measuredHeight = resolveSize(measuredHeight, heightMeasureSpec);
        // 第三步，调用 setMeasuredDimension() 来保存新的结果。
        setMeasuredDimension(measuredWidth, measuredHeight);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                getParent().requestDisallowInterceptTouchEvent(true);
                break;
            case MotionEvent.ACTION_MOVE:
                getParent().requestDisallowInterceptTouchEvent(true);
                break;
        }
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // 获取当前手指的坐标
        float currentX = event.getX();
        //开始速度检测
        if (mVelocityTracker == null) {
            mVelocityTracker = VelocityTracker.obtain();
        }
        mVelocityTracker.addMovement(event);

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                Log.d("@@@", "action down");
                if (!mOverScroller.isFinished()) {
                    mOverScroller.abortAnimation();
                }
                mLastX = currentX;
                break;
            case MotionEvent.ACTION_MOVE:
                Log.d("@@@", "action move");

                float moveX = mLastX - currentX;
                mLastX = currentX;
                scrollBy((int)(moveX),0);
                break;
            case MotionEvent.ACTION_UP:
                Log.d("@@@", "action up");
                // 处理松手后的Fling
                mVelocityTracker.computeCurrentVelocity(1000,mMaximumVelocity);
                int velocityX = (int) mVelocityTracker.getXVelocity();
                if (Math.abs(velocityX) > mMinimumVelocity) {
                    Log.d("@@@", "fling()");
                    fling(-velocityX); // 往右滑动，x逐渐增大，scrollX逐渐减小；往左，x逐渐减小，scrollX逐渐增大
                }else {
                    Log.d("@@@", "no fling()");
                    scrollBackToCurrentScale();
                }
                //VelocityTracker回收
                if (mVelocityTracker != null) {
                    mVelocityTracker.recycle();
                    mVelocityTracker = null;
                }
                break;
            case MotionEvent.ACTION_CANCEL:
                if (!mOverScroller.isFinished())
                {
                    mOverScroller.abortAnimation();
                }
                //VelocityTracker回收
                if (mVelocityTracker != null) {
                    mVelocityTracker.recycle();
                    mVelocityTracker = null;
                }
                break;
        }
        return true;
    }

    private void fling(int vX){
        mOverScroller.fling(getScrollX(), 0, vX, 0, mMinPositionX, mMaxPositionX, 0, 0);
        invalidate();
    }

    @Override
    public void scrollTo(@Px int x, @Px int y) {
        Log.d("@@@", "scrollTo x = "+x+", y = "+y);
        if (x < mMinPositionX)
        {
            x = mMinPositionX;
        }
        if (x > mMaxPositionX)
        {
            x = mMaxPositionX;
        }
        int scrollX = getScrollX();
        if (x != scrollX)
        {
            super.scrollTo(x, y);
        }

        mCurrentScale = scrollXtoScale(x);
        super.scrollTo(x, y);
    }

    //直接跳转到当前刻度
    public void goToScale(float scale){
        mCurrentScale = Math.round(scale);
        scrollTo(scaleToScrollX(mCurrentScale),0);

    }

    //把滑动偏移量scrollX转化为刻度Scale
    private float scrollXtoScale(int scrollX){
        return ((float) (scrollX - mMinPositionX) / mLength) *  mMaxLength + 490;
    }

    //把Scale转化为ScrollX
    private int scaleToScrollX(float scale){
        return (int) ((scale - 490) / mMaxLength * mLength + mMinPositionX);
    }

    //把移动后光标对准距离最近的刻度，就是回弹到最近刻度
    private void scrollBackToCurrentScale(){
        Log.d("@@@", "scrollBackToCurrentScale()");
        //渐变回弹
        mCurrentScale = Math.round(mCurrentScale);
        mOverScroller.startScroll(getScrollX(),0,scaleToScrollX(mCurrentScale) - getScrollX(),0,1000);
        invalidate();

        //立刻回弹
//        scrollTo(scaleToScrollX(mCurrentScale),0);
    }

    @Override
    public void computeScroll() {
        Log.d("@@@", "computeScroll()");
//        super.computeScroll();
        if (mOverScroller.computeScrollOffset()) {
            Log.d("@@@", "mOverScroller.computeScrollOffset()");
            scrollTo(mOverScroller.getCurrX(), mOverScroller.getCurrY());

            //这是最后OverScroller的最后一次滑动，如果这次滑动完了mCurrentScale不是整数，则把尺子移动到最近的整数位置
            if (!mOverScroller.computeScrollOffset() && mCurrentScale != Math.round(mCurrentScale)){
                Log.d("@@@", "这是最后OverScroller的最后一次滑动");
                //Fling完进行一次检测回滚
                scrollBackToCurrentScale();
            }
            invalidate();
        }
    }

    //获取控件宽高，设置相应信息
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mLength = (650 - 490) * 20;
        mHalfWidth = w / 2;
        mMinPositionX = -mHalfWidth;
        mMaxPositionX = mLength - mHalfWidth;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Log.d("@@@", "onDraw()");
        canvas.save();
        paint.reset();
        drawRuler(canvas);
        canvas.drawPath(path, paint);
        canvas.restore();

//        canvas.save();
//        paint.reset();
//        paint.setAntiAlias(true);
//        paint.setStyle(Paint.Style.STROKE);
//        paint.setColor(Color.GREEN);
//        paint.setStrokeWidth(10);
////        paint.setStrokeCap(Paint.Cap.ROUND);
//        canvas.drawLine(centerX, 100, centerX, 180, paint);
//        canvas.restore();
    }

    private void drawRuler(Canvas canvas){
        path.reset();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);

        for (int i = 490;i <= 650; i++){
            float locationX = (i - 490) * 20;
            path.moveTo(locationX, 0);
            if (i % 10 == 0){
                path.rLineTo(0, 100);
                canvas.drawText(String.valueOf(i / 10), locationX, 150, textPaint);
            } else {
                path.rLineTo(0, 50);
            }
        }
    }

}
