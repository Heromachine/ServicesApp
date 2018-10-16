package com.example.jessi.servicesapp.subcategory;

public class SubCategoryModel {
    private String subCategoryId;
    private String categoryId;
    private String subCategoryName;
    private String subCategoryImage;
    private String subCategoryDescription;

    public SubCategoryModel() {
    }

    public SubCategoryModel(String subCategoryId, String categoryId, String subCategoryName, String subCategoryImage, String subCategoryDescription) {
        this.subCategoryId = subCategoryId;
        this.categoryId = categoryId;
        this.subCategoryName = subCategoryName;
        this.subCategoryImage = subCategoryImage;
        this.subCategoryDescription = subCategoryDescription;
    }

    public String getSubCategoryId() {
        return subCategoryId;
    }

    public void setSubCategoryId(String subCategoryId) {
        this.subCategoryId = subCategoryId;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getSubCategoryName() {
        return subCategoryName;
    }

    public void setSubCategoryName(String subCategoryName) {
        this.subCategoryName = subCategoryName;
    }

    public String getSubCategoryImage() {
        return subCategoryImage;
    }

    public void setSubCategoryImage(String subCategoryImage) {
        this.subCategoryImage = subCategoryImage;
    }

    public String getSubCategoryDescription() {
        return subCategoryDescription;
    }

    public void setSubCategoryDescription(String subCategoryDescription) {
        this.subCategoryDescription = subCategoryDescription;
    }
}
