package ar.com.diamand.android.roomexample;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

@Database(entities = { Persona.class }, version = 1)
public abstract class MiDatabase extends RoomDatabase {

    private static MiDatabase instance;

    public abstract PersonaDao personaDao();

    public static synchronized MiDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    MiDatabase.class, "personas_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(new RoomDatabase.Callback() {
                        @Override
                        public void onCreate(@NonNull SupportSQLiteDatabase db) {
                            super.onCreate(db);
                            new InicializacionDBAsyncTask(instance).execute();
                        }
                    }).build();
        }
        return instance;
    }

    private static class InicializacionDBAsyncTask
            extends AsyncTask<Void, Void, Void> {

        private PersonaDao personaDao;

        public InicializacionDBAsyncTask(MiDatabase md) {
            personaDao = md.personaDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            personaDao.insertar(
                    new Persona("2345678", "Luciano", "Diamand"));
            personaDao.insertar(
                    new Persona("3467734", "Pedro", "Paez"));
            personaDao.insertar(
                    new Persona("8754332", "Miguel", "Cesar"));
            return null;
        }
    }
}
