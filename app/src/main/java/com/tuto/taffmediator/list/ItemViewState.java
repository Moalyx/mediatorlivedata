package com.tuto.taffmediator.list;

import java.util.Objects;

public class ItemViewState {

    private String unitPrice;
    private String name;
    private String quantity;
    private String total;

    public ItemViewState (String unitPrice, String name, String quantity, String total){
        this.unitPrice = unitPrice;
        this.name = name;
        this.quantity = quantity;
        this.total = total;
    }

    public String getUnitPrice() {
        return unitPrice;
    }

    public String getName() {
        return name;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getTotal() {
        return total;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemViewState that = (ItemViewState) o;
        return unitPrice == that.unitPrice && quantity == that.quantity && total == that.total && name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(unitPrice, name, quantity, total);
    }
}
