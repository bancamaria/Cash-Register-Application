package service;

import model.Product;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Set;
import java.util.TreeSet;

public final class ProductIOService {
    private static final String FILE_PATH = "src/data/Product.csv";
    private static final String FILE_HEADER = "id,categoryId,name,price,discount,quantity";
    private static ProductIOService instance;

    private ProductIOService() {
    }

    public static ProductIOService getInstance() {
        if (null == instance)
            instance = new ProductIOService();
        return instance;
    }

    public final Set<Product> loadProducts() {
        final Set<Product> products = new TreeSet<>();
        BufferedReader fileReader = null;
        try {
            String line;
            fileReader = new BufferedReader(new FileReader(FILE_PATH));
            fileReader.readLine();
            while (null != (line = fileReader.readLine())) {
                final String[] fields = line.split(",");
                if (0 < fields.length)
                    products.add(new Product(Integer.parseInt(fields[1].replace(' ', '')), fields[2].replace(' ', ''),
                            Float.parseFloat(fields[3].replace(' ', '')), Float.parseFloat(fields[4].replace(' ', '')),
                            Integer.parseInt(fields[5].replace(' ', ''))));
            }
        } catch (final IOException exception) {
            exception.printStackTrace();
        } finally {
            try {
                if (null != fileReader)
                    fileReader.close();
            } catch (final IOException e) {
                e.printStackTrace();
            }
        }
        LogService.getInstance().log("Loaded products", new Timestamp(System.currentTimeMillis()));
        return products;
    }

    public void saveProducts() {
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(FILE_PATH);
            fileWriter.write(FILE_HEADER + '\n');
            final Set<Product> products = ProductService.getInstance().getProducts();
            for (final Product product : products) {
                fileWriter.append(String.valueOf(product.getId())).append(",");
                fileWriter.append(String.valueOf(product.getCategoryId())).append(",");
                fileWriter.append(product.getName()).append(",");
                fileWriter.append(String.valueOf(product.getPrice())).append(",");
                fileWriter.append(String.valueOf(product.getDiscount())).append(",");
                fileWriter.append(String.valueOf(product.getQuantity())).append("\n");
            }
        } catch (final IOException exception) {
            exception.printStackTrace();
        } finally {
            try {
                if (null != fileWriter) {
                    fileWriter.flush();
                    fileWriter.close();
                }
            } catch (final IOException e) {
                e.printStackTrace();
            }
        }
        LogService.getInstance().log("Saved products", new Timestamp(System.currentTimeMillis()));
    }
}