package by.vshkl.android.foodapp.database;

import android.support.v4.util.Pair;

import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.structure.database.DatabaseWrapper;
import com.raizlabs.android.dbflow.structure.database.transaction.ITransaction;
import com.raizlabs.android.dbflow.structure.database.transaction.Transaction;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import by.vshkl.android.foodapp.database.entity.CategoryEntity;
import by.vshkl.android.foodapp.database.entity.OfferEntity;
import by.vshkl.android.foodapp.database.entity.OfferEntity_Table;
import by.vshkl.android.foodapp.database.entity.ParamEntity;
import by.vshkl.android.foodapp.database.entity.ParamEntity_Table;
import by.vshkl.android.foodapp.database.mapper.CategoryMapper;
import by.vshkl.android.foodapp.database.mapper.OfferMapper;
import by.vshkl.android.foodapp.mvp.mapper.CategoryEntityMapper;
import by.vshkl.android.foodapp.mvp.mapper.OfferEntityMapper;
import by.vshkl.android.foodapp.mvp.model.Category;
import by.vshkl.android.foodapp.mvp.model.Offer;
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

    public static Observable<Collection<Offer>> loadOffers(final int categoryId) {
        return Observable.create(new ObservableOnSubscribe<Collection<Offer>>() {
            @Override
            public void subscribe(ObservableEmitter<Collection<Offer>> emitter) throws Exception {
                List<OfferEntity> offerEntities = SQLite.select().from(OfferEntity.class)
                        .where(OfferEntity_Table.categoryId_id.eq(categoryId)).queryList();

                List<Offer> offers = new ArrayList<>(offerEntities.size());

                for (OfferEntity offerEntity : offerEntities) {
                    List<ParamEntity> paramEntities = SQLite.select().from(ParamEntity.class)
                            .where(ParamEntity_Table.offerId_id.eq(offerEntity.getId())).queryList();
                    offers.add(OfferEntityMapper.transform(offerEntity, paramEntities));
                }

                emitter.onNext(offers);
            }
        });
    }

    public static Observable<Offer> loadOffer(final int offerId) {
        return Observable.create(new ObservableOnSubscribe<Offer>() {
            @Override
            public void subscribe(ObservableEmitter<Offer> emitter) throws Exception {
                OfferEntity offerEntity = SQLite.select().from(OfferEntity.class)
                        .where(OfferEntity_Table.id.eq(offerId)).querySingle();

                if (offerEntity != null) {
                    List<ParamEntity> paramEntities = SQLite.select().from(ParamEntity.class)
                            .where(ParamEntity_Table.offerId_id.eq(offerEntity.getId())).queryList();
                    emitter.onNext(OfferEntityMapper.transform(offerEntity, paramEntities));
                }
            }
        });
    }
}
