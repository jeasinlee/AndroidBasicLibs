package simple.search.adapter;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import simple.R;
import simple.search.entity.SearchM;
import simple.search.presenter.GankSearchPresenter;

public class HistoryAdaper extends BaseQuickAdapter<SearchM, BaseViewHolder> {

    private final GankSearchPresenter mgankSearchPresenter1;

    public HistoryAdaper(List<SearchM> items, GankSearchPresenter mgankSearchPresenter) {
        super(R.layout.history_item, items);
        mgankSearchPresenter1 = mgankSearchPresenter;
    }

    @Override
    protected void convert(BaseViewHolder viewHolder, final SearchM item) {
        viewHolder.setText(R.id.title, item.getKeyName());
        viewHolder.getView(R.id.title).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mgankSearchPresenter1.findContentForGank(item.getKeyName());
            }
        });
    }

}