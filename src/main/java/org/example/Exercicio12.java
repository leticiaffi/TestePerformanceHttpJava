package org.example;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class Exercicio12 {

    public static void main(String[] args) {
        String isbn = gerarIsbn();
        if (isbn == null) return;

        criarItem(isbn);
        listarItens();
        atualizarItem(isbn);
        deletarItem(isbn);
    }

    public static String gerarIsbn() {
        try {
            URL url = new URL("https://apichallenges.eviltester.com/simpleapi/randomisbn");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String isbn = reader.readLine().replace("\"", "");
            reader.close();

            System.out.println("ISBN Gerado: " + isbn);
            connection.disconnect();
            return isbn;

        } catch (IOException e) {
            System.out.println("Erro ao gerar ISBN: " + e.getMessage());
            return null;
        }
    }

    public static void criarItem(String isbn) {
        try {
            URL url = new URL("https://apichallenges.eviltester.com/simpleapi/items");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            String json = "{\"type\": \"book\",\"isbn13\": \"" + isbn + "\",\"price\": 5.99,\"numberinstock\": 5}";
            DataOutputStream out = new DataOutputStream(connection.getOutputStream());
            out.writeBytes(json);
            out.flush();
            out.close();

            int status = connection.getResponseCode();
            System.out.println("Código de Status HTTP (POST): " + status);

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String linha;
            while ((linha = reader.readLine()) != null) {
                System.out.println(linha);
            }
            reader.close();
            connection.disconnect();

        } catch (IOException e) {
            System.out.println("Erro ao criar item: " + e.getMessage());
        }
    }

    public static void listarItens() {
        try {
            URL url = new URL("https://apichallenges.eviltester.com/simpleapi/items");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int status = connection.getResponseCode();
            System.out.println("Código de Status HTTP (GET Itens): " + status);

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String linha;
            while ((linha = reader.readLine()) != null) {
                System.out.println(linha);
            }
            reader.close();
            connection.disconnect();

        } catch (IOException e) {
            System.out.println("Erro ao listar itens: " + e.getMessage());
        }
    }

    public static void atualizarItem(String isbn) {
        try {
            URL url = new URL("https://apichallenges.eviltester.com/simpleapi/items");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("PUT");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            String json = "{\"type\": \"book\",\"isbn13\": \"" + isbn + "\",\"price\": 7.49,\"numberinstock\": 10}";
            DataOutputStream out = new DataOutputStream(connection.getOutputStream());
            out.writeBytes(json);
            out.flush();
            out.close();

            int status = connection.getResponseCode();
            System.out.println("Código de Status HTTP (PUT): " + status);

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String linha;
            while ((linha = reader.readLine()) != null) {
                System.out.println(linha);
            }
            reader.close();
            connection.disconnect();

        } catch (IOException e) {
            System.out.println("Erro ao atualizar item: " + e.getMessage());
        }
    }

    public static void deletarItem(String isbn) {
        try {
            URL url = new URL("https://apichallenges.eviltester.com/simpleapi/items/" + isbn);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("DELETE");

            int status = connection.getResponseCode();
            System.out.println("Código de Status HTTP (DELETE): " + status);

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String linha;
            while ((linha = reader.readLine()) != null) {
                System.out.println(linha);
            }
            reader.close();
            connection.disconnect();

        } catch (IOException e) {
            System.out.println("Erro ao deletar item: " + e.getMessage());
        }
    }
}

