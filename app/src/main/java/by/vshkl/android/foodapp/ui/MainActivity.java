package by.vshkl.android.foodapp.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.Drawer.OnDrawerItemClickListener;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import by.vshkl.android.foodapp.R;
import by.vshkl.android.foodapp.mvp.presenter.MainPresenter;
import by.vshkl.android.foodapp.mvp.view.MainView;
import by.vshkl.android.foodapp.ui.fragment.CategoriesFragment;
import by.vshkl.android.foodapp.util.DrawerUtils;

public class MainActivity extends MvpAppCompatActivity implements MainView, OnClickListener, OnDrawerItemClickListener {

    @InjectPresenter MainPresenter presenter;

    private FrameLayout flFragmentContainer;
    private Toolbar tbToolbar;
    private TextView tvEmpty;
    private Drawer ndMenu;

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
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        Fragment fragment = CategoriesFragment.newInstance();
        fragmentTransaction.replace(R.id.fl_fragment_container, fragment, fragment.getTag());
        fragmentTransaction.commit();
    }

    @Override
    public void showContacts() {

    }
}
