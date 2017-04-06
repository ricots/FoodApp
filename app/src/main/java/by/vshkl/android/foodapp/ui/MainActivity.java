package by.vshkl.android.foodapp.ui;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.mikepenz.materialdrawer.Drawer.OnDrawerItemClickListener;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import by.vshkl.android.foodapp.R;
import by.vshkl.android.foodapp.mvp.presenter.MainPresenter;
import by.vshkl.android.foodapp.mvp.view.MainView;
import by.vshkl.android.foodapp.util.DrawerUtils;
import by.vshkl.android.foodapp.util.Navigator;

public class MainActivity extends MvpAppCompatActivity implements MainView, OnClickListener, OnDrawerItemClickListener {

    @InjectPresenter MainPresenter presenter;

    private FrameLayout flFragmentContainer;
    private Toolbar tbToolbar;
    private TextView tvEmpty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        flFragmentContainer = (FrameLayout) findViewById(R.id.fl_fragment_container);
        tbToolbar = (Toolbar) findViewById(R.id.tb_toolbar);
        tvEmpty = (TextView) findViewById(R.id.tv_empty);
        DrawerUtils.initializeDrawer(this, tbToolbar, this);

        setSupportActionBar(tbToolbar);

        tvEmpty.setOnClickListener(this);

        presenter.checkIfCatalogDownloaded();
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
    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
        switch (position) {
            case 1:
                presenter.showCatalog();
                break;
            case 2:
                presenter.showContacts();
                break;
        }
        return false;
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

    @Override
    public void showCatalog() {
        Navigator.navigateToCategories(this);
    }

    @Override
    public void showOffers(int categoryId) {
        Navigator.navigateToOffers(this, categoryId);
    }

    @Override
    public void showOffer(int offerId) {
        Navigator.navigateToOffer(this, offerId);
    }

    @Override
    public void showContacts() {

    }

    public MainPresenter getPresenter() {
        return presenter;
    }
}
