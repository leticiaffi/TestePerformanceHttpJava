package org.example;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Exercicio8 {

    public static void main(String[] args) {
        try {
            URL urlPut = new URL("https://apichallenges.eviltester.com/sim/entities/10");
            HttpURLConnection connectionPut = (HttpURLConnection) urlPut.openConnection();
            connectionPut.setRequestMethod("PUT");
            connectionPut.setRequestProperty("Content-Type", "application/json");
            connectionPut.setDoOutput(true);

            String jsonInput = "{\"name\": \"atualizado\"}";

            DataOutputStream outputStream = new DataOutputStream(connectionPut.getOutputStream());
            outputStream.writeBytes(jsonInput);
            outputStream.flush();
            outputStream.close();

            int statusPut = connectionPut.getResponseCode();
            System.out.println("Código de Status HTTP (PUT): " + statusPut);

            BufferedReader readerPut = new BufferedReader(new InputStreamReader(connectionPut.getInputStream()));
            StringBuilder responsePut = new StringBuilder();
            String linePut;

            while ((linePut = readerPut.readLine()) != null) {
                responsePut.append(linePut).append("\n");
            }
            readerPut.close();

            System.out.println("Resposta da API (PUT):");
            System.out.println(responsePut.toString());

            connectionPut.disconnect();

        } catch (IOException e) {
            System.out.println("Erro na requisição: " + e.getMessage());
        }
    }
}

