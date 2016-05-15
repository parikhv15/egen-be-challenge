package com.egen;

import com.mongodb.MongoClient;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import java.net.UnknownHostException;

/**
 * Created by parikhv on 5/14/16.
 */
public class MorphiaConfig {

    private String packageName = "com.egen.model";

    private static MorphiaConfig morphiaConfig = new MorphiaConfig();

    private Datastore datastore = null;

    private MorphiaConfig() {
        Morphia morphia = new Morphia();
        try {
            datastore = morphia.createDatastore(new MongoClient(), "morphia_client");
            datastore.ensureIndexes();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    public Datastore getDatastore() {
        return datastore;
    }

    public void setDatastore(Datastore datastore) {
        this.datastore = datastore;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public static MorphiaConfig getInstance() {
        return morphiaConfig;
    }
}
