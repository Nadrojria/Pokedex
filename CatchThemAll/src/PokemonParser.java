import org.json.JSONArray;
import org.json.JSONObject;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class PokemonParser {
    public static void main(String[] args) throws IOException {
        String jsonContent = loadJsonFile("pokedex.json");  // Charger le fichier JSON
        List<Pokemon> pokemons = parsePokemons(jsonContent); // Parser le contenu JSON

        System.out.println("Nombre total de Pokemon chargés: " + pokemons.size());
    }

    // Charge le contenu du fichier JSON
    private static String loadJsonFile(String filename) throws IOException {
        return new String(Files.readAllBytes(Paths.get(filename)));
    }

    // Parse le JSON et crée une liste d'objets Pokemon
    private static List<Pokemon> parsePokemons(String jsonContent) {
        List<Pokemon> pokemons = new ArrayList<>();

        // Le JSON contient un objet avec une propriété "pokemon" qui est un array
        JSONObject root = new JSONObject(jsonContent);
        JSONArray pokemonArray = root.getJSONArray("pokemon");

        // Créer un objet Pokemon pour chaque élément du array
        for (int i = 0; i < pokemonArray.length(); i++) {
            JSONObject pokemonJson = pokemonArray.getJSONObject(i);
            Pokemon pokemon = new Pokemon(pokemonJson);
            pokemons.add(pokemon);
        }

        return pokemons;
    }
}