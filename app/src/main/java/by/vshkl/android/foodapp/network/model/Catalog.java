package by.vshkl.android.foodapp.network.model;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "yml_catalog")
public class Catalog {

    @Element(name = "shop") private Shop shop;
    @Attribute(name = "date") private String date;

    public Catalog() {
    }

    public Catalog(Shop shop, String date) {
        this.shop = shop;
        this.date = date;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Catalog)) {
            return false;
        }

        Catalog catalog = (Catalog) o;

        return getShop() != null ? getShop().equals(catalog.getShop()) : catalog.getShop() == null
                && (getDate() != null ? getDate().equals(catalog.getDate()) : catalog.getDate() == null);
    }

    @Override
    public int hashCode() {
        int result = getShop() != null ? getShop().hashCode() : 0;
        result = 31 * result + (getDate() != null ? getDate().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Catalog{");
        sb.append("shop=").append(shop);
        sb.append(", date='").append(date).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
