package ra.business.entity;

import javax.xml.crypto.Data;
import java.io.Serializable;
import java.util.List;

public class Product implements Serializable {
    private String pruductId;
    private String productName;
    private float price;
    private float discount;
    private float exportPrice;
    private String title;
    private String content;
    private List<Color> colorList;
    private List<Size> sizeList;
    private List<Product> productList;
    private boolean productStatus;
    private String date;

    public Product() {
    }

    public Product(String pruductId, String productName, float price, float discount, float exportPrice, String title, String content, List<Color> colorList, List<Size> sizeList, List<Product> productList, boolean productStatus, String date) {
        this.pruductId = pruductId;
        this.productName = productName;
        this.price = price;
        this.discount = discount;
        this.exportPrice = exportPrice;
        this.title = title;
        this.content = content;
        this.colorList = colorList;
        this.sizeList = sizeList;
        this.productList = productList;
        this.productStatus = productStatus;
        this.date = date;
    }

    public String getPruductId() {
        return pruductId;
    }

    public void setPruductId(String pruductId) {
        this.pruductId = pruductId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }

    public float getExportPrice() {
        return exportPrice;
    }

    public void setExportPrice(float exportPrice) {
        this.exportPrice = exportPrice;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<Color> getColorList() {
        return colorList;
    }

    public void setColorList(List<Color> colorList) {
        this.colorList = colorList;
    }

    public List<Size> getSizeList() {
        return sizeList;
    }

    public void setSizeList(List<Size> sizeList) {
        this.sizeList = sizeList;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public boolean isProductStatus() {
        return productStatus;
    }

    public void setProductStatus(boolean productStatus) {
        this.productStatus = productStatus;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
