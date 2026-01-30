package org.example;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class ConnectionMongo {
    private static final String URL_MONGO = "mongodb://localhost:27017";
    private static final String DB_NAME = "Cine";

    public static MongoDatabase getConnectionMongo() {
        try {
            MongoClient client = MongoClients.create(URL_MONGO);
            return client.getDatabase(DB_NAME);
        } catch (Exception e) {
            throw new RuntimeException("Error conectando a MongoDB", e);
        }
    }
}