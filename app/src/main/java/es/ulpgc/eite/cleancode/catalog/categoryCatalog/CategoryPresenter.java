package es.ulpgc.eite.cleancode.catalog.categoryCatalog;

import java.lang.ref.WeakReference;

import es.ulpgc.eite.cleancode.catalog.app.CatalogMediator;
import es.ulpgc.eite.cleancode.catalog.app.CategoryItem;

public class CategoryPresenter implements CategoryContract.Presenter {

    public static String TAG = CategoryPresenter.class.getSimpleName();

    private WeakReference<CategoryContract.View> view;
    private CategoryState state;
    private CategoryContract.Model model;
    private CatalogMediator mediator;

    public CategoryPresenter(CatalogMediator mediator) {
        this.mediator = mediator;
        state = mediator.getCategoryState();
    }

    @Override
    public void onStart() {
        // Log.e(TAG, "onStart()");

        // initialize the state
        if (state == null) {
            state = new CategoryState();
        }
    }

    @Override
    public void onRestart() {
        // Log.e(TAG, "onRestart()");

        // update the model if is necessary
        //model.onRestartScreen(state.);
    }

    @Override
    public void onResume() {
        // Log.e(TAG, "onResume()");

        // use passed state if is necessary
        //NextToCategoryState savedState = getStateFromNextScreen();
        //if (savedState != null) {

            // update the model if is necessary
            //model.onDataFromNextScreen(savedState.data);

            // update the state if is necessary
            //state.data = savedState.data;
        view.get().onDataUpdated(state);

        }



    @Override
    public void onBackPressed() {
        // Log.e(TAG, "onBackPressed()");
    }

    @Override
    public void onPause() {
        // Log.e(TAG, "onPause()");
    }

    @Override
    public void onDestroy() {
        // Log.e(TAG, "onDestroy()");
    }
    /*
    private NextToCategoryState getStateFromNextScreen() {
        return mediator.getNextCategoryScreenState();
    }

    private void passStateToNextScreen(CategoryToNextState state) {
        mediator.setNextCategoryScreenState(state);
    }

    private void passStateToPreviousScreen(CategoryToPreviousState state) {
        mediator.setPreviousCategoryScreenState(state);
    }

    private PreviousToCategoryState getStateFromPreviousScreen() {
        return mediator.getPreviousCategoryScreenState();
    }*/

    @Override
    public void fetchCategoryListData() {
        // Log.e(TAG, "fetchCategoryListData()");
        state.categories = model.fetchCategoryListData();
        // call the model
        view.get().onDataUpdated(state);
    }

    private void passDataToCategoryDetailScreen(CategoryItem categoryItem){
        mediator.setCategory(categoryItem);
    }

    @Override
    public void selectCategoryListData(CategoryItem category){
        passDataToCategoryDetailScreen(category);
        view.get().navigateToNextScreen();
    }

    @Override
    public void injectView(WeakReference<CategoryContract.View> view) {
        this.view = view;
    }

    @Override
    public void injectModel(CategoryContract.Model model) {
        this.model = model;
    }

}