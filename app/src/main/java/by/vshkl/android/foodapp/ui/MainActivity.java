package by.vshkl.android.foodapp.ui;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.mikepenz.materialdrawer.Drawer.OnDrawerItemClickListener;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import by.vshkl.android.foodapp.R;
import by.vshkl.android.foodapp.mvp.presenter.MainPresenter;
import by.vshkl.android.foodapp.mvp.view.MainView;
import by.vshkl.android.foodapp.ui.view.MarqueeToolbar;
import by.vshkl.android.foodapp.util.DrawerUtils;
import by.vshkl.android.foodapp.util.Navigator;

public class MainActivity extends MvpAppCompatActivity implements MainView, OnClickListener, OnDrawerItemClickListener {

    @InjectPresenter MainPresenter presenter;

    private FrameLayout flFragmentContainer;
    private LinearLayout llEmpty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        flFragmentContainer = (FrameLayout) findViewById(R.id.fl_fragment_container);
        MarqueeToolbar tbToolbar = (MarqueeToolbar) findViewById(R.id.tb_toolbar);
        llEmpty = (LinearLayout) findViewById(R.id.ll_empty);

        setSupportActionBar(tbToolbar);

        DrawerUtils.initializeDrawer(this, tbToolbar, this, savedInstanceState);

        llEmpty.setOnClickListener(this);

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
            case R.id.ll_empty:
                presenter.downloadCatalog();
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
            case -1:
                presenter.updateCatalog();
                break;
        }
        return false;
    }

    @Override
    public void showEmpty() {
        flFragmentContainer.setVisibility(View.GONE);
        llEmpty.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideEmpty() {
        llEmpty.setVisibility(View.GONE);
        flFragmentContainer.setVisibility(View.VISIBLE);
    }

    @Override
    public void showMessage(int messageId) {
        Toast.makeText(this, getString(messageId), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showCatalog() {
        Navigator.navigateToCategories(this);
    }

    @Override
    public void showOffers(int categoryId, String categoryName) {
        Navigator.navigateToOffers(this, categoryId, categoryName);
    }

    @Override
    public void showOffer(int offerId) {
        Navigator.navigateToOffer(this, offerId);
    }

    @Override
    public void showContacts() {
        Navigator.navigateToContacts(this);
    }

    @Override
    public void updateCatalog() {
        presenter.updateCatalog();
    }

    public MainPresenter getPresenter() {
        return presenter;
    }

    public void setTitle(String title) {
        getSupportActionBar().setTitle(title);
    }
}
