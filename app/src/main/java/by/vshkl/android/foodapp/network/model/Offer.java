package by.vshkl.android.foodapp.network.model;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(name = "offer")
public class Offer {

    @Element(name = "url") private String url;
    @Element(name = "name") private String name;
    @Element(name = "price") private String price;
    @Element(name = "description", required = false) private String description;
    @Element(name = "picture", required = false) private String pictureUrl;
    @Element(name = "categoryId") private String categoryId;    //TODO: change to int
    @ElementList(name = "param", inline = true, required = false) private List<Param> params;
    @Attribute(name = "id") private String id;  //TODO: change to int

    public Offer() {
    }

    public Offer(String url, String name, String price, String description, String pictureUrl, String categoryId,
                 List<Param> params, String id) {
        this.url = url;
        this.name = name;
        this.price = price;
        this.description = description;
        this.pictureUrl = pictureUrl;
        this.categoryId = categoryId;
        this.params = params;
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public List<Param> getParams() {
        return params;
    }

    public void setParams(List<Param> params) {
        this.params = params;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Offer)) {
            return false;
        }

        Offer offer = (Offer) o;

        return getId().equals(offer.getId());
    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Offer{");
        sb.append("url='").append(url).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", price='").append(price).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", pictureUrl='").append(pictureUrl).append('\'');
        sb.append(", categoryId='").append(categoryId).append('\'');
        sb.append(", params=").append(params);
        sb.append(", id='").append(id).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
