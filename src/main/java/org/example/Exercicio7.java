package org.example;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Exercicio7 {

    public static void main(String[] args) {
        try {
            URL urlPost = new URL("https://apichallenges.eviltester.com/sim/entities/10");
            HttpURLConnection connectionPost = (HttpURLConnection) urlPost.openConnection();
            connectionPost.setRequestMethod("POST");
            connectionPost.setRequestProperty("Content-Type", "application/json");
            connectionPost.setDoOutput(true);

            String jsonInput = "{\"name\": \"atualizado\"}";

            DataOutputStream outputStream = new DataOutputStream(connectionPost.getOutputStream());
            outputStream.writeBytes(jsonInput);
            outputStream.flush();
            outputStream.close();

            int statusPost = connectionPost.getResponseCode();
            System.out.println("Código de Status HTTP (POST): " + statusPost);

            BufferedReader readerPost = new BufferedReader(new InputStreamReader(connectionPost.getInputStream()));
            StringBuilder responsePost = new StringBuilder();
            String linePost;

            while ((linePost = readerPost.readLine()) != null) {
                responsePost.append(linePost).append("\n");
            }
            readerPost.close();

            System.out.println("Resposta da API (POST):");
            System.out.println(responsePost.toString());

            connectionPost.disconnect();

            URL urlGet = new URL("https://apichallenges.eviltester.com/sim/entities/10");
            HttpURLConnection connectionGet = (HttpURLConnection) urlGet.openConnection();
            connectionGet.setRequestMethod("GET");

            int statusGet = connectionGet.getResponseCode();
            System.out.println("Código de Status HTTP (GET): " + statusGet);

            BufferedReader readerGet = new BufferedReader(new InputStreamReader(connectionGet.getInputStream()));
            StringBuilder responseGet = new StringBuilder();
            String lineGet;

            while ((lineGet = readerGet.readLine()) != null) {
                responseGet.append(lineGet).append("\n");
            }
            readerGet.close();

            System.out.println("Resposta da API (GET):");
            System.out.println(responseGet.toString());

            connectionGet.disconnect();

        } catch (IOException e) {
            System.out.println("Erro na requisição: " + e.getMessage());
        }
    }
}

