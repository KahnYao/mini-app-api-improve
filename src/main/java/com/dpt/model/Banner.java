package com.dpt.model;

public class Banner {
    private Integer id;
    private String action;
    private String actionId;
    private String description;
    private String thumb;
    private String small;
    private String url;
    private String image;
    private String isH5;
    private String isOwnerCar;

    public void setId(Integer id) {
        this.id = id;
    }

    public void setAction(String action) {
        this.action = action == null ? null : action.trim();
    }

    public void setActionId(String actionId) {
        this.actionId = actionId == null ? null : actionId.trim();
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public void setThumb(String thumb) {
        this.thumb = thumb == null ? null : thumb.trim();
    }

    public void setSmall(String small) {
        this.small = small == null ? null : small.trim();
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public void setImage(String image) {
        this.image = image == null ? null : image.trim();
    }

    public void setIsH5(String isH5) {
        this.isH5 = isH5 == null ? null : isH5.trim();
    }

    public void setIsOwnerCar(String isOwnerCar) {
        this.isOwnerCar = isOwnerCar == null ? null : isOwnerCar.trim();
    }

    public Integer getId() {
        return id;
    }

    public String getAction() {
        return action;
    }

    public String getActionId() {
        return actionId;
    }

    public String getDescription() {
        return description;
    }

    public String getThumb() {
        return thumb;
    }

    public String getSmall() {
        return small;
    }

    public String getUrl() {
        return url;
    }

    public String getImage() {
        return image;
    }

    public String getIsH5() {
        return isH5;
    }

    public String getIsOwnerCar() {
        return isOwnerCar;
    }
}
