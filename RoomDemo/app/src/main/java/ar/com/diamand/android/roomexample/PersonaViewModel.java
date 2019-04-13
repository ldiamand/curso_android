package ar.com.diamand.android.roomexample;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

public class PersonaViewModel extends AndroidViewModel {

    private PersonaRepository repo;

    private LiveData<List<Persona>> personas;

    public PersonaViewModel(@NonNull Application application) {
        super(application);
        repo = new PersonaRepository(application);
    }
}
