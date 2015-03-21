
package com.example.sample;

import java.io.IOException;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.text.Html;
import android.text.Spanned;
import android.util.AttributeSet;
import android.view.View;

import com.caverock.androidsvg.SVG;
import com.caverock.androidsvg.SVGParseException;

public class CustomImageView extends View {

    private static final float SCALING_X_TOP_CONTAINER = 0.43f;

    private static final float SCALING_Y_TOP_CONTAINER = 0.33f;

    private static final float SCALING_X_BOTTOM_CONTAINER = 0.32f;

    private static final float SCALING_Y_BOTTOM_CONTAINER = 0.26f;
    private Paint mTextPaint;
    private String countText;
    private int countTextSize;
    private Spanned countValueText;
    private String sessionText;
    private Spanned sessionValueText;
    private int countValueTextSize;
    private int sessionTextSize;
    private int sessionValueTextSize;

    public CustomImageView(Context context) {
        super(context);
        init();
    }

    public CustomImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        // TODO: add Dimesnion for different resolution
        countText = getResources().getString(R.string.count);
        countTextSize = 22;
        countValueText =  Html.fromHtml(getResources().getString(R.string.count_value));
        countValueTextSize = 34;
        sessionText = getResources().getString(R.string.session_details);
        sessionTextSize = 18;
        sessionValueText = Html.fromHtml(getResources().getString(R.string.session_value));
        sessionValueTextSize = 28;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        try {
            final SVG svg = SVG.getFromAsset(getContext().getAssets(), "svgstatus.svg");
            RectF rect = svg.getDocumentViewBox();
            svg.renderToCanvas(canvas);
            mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
            mTextPaint.setStyle(Paint.Style.FILL);
            mTextPaint.setTextSize(countTextSize);
            mTextPaint.setColor(getResources().getColor(R.color.title_text_color));
            float x = (SCALING_X_TOP_CONTAINER * rect.width() - (mTextPaint.measureText(countText, 0,
                    countText.length() - 1) / 2));
            float y = (SCALING_Y_TOP_CONTAINER
                    * rect.height() - mTextPaint.getTextSize() / 2);
            canvas.drawText(countText, x, y, mTextPaint);
            Paint mSubtitlePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
            mSubtitlePaint.setStyle(Paint.Style.FILL);
            mSubtitlePaint.setTextSize(countValueTextSize);
            x = (SCALING_X_TOP_CONTAINER * rect.width() - (mSubtitlePaint.measureText(countValueText, 0,
                    countValueText.length() - 1) / 2));
            y += countTextSize + 10;
            mSubtitlePaint.setColor(getResources().getColor(R.color.subtitle_text_color));
            canvas.drawText(countValueText.toString(), x, y, mSubtitlePaint);
            Paint mSessionPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
            mSessionPaint.setStyle(Paint.Style.FILL);
            mSessionPaint.setTextSize(sessionTextSize);
            mSessionPaint.setColor(getResources().getColor(R.color.title_text_color));
           float bottomY = ((1-SCALING_Y_BOTTOM_CONTAINER) * rect.height() - mSessionPaint.getTextSize());
            float bottomX = ((1-SCALING_X_BOTTOM_CONTAINER) * rect.width() - (mSessionPaint.measureText(
                    sessionText, 0, sessionText.length() - 1) / 2));
            canvas.drawText(sessionText, bottomX, bottomY, mSessionPaint);
            Paint mSessionValuePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
            mSessionValuePaint.setStyle(Paint.Style.FILL);
            mSessionValuePaint.setTextSize(sessionValueTextSize);
            mSessionValuePaint.setColor(getResources().getColor(R.color.subtitle_text_color));
            bottomY +=  mTextPaint.getTextSize() + 10;
            bottomX = ((1-SCALING_X_BOTTOM_CONTAINER) * rect.width() - (mSessionValuePaint.measureText(
                    sessionValueText, 0, sessionValueText.length() - 1) / 2));
            canvas.drawText(sessionValueText.toString(), bottomX, bottomY, mSessionValuePaint);
          } catch (IOException | SVGParseException e) {
            // Handle IOException here
        }
    }

}
