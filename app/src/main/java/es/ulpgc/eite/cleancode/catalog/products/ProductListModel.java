package es.ulpgc.eite.cleancode.catalog.products;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import es.ulpgc.eite.cleancode.catalog.app.ProductItem;

public class ProductListModel implements ProductListContract.Model {

  public static String TAG = ProductListModel.class.getSimpleName();

  private final List<ProductItem> itemList = new ArrayList<>();
  private final int COUNT = 20;
  private int category;

  public ProductListModel(int category) {
    // Add some sample items
    this.category = category;
    for (int index = 1; index <= COUNT; index++) {
      addProduct(createProduct(index, category));
    }
  }

  @Override
  public List<ProductItem> fetchProductListData() {
    Log.e(TAG, "fetchProductListData()");
    return itemList;
  }

  private void addProduct(ProductItem item) {
    itemList.add(item);
  }


  private ProductItem createProduct(int position, int category) {
    String content = "Product " + category + "." + position;

    return new ProductItem(
        position, content, fetchProductDetails(position, category)
    );

  }


  private String fetchProductDetails(int position, int category) {
    String content = "Details about Product:  " + category + "." + position;
    StringBuilder builder = new StringBuilder();
    builder.append(content);

    for (int index = 0; index < position; index++) {
      builder.append("\nMore details information here.");
    }

    return builder.toString();
  }

}
