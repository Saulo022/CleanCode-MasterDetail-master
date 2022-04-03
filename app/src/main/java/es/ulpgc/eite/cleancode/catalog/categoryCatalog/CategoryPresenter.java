package es.ulpgc.eite.cleancode.catalog.categoryCatalog;

import java.lang.ref.WeakReference;

public class CategoryPresenter implements CategoryContract.Presenter {

    public static String TAG = CategoryPresenter.class.getSimpleName();

    private WeakReference<CategoryContract.View> view;
    private CategoryState state;
    private CategoryContract.Model model;
    private AppMediator mediator;

    public CategoryPresenter(AppMediator mediator) {
        this.mediator = mediator;
        state = mediator.getCategoryState();
    }

    @Override
    public void onStart() {
        // Log.e(TAG, "onStart()");

        // initialize the state
        state = new CategoryState();

        // call the model and update the state
        state.data = model.getStoredData();

        // use passed state if is necessary
        PreviousToCategoryState savedState = getStateFromPreviousScreen();
        if (savedState != null) {

            // update the model if is necessary
            model.onDataFromPreviousScreen(savedState.data);

            // update the state if is necessary
            state.data = savedState.data;
        }
    }

    @Override
    public void onRestart() {
        // Log.e(TAG, "onRestart()");

        // update the model if is necessary
        model.onRestartScreen(state.data);
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
        }

        // call the model and update the state
        //state.data = model.getStoredData();

        // update the view
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

    @Override
    public void injectView(WeakReference<CategoryContract.View> view) {
        this.view = view;
    }

    @Override
    public void injectModel(CategoryContract.Model model) {
        this.model = model;
    }

}