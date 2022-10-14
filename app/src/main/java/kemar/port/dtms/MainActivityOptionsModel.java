package kemar.port.dtms;

public class MainActivityOptionsModel {

    private String name;
    private int image;

    public MainActivityOptionsModel(String name, int image) {
        this.name = name;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public int getImage() {
        return image;
    }
}