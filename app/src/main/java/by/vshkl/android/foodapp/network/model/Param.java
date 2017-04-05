package by.vshkl.android.foodapp.network.model;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.Text;

@Root(name = "param")
public class Param {

    @Attribute(name = "name") private String name;
    @Text private String text;

    public Param() {
    }

    public Param(String name, String text) {
        this.name = name;
        this.text = text;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Param)) {
            return false;
        }

        Param param = (Param) o;

        return getName().equals(param.getName()) && getText().equals(param.getText());
    }

    @Override
    public int hashCode() {
        int result = getName().hashCode();
        result = 31 * result + getText().hashCode();
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Param{");
        sb.append("name='").append(name).append('\'');
        sb.append(", text='").append(text).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
