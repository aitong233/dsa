package com.qpyy.room.bean;

import java.util.List;

/**
 * 项目名称 qipao-android
 * 包名：com.qpyy.room.bean
 * 创建人 黄强
 * 创建时间 2020/8/11 10:50
 * 描述 describe
 */
public class ProductsModel {

    /**
     * id :
     * category_id :
     * title :
     * add_time :
     * picture :
     * category_name :
     * prices : [{"id":"","product_id":"","day":"","price":""}]
     */

    private String id;
    private String category_id;
    private String title;
    private String add_time;
    private String picture;
    private String category_name;
    private List<PricesBean> prices;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAdd_time() {
        return add_time;
    }

    public void setAdd_time(String add_time) {
        this.add_time = add_time;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public List<PricesBean> getPrices() {
        return prices;
    }

    public void setPrices(List<PricesBean> prices) {
        this.prices = prices;
    }

    public static class PricesBean {
        /**
         * id :
         * product_id :
         * day :
         * price :
         */

        private String id;
        private String product_id;
        private String day;
        private String price;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getProduct_id() {
            return product_id;
        }

        public void setProduct_id(String product_id) {
            this.product_id = product_id;
        }

        public String getDay() {
            return day;
        }

        public void setDay(String day) {
            this.day = day;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }
    }
}
