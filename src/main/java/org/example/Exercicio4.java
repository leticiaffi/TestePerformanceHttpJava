package org.example;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class Exercicio4 {

    public static void main(String[] args) {
        try {
            String baseUrl = "https://apichallenges.eviltester.com/sim/entities";
            String parametros = "?categoria=teste&limite=5";
            String urlFinal = baseUrl + parametros;

            URL url = new URL(urlFinal);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int statusCode = connection.getResponseCode();

            System.out.println("URL Final Montada: " + urlFinal);
            System.out.println("Código de Status HTTP: " + statusCode);

            connection.disconnect();

        } catch (IOException e) {
            System.out.println("Erro na requisição: " + e.getMessage());
        }
    }
}

