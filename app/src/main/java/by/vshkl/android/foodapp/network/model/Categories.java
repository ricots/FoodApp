package by.vshkl.android.foodapp.network.model;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(name = "categories")
public class Categories {

    @ElementList(inline = true) private List<Category> categories;

    public Categories() {
    }

    public Categories(List<Category> categories) {
        this.categories = categories;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Categories)) {
            return false;
        }

        Categories that = (Categories) o;

        return getCategories() != null ? getCategories().equals(that.getCategories()) : that.getCategories() == null;
    }

    @Override
    public int hashCode() {
        return getCategories() != null ? getCategories().hashCode() : 0;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Categories{");
        sb.append("categories=").append(categories);
        sb.append('}');
        return sb.toString();
    }
}
