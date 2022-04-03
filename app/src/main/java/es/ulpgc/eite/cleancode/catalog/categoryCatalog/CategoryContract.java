package es.ulpgc.eite.cleancode.catalog.categoryCatalog;

import java.lang.ref.WeakReference;
import java.util.List;

import es.ulpgc.eite.cleancode.catalog.app.CategoryItem;

public interface CategoryContract {

    interface View {
        void injectPresenter(Presenter presenter);

        void onDataUpdated(CategoryViewModel viewModel);

        void navigateToNextScreen();
    }

    interface Presenter {
        void fetchCategoryListData();

        void injectView(WeakReference<View> view);

        void injectModel(Model model);

        void onResume();

        void onStart();

        void onRestart();

        void onBackPressed();

        void onPause();

        void onDestroy();

        void selectCategoryListData(CategoryItem category);
    }

    interface Model {
        String getStoredData();

        void onDataFromNextScreen(String data);

        void onRestartScreen(String data);

        void onDataFromPreviousScreen(String data);

        List<CategoryItem> fetchCategoryListData();
    }

}