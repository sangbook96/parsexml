package vn.sangdv.simplexml.model;

public class Optional {
    private String key;
    private String value;

    public Optional() {
    }

    public Optional(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Optional{" +
                "key='" + key + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
