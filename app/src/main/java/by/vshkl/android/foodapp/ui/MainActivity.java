package by.vshkl.android.foodapp.ui;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;

import by.vshkl.android.foodapp.R;
import by.vshkl.android.foodapp.mvp.presenter.MainPresenter;
import by.vshkl.android.foodapp.mvp.view.MainView;

public class MainActivity extends MvpAppCompatActivity implements MainView, OnClickListener {

    @InjectPresenter MainPresenter presenter;

    private FrameLayout flFragmentContainer;
    private TextView tvEmpty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        flFragmentContainer = (FrameLayout) findViewById(R.id.fl_fragment_container);
        tvEmpty = (TextView) findViewById(R.id.tv_empty);

        tvEmpty.setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        presenter.checkIfCatalogDownloaded();
        super.onStart();
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_empty:
                presenter.downloadMenu();
                break;
        }
    }

    @Override
    public void showEmpty() {
        flFragmentContainer.setVisibility(View.GONE);
        tvEmpty.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideEmpty() {
        tvEmpty.setVisibility(View.GONE);
        flFragmentContainer.setVisibility(View.VISIBLE);
    }
}
