package com.tuto.taffmediator.data;

import androidx.lifecycle.LiveData;

public class Item {

//    private TestRepository testRepository;

//    private LiveData<Integer> unitPrice = testRepository.getUnitPriceLiveData();
//    private LiveData<String> name = testRepository.getNameLiveData();
//    private LiveData<Integer> quantity= testRepository.getQuantityLiveData();
//    private LiveData<Integer> total = testRepository.getTotalPriceLiveData();

    private int unitPrice;
    private String name;
    private int quantity;
    private int total;

//    public Item (LiveData<Integer> unitPrice,LiveData<String> name, LiveData<Integer> quantity, LiveData<Integer> total ){
//        this.unitPrice = unitPrice;
//        this.name = name;
//        this.quantity = quantity;
//        this.total = total;
//    }

    public Item (int unitPrice, String name, int quantity, int total){
        this.unitPrice = unitPrice;
        this.name = name;
        this.quantity = quantity;
        this.total = total;
    }

    public int getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(int unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
