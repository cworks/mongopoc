package cworks.mongo;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Component {

    @Id
    private String id;

    private String name;

    @DBRef
    private ComponentValue value;

    public Component() {

    }

    public Component(String name, ComponentValue value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ComponentValue getValue() {
        return value;
    }

    public void setValue(ComponentValue value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Component{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
