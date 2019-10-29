package com.zhangqiang.celladapter.vh;

import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextWatcher;
import android.text.method.MovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.CompoundButton;

public class RVViewHolder extends RecyclerView.ViewHolder implements ViewHolder {

    private ViewHolder delegate;

    public static RVViewHolder create(ViewGroup viewGroup,int layoutId){
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(layoutId, viewGroup, false);
        return new RVViewHolder(view);
    }

    public RVViewHolder(@NonNull View itemView) {
        super(itemView);
        delegate = new ViewHolderImpl(itemView);
    }

    @Override
    public <T extends View> T getView(int viewId) {
        return delegate.getView(viewId);
    }

    @Override
    public View getView() {
        return delegate.getView();
    }

    @Override
    public ViewHolder setText(int viewId, CharSequence charSequence) {
        return delegate.setText(viewId, charSequence);
    }

    @Override
    public ViewHolder setImageResource(int viewId, int imageResource) {
        return delegate.setImageResource(viewId, imageResource);
    }

    @Override
    public ViewHolder setImageBitmap(int viewId, Bitmap bitmap) {
        return delegate.setImageBitmap(viewId, bitmap);
    }

    @Override
    public ViewHolder setImageDrawable(int viewId, Drawable drawable) {
        return delegate.setImageDrawable(viewId, drawable);
    }

    @Override
    public ViewHolder setBackgroundColor(int viewId, int color) {
        return delegate.setBackgroundColor(viewId, color);
    }

    @Override
    public ViewHolder setBackgroundRes(int viewId, int backgroundRes) {
        return delegate.setBackgroundRes(viewId, backgroundRes);
    }

    @Override
    public ViewHolder setTextColor(int viewId, int textColor) {
        return delegate.setTextColor(viewId, textColor);
    }

    @Override
    public ViewHolder setTextColorRes(int viewId, int textColorRes) {
        return delegate.setTextColorRes(viewId, textColorRes);
    }

    @Override
    public ViewHolder setAlpha(int viewId, float value) {
        return delegate.setAlpha(viewId, value);
    }

    @Override
    public ViewHolder setVisible(int viewId, boolean visible) {
        return delegate.setVisible(viewId, visible);
    }

    @Override
    public ViewHolder setVisibility(int viewId, int visible) {
        return delegate.setVisibility(viewId, visible);
    }

    @Override
    public ViewHolder addLinks(int viewId, int mask) {
        return delegate.addLinks(viewId, mask);
    }

    @Override
    public ViewHolder setTypeface(Typeface typeface, int... viewIds) {
        return delegate.setTypeface(typeface, viewIds);
    }

    @Override
    public ViewHolder setProgress(int viewId, int progress) {
        return delegate.setProgress(viewId, progress);
    }

    @Override
    public ViewHolder setProgress(int viewId, int progress, int max) {
        return delegate.setProgress(viewId, progress, max);
    }

    @Override
    public ViewHolder setMax(int viewId, int max) {
        return delegate.setMax(viewId, max);
    }

    @Override
    public ViewHolder setRating(int viewId, float rating) {
        return delegate.setRating(viewId, rating);
    }

    @Override
    public ViewHolder setRating(int viewId, float rating, int max) {
        return delegate.setRating(viewId, rating, max);
    }

    @Override
    public ViewHolder setTag(int viewId, Object tag) {
        return delegate.setTag(viewId, tag);
    }

    @Override
    public ViewHolder setTag(int viewId, int key, Object tag) {
        return delegate.setTag(viewId, key, tag);
    }

    @Override
    public ViewHolder setChecked(int viewId, boolean checked) {
        return delegate.setChecked(viewId, checked);
    }

    @Override
    public ViewHolder setOnClickListener(int viewId, View.OnClickListener listener) {
        return delegate.setOnClickListener(viewId, listener);
    }

    @Override
    public ViewHolder setOnTouchListener(int viewId, View.OnTouchListener listener) {
        return delegate.setOnTouchListener(viewId, listener);
    }

    @Override
    public ViewHolder setOnLongClickListener(int viewId, View.OnLongClickListener listener) {
        return delegate.setOnLongClickListener(viewId, listener);
    }

    @Override
    public ViewHolder setAdapter(int viewId, Adapter adapter) {
        return delegate.setAdapter(viewId, adapter);
    }

    @Override
    public ViewHolder setOnItemClickListener(int viewId, AdapterView.OnItemClickListener itemClickListener) {
        return delegate.setOnItemClickListener(viewId, itemClickListener);
    }

    @Override
    public ViewHolder setCompoundDrawablePadding(int viewId, int pad) {
        return delegate.setCompoundDrawablePadding(viewId, pad);
    }

    @Override
    public ViewHolder setCompoundDrawablesWithIntrinsicBounds(int viewId, Drawable left, Drawable top, Drawable right, Drawable bottom) {
        return delegate.setCompoundDrawablesWithIntrinsicBounds(viewId, left, top, right, bottom);
    }

    @Override
    public ViewHolder setOnCheckedChangeListener(int viewId, CompoundButton.OnCheckedChangeListener onCheckedChangeListener) {
        return delegate.setOnCheckedChangeListener(viewId, onCheckedChangeListener);
    }

    @Override
    public ViewHolder setBackgroundResource(int viewId, int resId) {
        return delegate.setBackgroundResource(viewId, resId);
    }

    @Override
    public ViewHolder addTextChangedListener(int viewId, TextWatcher textWatcher) {
        return delegate.addTextChangedListener(viewId, textWatcher);
    }

    @Override
    public ViewHolder setMovementMethod(int viewId, MovementMethod movement) {
        return delegate.setMovementMethod(viewId, movement);
    }

    @Override
    public ViewHolder setEnable(int viewId, boolean enable) {
        return delegate.setEnable(viewId, enable);
    }

    @Override
    public int getVisibility(int viewId) {
        return delegate.getVisibility(viewId);
    }

    @Override
    public ViewHolder setLayoutParams(int viewId, ViewGroup.LayoutParams layoutParams) {
        return delegate.setLayoutParams(viewId, layoutParams);
    }

    @Override
    public ViewHolder setOnItemLongClickListener(int viewId, AdapterView.OnItemLongClickListener onItemLongClickListener) {
        return delegate.setOnItemLongClickListener(viewId, onItemLongClickListener);
    }

    @Override
    public ViewHolder removeAllViews(int viewId) {
        return delegate.removeAllViews(viewId);
    }

    @Override
    public ViewHolder addView(int viewId, View childView) {
        return delegate.addView(viewId, childView);
    }

    @Override
    public ViewHolder setTextSize(int viewId, int unit, float textSize) {
        return delegate.setTextSize(viewId, unit, textSize);
    }

    @Override
    public ViewHolder setTextSize(int viewId, float textSize) {
        return delegate.setTextSize(viewId, textSize);
    }

    @Override
    public ViewHolder setText(int viewId, int textRes) {
        return delegate.setText(viewId, textRes);
    }
}
