package org.example;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Exercicio9 {

    public static void main(String[] args) {
        try {
            URL urlDelete = new URL("https://apichallenges.eviltester.com/sim/entities/9");
            HttpURLConnection connectionDelete = (HttpURLConnection) urlDelete.openConnection();
            connectionDelete.setRequestMethod("DELETE");

            int statusDelete = connectionDelete.getResponseCode();
            System.out.println("Código de Status HTTP (DELETE): " + statusDelete);
            connectionDelete.disconnect();

            URL urlGet = new URL("https://apichallenges.eviltester.com/sim/entities/9");
            HttpURLConnection connectionGet = (HttpURLConnection) urlGet.openConnection();
            connectionGet.setRequestMethod("GET");

            int statusGet = connectionGet.getResponseCode();
            System.out.println("Código de Status HTTP (GET após DELETE): " + statusGet);

            if (statusGet == 404) {
                System.out.println("Entidade 9 não encontrada. Exclusão confirmada.");
            } else {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connectionGet.getInputStream()));
                StringBuilder responseBody = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    responseBody.append(line).append("\n");
                }
                reader.close();
                System.out.println("Resposta da API:");
                System.out.println(responseBody.toString());
            }

            connectionGet.disconnect();

        } catch (IOException e) {
            System.out.println("Erro na requisição: " + e.getMessage());
        }
    }
}
