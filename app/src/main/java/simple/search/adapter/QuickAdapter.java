package simple.search.adapter;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.thefinestartist.finestwebview.FinestWebView;

import java.util.List;

import simple.R;
import simple.search.entity.GankM;

public class QuickAdapter extends BaseQuickAdapter<GankM, BaseViewHolder> {
    public QuickAdapter(List<GankM> items) {
        super(R.layout.gank_item, items);
    }

    @Override
    protected void convert(BaseViewHolder viewHolder, final GankM item) {
        viewHolder.setText(R.id.title, item.getTitle())
                .setText(R.id.type, item.getType());
        viewHolder.getView(R.id.ll_view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /**
                 * 跳转至webView
                 */

                new FinestWebView.Builder(mContext).show(item.getUrl());
            }
        });
    }

}