package cn.zeffect.view.progressbutton;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.Button;

/**
 * 带进度的Button
 */
public class ProgressButton extends Button {
    public static final int TYPE_FILL = 0;
    public static final int TYPE_STROKE = 1;

    private Paint mPaint = new Paint();
    private int mProgress;
    private int currentType = TYPE_FILL;
    /**
     * 默认颜色
     **/
    private int mDefaultColor = 0xffff472f;

    public ProgressButton(Context context) {
        super(context);
    }

    public ProgressButton(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ProgressButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.ProgressButton, defStyle, 0);
        try {
            mDefaultColor = a.getColor(R.styleable.ProgressButton_border_color, mDefaultColor);
        } finally {
            a.recycle();
        }
    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        if (currentType == TYPE_FILL) {
            mPaint.setColor(mDefaultColor);
            mPaint.setAntiAlias(true);
            mPaint.setAlpha(255);
            mPaint.setStrokeWidth(1.0f);
            Rect rect = new Rect();
            canvas.getClipBounds(rect);
            rect.right = rect.left + (mProgress * getWidth() / 100);
            canvas.drawRoundRect(new RectF(rect), 8.0f, 8.0f, mPaint);
        } else if (currentType == TYPE_STROKE) {
            mPaint.setAntiAlias(true);
            mPaint.setColor(mDefaultColor);
            mPaint.setAlpha(255);
            Rect rect = new Rect();
            canvas.getClipBounds(rect);
            Paint paint1, paint2, paint3, paint4;
            if (mProgress >= 0 && mProgress < 25) {
                paint1 = new Paint(mPaint);
                Rect temp = new Rect(rect.left,
                        rect.top,
                        rect.left + mProgress * (getWidth())
                                / 25,
                        rect.top + 2);
                canvas.drawRect(temp, paint1);
            } else if (mProgress < 50) {
                paint1 = new Paint(mPaint);
                Rect rect1 = new Rect(rect.left,
                        rect.top, rect.right,
                        rect.top + 2);
                canvas.drawRect(rect1, paint1);
                paint2 = new Paint(mPaint);
                Rect rect2 = new Rect(rect.right - 2,
                        rect.top, rect.right,
                        rect.top + (mProgress - 25) *
                                (getHeight()) / 25);
                canvas.drawRect(rect2, paint2);
            } else if (mProgress < 75) {
                paint1 = new Paint(mPaint);
                Rect rect1 = new Rect(rect.left,
                        rect.top, rect.right,
                        rect.top + 2);
                canvas.drawRect(rect1, paint1);

                paint2 = new Paint(mPaint);
                Rect rect2 = new Rect(rect.right - 2,
                        rect.top, rect.right,
                        rect.bottom);
                canvas.drawRect(rect2, paint2);

                paint3 = new Paint(mPaint);
                Rect rect3 = new Rect(
                        rect.right - (mProgress - 50) *
                                (getWidth()) / 25,
                        rect.bottom - 2,
                        rect.right,
                        rect.bottom);
                canvas.drawRect(rect3, paint3);
            } else if (mProgress <= 100) {
                paint1 = new Paint(mPaint);
                Rect rect1 = new Rect(
                        rect.left,
                        rect.top, rect.right,
                        rect.top + 2);
                canvas.drawRect(rect1, paint1);

                paint2 = new Paint(mPaint);
                Rect rect2 = new Rect(
                        rect.right - 2,
                        rect.top, rect.right,
                        rect.bottom);
                canvas.drawRect(rect2, paint2);

                paint3 = new Paint(mPaint);
                Rect rect3 = new Rect(
                        rect.left,
                        rect.bottom - 2, rect.right,
                        rect.bottom);
                canvas.drawRect(rect3, paint3);

                paint4 = new Paint(mPaint);
                Rect rect4 = new Rect(
                        rect.left,
                        rect.bottom - (mProgress - 75) *
                                (getHeight()) / 25,
                        rect.left + 2,
                        rect.bottom);
                canvas.drawRect(rect4, paint4);
            }
        }

        super.onDraw(canvas);
    }

    public void updateProgress(int progress) {
        if (progress >= 0 && progress <= 100) {
            mProgress = progress;
            invalidate();
        } else if (progress < 0) {
            mProgress = 0;
            invalidate();
        } else if (progress > 100) {
            mProgress = 100;
            invalidate();
        }
    }

    public void setType(int type) {
        if (type == TYPE_FILL || type == TYPE_STROKE)
            currentType = type;
        else
            currentType = TYPE_FILL;
    }
}
