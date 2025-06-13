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
        List<Pokemon> heavyPokemons = findByWeight(pokemons, 10);
        System.out.println("Total of loading Pokemons: " + pokemons.size());
        System.out.println("Total of heavy Pokemons: " + heavyPokemons.size());
    }

    // Charge le contenu du fichier JSON
    private static String loadJsonFile(String filename) throws IOException {
        return new String(Files.readAllBytes(Paths.get(filename)));
    }

    // Parse le JSON et crée une liste d'objets Pokemon
    private static List<Pokemon> parsePokemons(String jsonContent) {
        List<Pokemon> pokemons = new ArrayList<>(); // On crée une liste vide qui va contenir tous nos objets Pokemon

        // Le JSON contient un objet avec une propriété "pokemon" qui est un array
        JSONObject root = new JSONObject(jsonContent); // Parse (analyse) le texte JSON et le transforme en structure d'objets Java
        JSONArray pokemonArray = root.getJSONArray("pokemon"); // Récupère la valeur de la propriété "pokemon"

        // Créer un objet Pokemon pour chaque élément du array
        for (int i = 0; i < pokemonArray.length(); i++) {
            JSONObject pokemonJson = pokemonArray.getJSONObject(i);
            Pokemon pokemon = new Pokemon(pokemonJson);
            pokemons.add(pokemon);
            System.out.println(pokemon.getNum() + " - " + pokemon.getName());
        }

        return pokemons;
    }

    private static List<Pokemon> findByWeight(List<Pokemon> pokemons, int weight) {
        List<Pokemon> heavyPokemons = new ArrayList<>();

        for (Pokemon pokemon : pokemons) { // Pour chaque pokemon de la liste pokemons
            if (pokemon.getWeightInKg(pokemon.getWeight()) > weight) {
                heavyPokemons.add(pokemon);
            }
        }
        return heavyPokemons;
    }
}