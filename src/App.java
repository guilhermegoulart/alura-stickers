import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {
        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
        URI addressUri = URI.create(url);

        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(addressUri).GET().build();

        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();

        var parser = new JsonParser();
        List<Map<String, String>> ListaDeFilmes = parser.parse(body);

        var generator = new StickerGenerator();

        for (Map<String, String> filme : ListaDeFilmes) {

            String urlImage = filme.get("image");
            String title = filme.get("title");

            InputStream original = new URL(urlImage).openStream();
            var fileName = title + ".png";

            generator.create(original, fileName);

            System.out.println(title);
            System.out.println();
        }
    }
}