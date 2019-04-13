package ar.com.diamand.android.roomexample;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface PersonaDao {

    @Insert
    void insertar(Persona p);

    @Update
    void actualizar(Persona p);

    @Delete
    void borrar(Persona p);

    @Query("SELECT * FROM PERSONAS")
    LiveData<List<Persona>> obtenerTodas();

}
