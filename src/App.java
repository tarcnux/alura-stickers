import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {

        // Fazer uma conexão http e buscar os top 250 filmes
        String url = "https://alura-filmes.herokuapp.com/conteudos";
        /**
         * (https://mocki.io/v1/9a7c1ca9-29b4-4eb3-8306-1adb9d159060) criada pelo @rezendecas
         * (https://alura-imdb-api.herokuapp.com/movies) criada pelo Jhon Santana
         * (https://api.mocki.io/v2/549a5d8b) criada pelo instrutor Alexandre Aquiles
         * (https://alura-filmes.herokuapp.com/conteudos) criada pela instrutora Jacqueline Oliveira
         * (https://raw.githubusercontent.com/alexfelipe/imersao-java/json/top250.json) criada pelo instrutor Alex Felipe
         */
        URI endereco = URI.create(url);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(endereco).GET().build();
        HttpResponse<String> response =  client.send(request, BodyHandlers.ofString());
        String body = response.body();

        //extrair só os dados que interessam
        JsonParser parser = new JsonParser();
        List<Map<String, String>> listaDeFilmes = parser.parse(body);

        //exibir os dados
        // System.out.println("Tamanho da Lista de Filmes: " + listaDeFilmes.size());
        // System.out.println("Filme: \n" +listaDeFilmes.get(0));
        for (Map<String,String> filme : listaDeFilmes) {
            System.out.println("\u001b[37m \u001b[44m Título:\u001b[m " + filme.get("title"));
            System.out.println("\u001b[37m \u001b[44m URL:\u001b[m " + filme.get("image"));
            System.out.print("\u001b[37m \u001b[44m Avaliação:\u001b[m ");
            Double hearts;
            hearts = (double) Math.round(Double.parseDouble(filme.get("imDbRating")));
            System.out.println(hearts.intValue() + " " +
                new String(new char[hearts.intValue()])
                .replace("\0", "💙️")
            );
            System.out.println("________________________________________\n");
        }
    }
}
