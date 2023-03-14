package com.tuto.taffmediator.main;

import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.tuto.taffmediator.data.Item;
import com.tuto.taffmediator.data.TestRepository;

public class MainViewModel extends ViewModel {

    private final TestRepository testRepository;

    private final MediatorLiveData<MainViewState> mediatorLiveData = new MediatorLiveData<>();

    private final MutableLiveData<String> nameMutableLiveData = new MutableLiveData<>();
    private final MutableLiveData<Integer> quantityMutableLiveData = new MutableLiveData<>(0);
    private final MutableLiveData<Integer> priceMutableLiveData = new MutableLiveData<>(0);

    public MainViewModel(TestRepository testRepository) {
        this.testRepository = testRepository;

        mediatorLiveData.addSource(priceMutableLiveData, new Observer<Integer>() {
            @Override
            public void onChanged(Integer price) {
                MainViewModel.this.combine(
                        price,
                        nameMutableLiveData.getValue(),
                        quantityMutableLiveData.getValue()
                );
            }
        });
        mediatorLiveData.addSource(nameMutableLiveData, name -> combine(
                priceMutableLiveData.getValue(),
                name,
                quantityMutableLiveData.getValue()
        ));
        mediatorLiveData.addSource(quantityMutableLiveData, quantity -> combine(
                priceMutableLiveData.getValue(),
                nameMutableLiveData.getValue(),
                quantity
        ));
    }

    private void combine(Integer price, String name, Integer quantity) {
        String sentence = "Vous avez acheté la quantité de " + quantity + " " + name
                + " au prix unitaire de " + price
                + " pour un prix total de " + quantity * price;

        boolean isMinusButtonEnabled = quantity > 0;

        mediatorLiveData.setValue(new MainViewState(sentence, isMinusButtonEnabled));
    }

    private void addItemToList(int price, String name, int quantity, int total) {
        testRepository.addItemMutableLiveDateToList(
                new Item(
                        price,
                        name,
                        quantity,
                        total));
    }

    public LiveData<MainViewState> getMessageLiveData() {
        return mediatorLiveData;
    }

    public void onPriceChanged(String price) {
        Integer parsedPrice = null;

        try {
            parsedPrice = Integer.parseInt(price);
        } catch (NumberFormatException exception) {
            exception.printStackTrace();
        }

        if (parsedPrice != null) {
            priceMutableLiveData.setValue(parsedPrice);
        }
    }

    public void onNameChanged(String name) {
        nameMutableLiveData.setValue(name);
    }

    public void onIncreaseButtonClick() {
        Integer previousValue = quantityMutableLiveData.getValue();

        if (previousValue != null) {
            quantityMutableLiveData.setValue(previousValue + 1);
        } else {
            quantityMutableLiveData.setValue(1);
        }
    }

    public void onDecreaseButtonClick() {
        Integer previousValue = quantityMutableLiveData.getValue();

        if (previousValue != null) {
            quantityMutableLiveData.setValue(previousValue - 1);
        } else {
            quantityMutableLiveData.setValue(0);
        }
    }

    public void onAddButtonClicked() {
        Integer quantity = quantityMutableLiveData.getValue();
        Integer price = priceMutableLiveData.getValue();

        if (quantity != null && price != null) {
            addItemToList(
                    price,
                    nameMutableLiveData.getValue(),
                    quantity,
                    quantity * price
            );
        }
    }
}
