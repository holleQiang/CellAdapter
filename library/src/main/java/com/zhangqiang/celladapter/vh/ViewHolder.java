package com.zhangqiang.celladapter.vh;

import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.annotation.IdRes;
import android.support.annotation.StringRes;
import android.text.TextWatcher;
import android.text.method.MovementMethod;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.CompoundButton;

public interface ViewHolder {

    <T extends View> T getView(@IdRes int viewId);

    View getView();

    ViewHolder setText(@IdRes int viewId, CharSequence charSequence) ;

    ViewHolder setImageResource(@IdRes int viewId, int imageResource);

    ViewHolder setImageBitmap(@IdRes int viewId, Bitmap bitmap);

    ViewHolder setImageDrawable(@IdRes int viewId, Drawable drawable);

    ViewHolder setBackgroundColor(@IdRes int viewId, int color);

    ViewHolder setBackgroundRes(@IdRes int viewId, int backgroundRes);

    ViewHolder setTextColor(@IdRes int viewId, int textColor);

    ViewHolder setTextColorRes(@IdRes int viewId, int textColorRes);

    ViewHolder setAlpha(@IdRes int viewId, float value);

    ViewHolder setVisible(@IdRes int viewId, boolean visible);

    ViewHolder setVisibility(@IdRes int viewId, int visible);

    ViewHolder addLinks(@IdRes int viewId, int mask);

    ViewHolder setTypeface(Typeface typeface, int... viewIds);

    ViewHolder setProgress(@IdRes int viewId, int progress);

    ViewHolder setProgress(@IdRes int viewId, int progress, int max);

    ViewHolder setMax(@IdRes int viewId, int max);

    ViewHolder setRating(@IdRes int viewId, float rating);

    ViewHolder setRating(@IdRes int viewId, float rating, int max);

    ViewHolder setTag(@IdRes int viewId, Object tag) ;

    ViewHolder setTag(@IdRes int viewId, int key, Object tag) ;

    ViewHolder setChecked(@IdRes int viewId, boolean checked);

    /**
     * 关于事件的
     */
    ViewHolder setOnClickListener(@IdRes int viewId, View.OnClickListener listener);

    ViewHolder setOnTouchListener(@IdRes int viewId, View.OnTouchListener listener);

    ViewHolder setOnLongClickListener(@IdRes int viewId, View.OnLongClickListener listener);

    ViewHolder setAdapter(@IdRes int viewId, Adapter adapter);

    ViewHolder setOnItemClickListener(@IdRes int viewId, AdapterView.OnItemClickListener itemClickListener);

    ViewHolder setCompoundDrawablePadding(@IdRes int viewId, int pad);

    ViewHolder setCompoundDrawablesWithIntrinsicBounds(@IdRes int viewId, Drawable left, Drawable top, Drawable right, Drawable bottom);

    ViewHolder setOnCheckedChangeListener(@IdRes int viewId, CompoundButton.OnCheckedChangeListener onCheckedChangeListener);

    ViewHolder setBackgroundResource(@IdRes int viewId, int resId);

    ViewHolder addTextChangedListener(@IdRes int viewId, TextWatcher textWatcher);

    ViewHolder setMovementMethod(@IdRes int viewId, MovementMethod movement);

    ViewHolder setEnable(@IdRes int viewId, boolean enable);

    int getVisibility(@IdRes int viewId);

    ViewHolder setLayoutParams(@IdRes int viewId, ViewGroup.LayoutParams layoutParams) ;

    ViewHolder setOnItemLongClickListener(@IdRes int viewId, AdapterView.OnItemLongClickListener onItemLongClickListener);

    ViewHolder removeAllViews(@IdRes int viewId);

    ViewHolder addView(@IdRes int viewId, View childView);

    ViewHolder setTextSize(@IdRes int viewId, int unit, float textSize);

    ViewHolder setTextSize(@IdRes int viewId, float textSize);

    ViewHolder setText(@IdRes int viewId, @StringRes int textRes);
}
