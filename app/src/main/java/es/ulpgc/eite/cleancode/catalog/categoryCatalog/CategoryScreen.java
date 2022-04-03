package es.ulpgc.eite.cleancode.catalog.categoryCatalog;

import androidx.fragment.app.FragmentActivity;

import java.lang.ref.WeakReference;

import es.ulpgc.eite.cleancode.catalog.R;

public class CategoryScreen {

    public static void configure(CategoryContract.View view) {

        WeakReference<FragmentActivity> context =
                new WeakReference<>((FragmentActivity) view);

        String data = context.get().getString(R.string.app_name);

        AppMediator mediator = AppMediator.getInstance();

        CategoryContract.Presenter presenter = new CategoryPresenter(mediator);
        CategoryContract.Model model = new CategoryModel(data);
        presenter.injectModel(model);
        presenter.injectView(new WeakReference<>(view));

        view.injectPresenter(presenter);

    }
}
