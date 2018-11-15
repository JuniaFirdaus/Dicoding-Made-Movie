package juniafirdaus.com.dicodingmovie.base.mvp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import juniafirdaus.com.dicodingmovie.base.ui.BaseFragment;
import juniafirdaus.com.dicodingmovie.base.ui.BasePresenter;

public abstract class MvpFragment<P extends BasePresenter> extends BaseFragment {
    protected P presenter;

    protected abstract P createPresenter();

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        presenter = createPresenter();
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.dettachView();
        }
    }
}
