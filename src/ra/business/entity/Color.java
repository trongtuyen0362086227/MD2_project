package ra.business.entity;

import java.io.Serializable;

public class Color implements Serializable {
    private int colorId;
    private String colorName;
    private boolean colorStatus;

    public Color(int colorId, String colorName, boolean colorStatus) {
        this.colorId = colorId;
        this.colorName = colorName;
        this.colorStatus = colorStatus;
    }

    public Color() {

    }

    public int getColorId() {
        return colorId;
    }

    public void setColorId(int colorId) {
        this.colorId = colorId;
    }

    public String getColorName() {
        return colorName;
    }

    public void setColorName(String colorName) {
        this.colorName = colorName;
    }

    public boolean isColorStatus() {
        return colorStatus;
    }

    public void setColorStatus(boolean colorStatus) {
        this.colorStatus = colorStatus;
    }
}
