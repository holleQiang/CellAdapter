package com.zhangqiang.celladapter.vh;

import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.annotation.StringRes;
import android.support.v7.widget.RecyclerView;
import android.text.TextWatcher;
import android.text.method.MovementMethod;
import android.text.util.Linkify;
import android.util.SparseArray;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Checkable;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

public final class ViewHolder extends RecyclerView.ViewHolder{

    private final SparseArray<View> views = new SparseArray<>();

    private View contentView;

    public static ViewHolder create(ViewGroup viewGroup, int layoutId){
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(layoutId, viewGroup, false);
        return new ViewHolder(view);
    }

    public ViewHolder(View contentView) {
        super(contentView);
        this.contentView = contentView;
    }

    @SuppressWarnings("unchecked")
    public <T extends View> T getView(int viewId) {

        View view = views.get(viewId);

        if (view == null) {

            view = getView().findViewById(viewId);
            if (view != null) {

                views.put(viewId, view);
            }
        }
        return (T) view;
    }

   
    public View getView() {
        return contentView;
    }

   
    public ViewHolder setText(int viewId, CharSequence charSequence) {

        TextView textView = getView(viewId);
        textView.setText(charSequence);
        return this;
    }

   
    public ViewHolder setImageResource(int viewId, int imageResource) {

        ImageView imageView = getView(viewId);
        imageView.setImageResource(imageResource);
        return this;
    }

   
    public ViewHolder setImageBitmap(int viewId, Bitmap bitmap) {
        ImageView view = getView(viewId);
        view.setImageBitmap(bitmap);
        return this;
    }

   
    public ViewHolder setImageDrawable(int viewId, Drawable drawable) {
        ImageView view = getView(viewId);
        view.setImageDrawable(drawable);
        return this;
    }

   
    public ViewHolder setBackgroundColor(int viewId, int color) {
        View view = getView(viewId);
        view.setBackgroundColor(color);
        return this;
    }

    public ViewHolder setBackgroundRes(int viewId, int backgroundRes) {
        View view = getView(viewId);
        view.setBackgroundResource(backgroundRes);
        return this;
    }

   
    public ViewHolder setTextColor(int viewId, int textColor) {
        TextView view = getView(viewId);
        view.setTextColor(textColor);
        return this;
    }

   
    public ViewHolder setTextColorRes(int viewId, int textColorRes) {
        TextView view = getView(viewId);
        view.setTextColor(view.getResources().getColor(textColorRes));
        return this;
    }

   
    public ViewHolder setAlpha(int viewId, float value) {

        getView(viewId).setAlpha(value);
        return this;
    }

   
    public ViewHolder setVisible(int viewId, boolean visible) {
        View view = getView(viewId);
        view.setVisibility(visible ? View.VISIBLE : View.GONE);
        return this;
    }

   
    public ViewHolder setVisibility(int viewId, int visible) {
        View view = getView(viewId);
        view.setVisibility(visible);
        return this;
    }

   
    public ViewHolder addLinks(int viewId, int mask) {
        TextView view = getView(viewId);
//        Linkify.addLinks(view, Linkify.ALL);
        Linkify.addLinks(view, mask);
        return this;
    }

   
    public ViewHolder setTypeface(Typeface typeface, int... viewIds) {
        for (int viewId : viewIds) {
            TextView view = getView(viewId);
            view.setTypeface(typeface);
            view.setPaintFlags(view.getPaintFlags() | Paint.SUBPIXEL_TEXT_FLAG);
        }
        return this;
    }

   
    public ViewHolder setProgress(int viewId, int progress) {
        ProgressBar view = getView(viewId);
        view.setProgress(progress);
        return this;
    }

   
    public ViewHolder setProgress(int viewId, int progress, int max) {
        ProgressBar view = getView(viewId);
        view.setMax(max);
        view.setProgress(progress);
        return this;
    }

   
    public ViewHolder setMax(int viewId, int max) {
        ProgressBar view = getView(viewId);
        view.setMax(max);
        return this;
    }

   
    public ViewHolder setRating(int viewId, float rating) {
        RatingBar view = getView(viewId);
        view.setRating(rating);
        return this;
    }

   
    public ViewHolder setRating(int viewId, float rating, int max) {
        RatingBar view = getView(viewId);
        view.setMax(max);
        view.setRating(rating);
        return this;
    }

   
    public ViewHolder setTag(int viewId, Object tag) {
        View view = getView(viewId);
        view.setTag(tag);
        return this;
    }

   
    public ViewHolder setTag(int viewId, int key, Object tag) {
        View view = getView(viewId);
        view.setTag(key, tag);
        return this;
    }

   
    public ViewHolder setChecked(int viewId, boolean checked) {
        Checkable view = getView(viewId);
        view.setChecked(checked);
        return this;
    }

    /**
     * 关于事件的
     */
   
    public ViewHolder setOnClickListener(int viewId, View.OnClickListener listener) {
        View view = getView(viewId);
        view.setOnClickListener(listener);
        return this;
    }

   
    public ViewHolder setOnTouchListener(int viewId, View.OnTouchListener listener) {
        View view = getView(viewId);
        view.setOnTouchListener(listener);
        return this;
    }

   
    public ViewHolder setOnLongClickListener(int viewId, View.OnLongClickListener listener) {
        View view = getView(viewId);
        view.setOnLongClickListener(listener);
        return this;
    }

   
    public ViewHolder setAdapter(int viewId, Adapter adapter){

        AdapterView<Adapter> adapterView = getView(viewId);
        adapterView.setAdapter(adapter);
        return this;
    }

   
    public ViewHolder setOnItemClickListener(int viewId, AdapterView.OnItemClickListener itemClickListener){

        AdapterView<Adapter> adapterView = getView(viewId);
        adapterView.setOnItemClickListener(itemClickListener);
        return this;
    }

   
    public ViewHolder setCompoundDrawablePadding(int viewId, int pad){

        TextView textView = getView(viewId);
        textView.setCompoundDrawablePadding(pad);
        return this;
    }

   
    public ViewHolder setCompoundDrawablesWithIntrinsicBounds(int viewId, Drawable left, Drawable top, Drawable right, Drawable bottom) {

        TextView textView = getView(viewId);
        textView.setCompoundDrawablesWithIntrinsicBounds(left, top, right, bottom);
        return this;
    }

   
    public ViewHolder setOnCheckedChangeListener(int viewId, CompoundButton.OnCheckedChangeListener onCheckedChangeListener){

        CompoundButton checkBox = getView(viewId);
        checkBox.setOnCheckedChangeListener(onCheckedChangeListener);
        return this;
    }

   
    public ViewHolder setBackgroundResource(int viewId, int resId) {

        View v = getView(viewId);
        v.setBackgroundResource(resId);
        return this;
    }

   
    public ViewHolder addTextChangedListener(int viewId, TextWatcher textWatcher) {

        EditText v = getView(viewId);
        v.addTextChangedListener(textWatcher);
        return this;
    }

   
    public ViewHolder setMovementMethod(int viewId, MovementMethod movement) {

        TextView textView = getView(viewId);
        textView.setMovementMethod(movement);
        return this;
    }

   
    public ViewHolder setEnable(int viewId, boolean enable) {

        getView(viewId).setEnabled(enable);
        return this;
    }

   
    public int getVisibility(int viewId) {

        return getView(viewId).getVisibility();
    }

   
    public ViewHolder setLayoutParams(int viewId, ViewGroup.LayoutParams layoutParams) {

        getView(viewId).setLayoutParams(layoutParams);
        return this;
    }

   
    public ViewHolder setOnItemLongClickListener(int viewId, AdapterView.OnItemLongClickListener onItemLongClickListener) {

        AdapterView adapterView = getView(viewId);
        adapterView.setOnItemLongClickListener(onItemLongClickListener);
        return this;
    }

   
    public ViewHolder removeAllViews(int viewId) {

        ViewGroup viewGroup = getView(viewId);
        viewGroup.removeAllViews();
        return this;
    }

   
    public ViewHolder addView(int viewId, View childView) {

        ViewGroup viewGroup = getView(viewId);
        viewGroup.addView(childView);
        return this;
    }

   
    public ViewHolder setTextSize(int viewId, int unit, float textSize) {

        TextView textView = getView(viewId);
        textView.setTextSize(unit,textSize);
        return this;
    }

   
    public ViewHolder setTextSize(int viewId, float textSize) {

        TextView textView = getView(viewId);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP,textSize);
        return this;
    }

   
    public ViewHolder setText(int viewId, @StringRes int text) {

        TextView textView = getView(viewId);
        textView.setText(text);
        return this;
    }
}
