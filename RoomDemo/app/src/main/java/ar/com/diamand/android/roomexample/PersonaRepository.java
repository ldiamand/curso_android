package ar.com.diamand.android.roomexample;

import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.os.AsyncTask;

import java.util.List;

public class PersonaRepository {

    private PersonaDao personaDao;

    private LiveData<List<Persona>> personas;

    public PersonaRepository(Context context) {
        personaDao = MiDatabase.getInstance(context).personaDao();
        personas = personaDao.obtenerTodas();
    }

    public void insertar(Persona p) {
        new InsertarPersonaAsyncTask(personaDao).execute(p);
    }

    private static class InsertarPersonaAsyncTask
            extends AsyncTask<Persona, Void, Void> {

        private PersonaDao dao;
        public InsertarPersonaAsyncTask(PersonaDao personaDao) {
            this.dao = personaDao;
        }

        @Override
        protected Void doInBackground(Persona... personas) {
            dao.insertar(personas[0]);
            return null;
        }
    }

}
