package by.vshkl.android.foodapp.database;

import android.support.v4.util.Pair;

import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.structure.database.DatabaseWrapper;
import com.raizlabs.android.dbflow.structure.database.transaction.ITransaction;
import com.raizlabs.android.dbflow.structure.database.transaction.Transaction;

import java.util.Collection;
import java.util.List;

import by.vshkl.android.foodapp.database.entity.CategoryEntity;
import by.vshkl.android.foodapp.database.entity.OfferEntity;
import by.vshkl.android.foodapp.database.entity.ParamEntity;
import by.vshkl.android.foodapp.database.mapper.CategoryMapper;
import by.vshkl.android.foodapp.database.mapper.OfferMapper;
import by.vshkl.android.foodapp.mvp.mapper.CategoryEntityMapper;
import by.vshkl.android.foodapp.mvp.model.Category;
import by.vshkl.android.foodapp.network.model.Catalog;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

public class DatabaseRepository {

    public static Observable<Boolean> checkIfCatalogDownloaded() {
        return Observable.create(new ObservableOnSubscribe<Boolean>() {
            @Override
            public void subscribe(ObservableEmitter<Boolean> emitter) throws Exception {
                long categories = SQLite.selectCountOf().from(CategoryEntity.class).count();
                long offers = SQLite.selectCountOf().from(OfferEntity.class).count();
                long params = SQLite.selectCountOf().from(ParamEntity.class).count();
                emitter.onNext(categories > 0 && offers > 0 && params > 0);
            }
        });
    }

    public static Observable<Boolean> saveCatalog(final Catalog catalog) {
        return Observable.create(new ObservableOnSubscribe<Boolean>() {
            @Override
            public void subscribe(final ObservableEmitter<Boolean> emitter) throws Exception {
                FlowManager.getDatabase(AppDatabase.class).beginTransactionAsync(new ITransaction() {
                    @Override
                    public void execute(DatabaseWrapper databaseWrapper) {
                        for (CategoryEntity categoryEntity
                                : CategoryMapper.transform(catalog.getShop().getCategories().getCategories())) {
                            categoryEntity.save(databaseWrapper);
                        }

                        for (Pair<OfferEntity, Collection<ParamEntity>> pair
                                : OfferMapper.transform(catalog.getShop().getOffers().getOffers())) {
                            pair.first.save(databaseWrapper);
                            for (ParamEntity paramEntity : pair.second) {
                                paramEntity.save(databaseWrapper);
                            }
                        }
                    }
                }).success(new Transaction.Success() {
                    @Override
                    public void onSuccess(Transaction transaction) {
                        emitter.onNext(true);
                    }
                }).error(new Transaction.Error() {
                    @Override
                    public void onError(Transaction transaction, Throwable error) {
                        emitter.onNext(false);
                    }
                }).build().execute();
            }
        });
    }

    public static Observable<Collection<Category>> loadCategories() {
        return Observable.create(new ObservableOnSubscribe<Collection<Category>>() {
            @Override
            public void subscribe(ObservableEmitter<Collection<Category>> emitter) throws Exception {
                List<CategoryEntity> categoryEntities = SQLite.select().from(CategoryEntity.class).queryList();
                emitter.onNext(CategoryEntityMapper.transform(categoryEntities));
            }
        });
    }
}
