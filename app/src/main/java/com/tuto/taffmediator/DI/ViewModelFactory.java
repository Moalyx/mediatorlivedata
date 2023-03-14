package com.tuto.taffmediator.DI;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.tuto.taffmediator.data.TestRepository;
import com.tuto.taffmediator.list.ListViewModel;
import com.tuto.taffmediator.main.MainViewModel;
import com.tuto.taffmediator.data.TestRepository;


/**
 * L'Injection de dépendance se fait dans la ViewModelFactory. C'est elle qui se charge de créer tous les ViewModels, c'est donc le point
 * d'entrée de tous les ViewModels
 */
public class ViewModelFactory implements ViewModelProvider.Factory {

    private static ViewModelFactory sFactory;
    private final TestRepository testRepository;


    // Pattern singleton : seule la classe elle-même peut s'instancier
    private ViewModelFactory(@NonNull TestRepository testRepository) {
        this.testRepository = testRepository;

    }

    // Pattern singleton : récupération de l'Instance unique disponible partout dans l'app
    public static ViewModelFactory getInstance() {
        if (sFactory == null) {
            synchronized (ViewModelFactory.class) {
                if (sFactory == null) {
                    sFactory = new ViewModelFactory(
                            new TestRepository()
                    );
                }
            }
        }

        return sFactory;
    }

    @SuppressWarnings("unchecked")
    @NonNull
    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        if (modelClass.isAssignableFrom(MainViewModel.class)) {
            return (T) new MainViewModel(
                    testRepository
            );
        }
        // C'est ici qu'on va créer tous les différents VM : on utilise une seule ViewModelFactory pour toute l'application
        // Exemple pour un deuxième ViewModel :
         else if (modelClass.isAssignableFrom(ListViewModel.class)) {
             return (T) new ListViewModel(
                 testRepository
             );
         }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}