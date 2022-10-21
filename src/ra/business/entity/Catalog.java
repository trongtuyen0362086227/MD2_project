package ra.business.entity;

import ra.business.design.Icrud;

import java.io.Serializable;

public class Catalog implements Serializable {
    private int catalogId;
    private String catalogName;

    private String content;
    private boolean catalogStatus;
    private Catalog catalog;

    public Catalog() {
    }

    public Catalog(int catalogId, String catalogName, String content, boolean catalogStatus, Catalog catalog) {
        this.catalogId = catalogId;
        this.catalogName = catalogName;
        this.content = content;
        this.catalogStatus = catalogStatus;
        this.catalog = catalog;
    }

    public int getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(int catalogId) {
        this.catalogId = catalogId;
    }

    public String getCatalogName() {
        return catalogName;
    }

    public void setCatalogName(String catalogName) {
        this.catalogName = catalogName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isCatalogStatus() {
        return catalogStatus;
    }

    public void setCatalogStatus(boolean catalogStatus) {
        this.catalogStatus = catalogStatus;
    }

    public Catalog getCatalog() {
        return catalog;
    }

    public void setCatalog(Catalog catalog) {
        this.catalog = catalog;
    }
}
