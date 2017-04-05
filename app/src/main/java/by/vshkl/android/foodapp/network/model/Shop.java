package by.vshkl.android.foodapp.network.model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "shop")
public class Shop {

    @Element(name = "categories") private Categories categories;
    @Element(name = "offers") private Offers offers;

    public Shop() {
    }

    public Shop(Categories categories, Offers offers) {
        this.categories = categories;
        this.offers = offers;
    }

    public Categories getCategories() {
        return categories;
    }

    public void setCategories(Categories categories) {
        this.categories = categories;
    }

    public Offers getOffers() {
        return offers;
    }

    public void setOffers(Offers offers) {
        this.offers = offers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Shop)) return false;

        Shop shop = (Shop) o;

        return getCategories().equals(shop.getCategories()) && getOffers().equals(shop.getOffers());
    }

    @Override
    public int hashCode() {
        int result = getCategories().hashCode();
        result = 31 * result + getOffers().hashCode();
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Shop{");
        sb.append("categories=").append(categories);
        sb.append(", offers=").append(offers);
        sb.append('}');
        return sb.toString();
    }
}
