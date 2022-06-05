package product;

import java.util.HashMap;
import java.util.Map;

public class ProductService {
    private static Map<String, Product> products = new HashMap<>();
    public static Map<String, Product> getAll(){
        products.put("kefir", new Product("kefir", 100, "diary" ));
        products.put("milk", new Product("milk", 90, "diary" ));
        products.put("banan", new Product("banan", 120, "fruits" ));
        products.put("apple", new Product("apple", 100, "fruits" ));
        return products;
    }
}
