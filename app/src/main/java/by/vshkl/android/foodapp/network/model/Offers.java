package by.vshkl.android.foodapp.network.model;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(name = "offers")
public class Offers {

    @ElementList(name = "offer", inline = true) private List<Offer> offers;

    public Offers() {
    }

    public Offers(List<Offer> offers) {
        this.offers = offers;
    }

    public List<Offer> getOffers() {
        return offers;
    }

    public void setOffers(List<Offer> offers) {
        this.offers = offers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Offers)) {
            return false;
        }

        Offers offers1 = (Offers) o;

        return getOffers() != null ? getOffers().equals(offers1.getOffers()) : offers1.getOffers() == null;
    }

    @Override
    public int hashCode() {
        return getOffers() != null ? getOffers().hashCode() : 0;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Offers{");
        sb.append("offers=").append(offers);
        sb.append('}');
        return sb.toString();
    }
}
