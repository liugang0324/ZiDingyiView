package test.bwie.com.liugang20170612;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * @ Description:业务操作类
 * @ Date:2017/6/12
 * @ Author:刘刚
 */

public class CustomProgress extends View {
    //View的当前状态，默认为未开始
    // 画实心圆的画笔
    private Paint mCirclePaint;
    // 画圆环的画笔
    private Paint mRingPaint;
    // 画字体的画笔
    private Paint mTextPaint;
    private Paint mTextPaint1;
    // 圆形颜色
    private int mCircleColor;
    // 圆环颜色
    private int mRingColor;
    // 半径
    private float mRadius;
    // 圆环半径
    private float mRingRadius;
    // 圆环宽度
    private float mStrokeWidth;
    // 圆心x坐标
    private int mXCenter;
    // 圆心y坐标
    private int mYCenter;
    // 字的长度
    private float mTxtWidth;
    // 字的高度
    private float mTxtHeight;
    // 总进度
    private int mTotalProgress;
    // 当前进度
    private int mProgress;
    //大圆
    private Paint mBigPatient;
    //字体颜色
    private int mTextColor;
    //外圆颜色
    private int mBigCircleColor;
    private Paint mP;

    public CustomProgress(Context context, AttributeSet attrs) {
        super(context, attrs);
        // 获取自定义的属性
        initAttrs(context, attrs);
        initVariable();
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        TypedArray typeArray = context.getTheme().obtainStyledAttributes(attrs,
                R.styleable.CustomProgress, 0, 0);
        mRadius = typeArray.getDimension(R.styleable.CustomProgress_radius, 600);
        mStrokeWidth = typeArray.getDimension(R.styleable.CustomProgress_strokeWidth, 20);
        mCircleColor = typeArray.getColor(R.styleable.CustomProgress_circleColor, Color.BLACK);
        mRingColor = typeArray.getColor(R.styleable.CustomProgress_ringColor, Color.GRAY);
        mTotalProgress = typeArray.getInt(R.styleable.CustomProgress_totalProgress, 100);
        mTextColor = typeArray.getColor(R.styleable.CustomProgress_textColor, Color.WHITE);
        mBigCircleColor = typeArray.getColor(R.styleable.CustomProgress_bigCircleColor, Color.WHITE);

        typeArray.recycle();//注意这里要释放掉

        mRingRadius = mRadius + mStrokeWidth / 2;
    }



    private void initVariable() {
        mCirclePaint = new Paint();
        mCirclePaint.setAntiAlias(true);
        mCirclePaint.setColor(mCircleColor);
        mCirclePaint.setStrokeCap(Paint.Cap.ROUND);
        mCirclePaint.setStyle(Paint.Style.FILL);
        mP = new Paint();
        mP.setStrokeWidth(10);
        mP.setColor(Color.GRAY);
        mP.setAntiAlias(true);
        mP.setStyle(Paint.Style.FILL_AND_STROKE);

        mRingPaint = new Paint();
        mRingPaint.setAntiAlias(true);
        mRingPaint.setColor(mRingColor);
        mRingPaint.setStrokeCap(Paint.Cap.ROUND);
        mRingPaint.setStyle(Paint.Style.STROKE);
        mRingPaint.setStrokeWidth(mStrokeWidth);

        mTextPaint = new Paint();
        mTextPaint.setAntiAlias(true);
        mTextPaint.setStyle(Paint.Style.FILL);
        mTextPaint.setColor(mTextColor);
        mTextPaint.setTextSize(mRadius / 2);

        mTextPaint1 = new Paint();
        mTextPaint1.setAntiAlias(true);
        mTextPaint1.setStyle(Paint.Style.FILL);
        mTextPaint1.setColor(mTextColor);
        mTextPaint1.setTextSize(mRadius / 4);

        mBigPatient = new Paint();
        mBigPatient.setColor(mBigCircleColor);
        mBigPatient.setAntiAlias(true);
        mBigPatient.setStyle(Paint.Style.FILL);

        Paint.FontMetrics fm = mTextPaint.getFontMetrics();
        mTxtHeight = (int) Math.ceil(fm.descent - fm.ascent);

    }

    @Override
    protected void onDraw(Canvas canvas) {

        mXCenter = getWidth() / 2;
        mYCenter = getHeight() / 2;

        canvas.drawCircle(mXCenter, mYCenter, mRadius + mStrokeWidth, mBigPatient);
        canvas.drawCircle(mXCenter, mYCenter, mRadius, mCirclePaint);
        canvas.drawPoint(mXCenter, mYCenter,mP);
            /*RectF oval = new RectF();
            oval.left = (mXCenter - mRingRadius);
            oval.top = (mYCenter - mRingRadius);
            oval.right = mRingRadius * 2 + (mXCenter - mRingRadius);
            oval.bottom = mRingRadius * 2 + (mYCenter - mRingRadius);
            canvas.drawArc(oval, -90, ((float) mProgress / mTotalProgress) * 360, false, mRingPaint); //
*/
           canvas.drawCircle(mXCenter, mYCenter,mRadius*mProgress/100,mP);
            String txt = (int) (mProgress * 1.0f / mTotalProgress * 100) + "%";
            mTxtWidth = mTextPaint.measureText(txt, 0, txt.length());
            canvas.drawText(txt, mXCenter - mTxtWidth / 2, mYCenter + mTxtHeight / 4, mTextPaint);
            canvas.drawText("学习进度", mXCenter - mTxtWidth / 2, mYCenter + mTxtHeight / 4-30, mTextPaint1);
        }


    public void setProgress(int progress) {
        mProgress = progress;
        postInvalidate();
    }

    public void setmTotalProgress(int totalProgress) {
        mTotalProgress = totalProgress;
    }
    //图片可拖动
    private int lastX = 0;
    private int lastY = 0;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:

                lastX = (int) event.getRawX();
                lastY = (int) event.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:

                int dx = (int) event.getRawX() - lastX;
                int dy = (int) event.getRawY() - lastY;

                int left = getLeft() + dx;
                int top = getTop() + dy;
                int right = getRight() + dx;
                int bottom = getBottom() + dy;

                layout(left, top, right, bottom);
                lastX = (int) event.getRawX();
                lastY = (int) event.getRawY();


                break;
            case MotionEvent.ACTION_UP:
                break;
            default:
                break;
        }
        return true;
    }
}
