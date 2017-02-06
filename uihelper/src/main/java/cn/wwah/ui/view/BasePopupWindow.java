package cn.wwah.ui.view;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;

public abstract class BasePopupWindow extends PopupWindow {
    private View mMenuView;
    public Activity mcontext; // 用于将屏幕回复透明度
    public  WindowManager.LayoutParams lp;// 获取屏幕

    @Override
    public void showAtLocation(View parent, int gravity, int x, int y) {
        // TODO Auto-generated method stub
        super.showAtLocation(parent, gravity, x, y);
    }

    public BasePopupWindow(final Activity context, int width, int height) {
        super(context);
        mcontext = context;
        mMenuView = getView();
        setpopstyle(context, width, height);
    }

    public abstract View getView();

    public void setpopstyle(final Activity context, int width, int height) {
        mcontext = context;
        // 设置SelectPicPopupWindow的View
        this.setContentView(mMenuView);
        // 设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(width);
        // 设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(height);
        // 设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
//        // 设置SelectPicPopupWindow弹出窗体动画效果
//        this.setAnimationStyle(R.style.AnimBottom);
        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0x00000000);
        // 设置SelectPicPopupWindow弹出窗体的背景
        this.setBackgroundDrawable(dw);
        lp = context.getWindow().getAttributes();
        lp.alpha = .5f;
        context.getWindow().setAttributes(lp);
    }

    @Override
    public void dismiss() {
        super.dismiss();
        lp.alpha = 1.0f;
        mcontext.getWindow().setAttributes(lp);
    }

}
