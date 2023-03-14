package com.tuto.taffmediator.list;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.tuto.taffmediator.data.Item;
import com.tuto.taffmediator.data.TestRepository;

import java.util.ArrayList;
import java.util.List;

public class ListViewModel extends ViewModel {

    private final TestRepository testRepository;


    public ListViewModel(TestRepository testRepository){
        this.testRepository = testRepository;
    }

    public LiveData<List<Item>> getListItemLiveData(){return testRepository.getItemMutableLiveDataList();}

    public void getItemList(Item item){

    }

        public LiveData<List<ItemViewState>> getViewStateItemLiveData(){
        return Transformations.map(testRepository.getItemMutableLiveDataList(), new Function<List<Item>, List<ItemViewState>>() {
            @Override
            public List<ItemViewState> apply(List<Item> items) {
                List<ItemViewState> itemsViewState = new ArrayList<>();
                for(Item item : items){
                        itemsViewState.add(
                                new ItemViewState(
                                        "" + item.getUnitPrice(),
                                        "" + item.getName(),
                                        "" + item.getQuantity(),
                                        "" + item.getTotal()
                                )
                        );
                    }

                return itemsViewState;
            }
        });
    }


}
