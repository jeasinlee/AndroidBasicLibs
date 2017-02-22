package simple.search.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.wwah.basekit.base.activity.BaseActivity;
import cn.wwah.common.assist.Check;
import cn.wwah.ui.view.RecycleViewDivider;
import simple.R;
import simple.search.adapter.HistoryAdaper;
import simple.search.adapter.QuickAdapter;
import simple.search.entity.GankM;
import simple.search.entity.SearchM;
import simple.search.iview.GankSearchIView;
import simple.search.presenter.GankSearchPresenter;

/**
 * author：LiuShenEn on 2017/2/20 15:14
 */
public class SimpleActivity extends BaseActivity implements GankSearchIView {


    @Bind(R.id.edt_search)
    EditText edtSearch;
    @Bind(R.id.btn_search)
    Button btnSearch;
    @Bind(R.id.rv_listGank)
    RecyclerView rvListGank;
    @Bind(R.id.rv_histroy)
    RecyclerView rvHistroy;
    @Bind(R.id.pb_lodin)
    ProgressBar pbLodin;
    private GankSearchPresenter gankSearchPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simple_a);
        ButterKnife.bind(this);
        init();
        initEvent();
    }

    private void initEvent() {

        edtSearch.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {
                    //此处为得到焦点时的处理内容
                    if (rvHistroy.getVisibility() == View.GONE) {
                        rvHistroy.setVisibility(View.VISIBLE);
                    }
                }

            }
        });
        edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (edtSearch.getText().toString().length() > 0) {
                    gankSearchPresenter.selectHistoryKey(edtSearch.getText().toString().trim());
                }else{
                    gankSearchPresenter.selectHistoryKey(10);
                }
            }
        });
    }

    private void init() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(SimpleActivity.this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvListGank.setLayoutManager(layoutManager);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.VERTICAL);
        rvHistroy.setLayoutManager(staggeredGridLayoutManager);
        rvHistroy.setVisibility(View.GONE);
        gankSearchPresenter = new GankSearchPresenter(SimpleActivity.this, SimpleActivity.this);
        gankSearchPresenter.selectHistoryKey(10);
    }


    @OnClick(R.id.btn_search)
    public void onClick() {
        String content = edtSearch.getText().toString();
        if (Check.isEmpty(content)) {
            mToast.show("请输入再搜索哦!");
        } else {
            gankSearchPresenter.findContentForGank(content);
        }
    }

    @Override
    public void showList(List<GankM> gankMs) {
        pbLodin.setVisibility(View.GONE);
        rvHistroy.setVisibility(View.GONE);
        QuickAdapter adapter = new QuickAdapter(gankMs);
        adapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
        rvListGank.addItemDecoration(new RecycleViewDivider(SimpleActivity.this, LinearLayoutManager.HORIZONTAL));
        rvListGank.setAdapter(adapter);

    }

    @Override
    public void lodinHistory(List<SearchM> mList) {
        if (rvHistroy.getVisibility() == View.GONE) {
            rvHistroy.setVisibility(View.VISIBLE);
        }
        HistoryAdaper adapter = new HistoryAdaper(mList, gankSearchPresenter);
        adapter.openLoadAnimation(BaseQuickAdapter.LOADING_VIEW);
        rvHistroy.setAdapter(adapter);

    }


    @Override
    public void showLoading() {
        pbLodin.setVisibility(View.VISIBLE);
    }
}
