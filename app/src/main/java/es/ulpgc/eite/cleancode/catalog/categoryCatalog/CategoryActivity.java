package es.ulpgc.eite.cleancode.catalog.categoryCatalog;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;


import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import es.ulpgc.eite.cleancode.catalog.R;
import es.ulpgc.eite.cleancode.catalog.app.CategoryItem;
import es.ulpgc.eite.cleancode.catalog.products.ProductListActivity;

public class CategoryActivity
        extends AppCompatActivity implements CategoryContract.View {

    public static String TAG = CategoryActivity.class.getSimpleName();

    private CategoryContract.Presenter presenter;

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(getString(R.string.title_category));
        }

        listView = findViewById(R.id.category_list);

        // do the setup
        CategoryScreen.configure(this);

        presenter.fetchCategoryListData();
    }

    @Override
    protected void onResume() {
        super.onResume();

        // load the data
        presenter.onResume();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        presenter.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();

        presenter.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        presenter.onDestroy();
    }

    @Override
    public void onDataUpdated(CategoryViewModel viewModel) {
        //Log.e(TAG, "onDataUpdated()");

        // deal with the data
        listView.setAdapter(new CategoryAdapter(this, viewModel.categories, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CategoryItem item = (CategoryItem) view.getTag();
                presenter.selectCategoryListData(item);
            }
        }));
    }


    @Override
    public void navigateToNextScreen() {
        Intent intent = new Intent(this, ProductListActivity.class);
        startActivity(intent);
    }

    @Override
    public void injectPresenter(CategoryContract.Presenter presenter) {
        this.presenter = presenter;
    }
}