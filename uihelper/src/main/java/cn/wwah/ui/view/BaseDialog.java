package cn.wwah.ui.view;

import android.app.Dialog;
import android.content.Context;
import android.view.View;

import cn.wwah.ui.R;


/**
 * @author liushenen
 *         <p/>
 *         View view = View.inflate(this,
 *         R.layout.activity_patientinformation_left, null); Dialog dialog =
 *         baseDialog.getDialog(SettlementActivity.this, view); dialog.show();
 */
public class BaseDialog {
    static Dialog dialog = null;
    static Context Activity = null;

    private BaseDialog() {

    }

    /**
     * dialog在单例的模式下，只适用于同一个Context，因为每一个activity的context都不是同一个对象。
     * 而application是单例的，但是dialog不能通过application来new出。
     * 故:只能在每一个activity保持单独的一个dialog。不同的activity需要重新new 出来
     *
     * @param mActivity
     * @param view
     * @return
     */
    public static Dialog getDialog(Context mActivity, View view) {
        if (Activity != mActivity) {
            Activity = mActivity;
            dialog = new Dialog(mActivity, R.style.Dialog_FS); // 设置全屏样式
        }
        dialog.setContentView(view); // 设置dialog的布局

        return dialog;
    }

    /**
     * 必须在onstop时关闭,因为会导致强引用出现内存泄露
     */
    public static void close() {
        if (Activity != null) {
            dialog = null;
            Activity = null;
        }
    }

}
