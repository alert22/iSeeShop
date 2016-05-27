package ium.progetto.iseeshop;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.TextView;

/**
 * Created by robin on 26/05/2016.
 */
public class IndicatorView extends FrameLayout {

    private int fontSize = 25;
    private TextView textTab;
    private boolean selected = false;
    private int coloreLinea = Color.parseColor("#009999");
    private Paint _paint;
    private FrameLayout.LayoutParams params;
    private int grandezzaBarra = 10;


    public IndicatorView(Context context) {
        super(context);
        textTab = new TextView(getContext());
        textTab.setText("tabHost");
        textTab.setTextColor(Color.BLACK);
        textTab.setTextSize(fontSize);
        textTab.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);
        textTab.setPadding(0,30,0,30);
        this.addView(textTab);
        this.setClickable(true);
        this.setBackground(getResources().getDrawable(R.drawable.ripple));
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (selected) {
            _paint = new Paint();
            _paint.setColor(coloreLinea);
            _paint.setStyle(Paint.Style.FILL);
            canvas.drawRect(new Rect(0, getMeasuredHeight()-grandezzaBarra, getMeasuredWidth(), 200), _paint);
            invalidate();
        }

    }

    public void setGrandezzaBarra(int grandezzaBarra) {
        this.grandezzaBarra = grandezzaBarra;
    }

    public void setColoreBarra(int colore) {
        _paint.setColor(colore);
        invalidate();
    }

    public void setBackgroundColore(int colore) {
        this.setBackgroundColor(colore);
        invalidate();
    }

    public void setTextColor(int colore) {
        textTab.setTextColor(colore);
        invalidate();
    }

    public void setText(String str) {
        textTab.setText(str);
        invalidate();
    }

    public void setTextSize(int size) {
        textTab.setTextSize(size);
        invalidate();
    }

    @Override
    public boolean isSelected() {
        return selected;
    }

    @Override
    public void setSelected(boolean selected) {
        this.selected = selected;
        this.invalidate();
    }
}
