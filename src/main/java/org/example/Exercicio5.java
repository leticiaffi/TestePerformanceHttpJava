package org.example;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Exercicio5 {

    public static void main(String[] args) {
        try {
            URL url = new URL("https://apichallenges.eviltester.com/sim/entities");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            String jsonInput = "{\"name\": \"aluno\"}";

            DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());
            outputStream.writeBytes(jsonInput);
            outputStream.flush();
            outputStream.close();

            int statusCode = connection.getResponseCode();
            System.out.println("Código de Status HTTP: " + statusCode);

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder responseBody = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                responseBody.append(line).append("\n");
            }
            reader.close();

            System.out.println("Resposta da API:");
            System.out.println(responseBody.toString());

            connection.disconnect();

        } catch (IOException e) {
            System.out.println("Erro na requisição: " + e.getMessage());
        }
    }
}

