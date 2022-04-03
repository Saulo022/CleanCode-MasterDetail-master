package es.ulpgc.eite.cleancode.catalog.categoryCatalog;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import es.ulpgc.eite.cleancode.catalog.app.CategoryItem;
import es.ulpgc.eite.cleancode.catalog.app.ProductItem;

public class CategoryModel implements CategoryContract.Model {

    public static String TAG = CategoryModel.class.getSimpleName();

    private String data;

    private final List<CategoryItem> itemList = new ArrayList<>();
    private final int COUNT = 20;

    public CategoryModel() {
        for (int index =1; index <= COUNT; index++ ){
            addProduct(createProduct(index));
        }
    }

    @Override
    public String getStoredData() {
        // Log.e(TAG, "getStoredData()");
        return data;
    }

    @Override
    public void onRestartScreen(String data) {
        // Log.e(TAG, "onRestartScreen()");
    }

    @Override
    public void onDataFromNextScreen(String data) {
        // Log.e(TAG, "onDataFromNextScreen()");
    }

    @Override
    public void onDataFromPreviousScreen(String data) {
        // Log.e(TAG, "onDataFromPreviousScreen()");
    }

    @Override
    public List<CategoryItem> fetchCategoryListData(){
        Log.e(TAG, "fetchCategoryListData");
        return itemList;
    }
    private void addProduct(CategoryItem item){
        itemList.add(item);
    }

    private CategoryItem createProduct(int position){
        String content = "Category " + position;

        return new CategoryItem(position, content);
    }
}