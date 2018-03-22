package top.zywork.generator.bean;

public class Select2 {

    private String id;
    private String text;
    private Boolean selected;

    public Select2() {
    }

    public Select2(String id, String text, Boolean selected) {
        this.id = id;
        this.text = text;
        this.selected = selected;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Boolean getSelected() {
        return selected;
    }

    public void setSelected(Boolean selected) {
        this.selected = selected;
    }
}
