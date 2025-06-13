package org.example;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class Exercicio11 {

    public static void main(String[] args) {
        try {
            URL url = new URL("https://apichallenges.eviltester.com/sim/entities");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("OPTIONS");

            int statusCode = connection.getResponseCode();
            System.out.println("Código de Status HTTP (OPTIONS): " + statusCode);

            String allowHeader = connection.getHeaderField("Allow");
            System.out.println("Métodos permitidos (Allow): " + allowHeader);

            connection.disconnect();

        } catch (IOException e) {
            System.out.println("Erro na requisição: " + e.getMessage());
        }
    }
}

