import org.json.JSONObject;

public class Pokemon {
    private int id;
    private String num;
    private String name;
    private String weight;

    public Pokemon(JSONObject jsonPokemon) {
        this.id = jsonPokemon.getInt("id");
        this.num = jsonPokemon.getString("num");
        this.name = jsonPokemon.getString("name");
        this.weight = jsonPokemon.getString("weight");
    }

    public int getId() { return id; }
    public String getNum() { return num; }
    public String getName() { return name; }
    public String getWeight() { return weight; }

    public double getWeightInKg(String weight) {
        String weightValue = this.weight.replace(" kg", "").trim();
        return Double.parseDouble(weightValue);
    }
}
