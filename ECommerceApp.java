import java.util.*;
import java.util.stream.Collectors;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ECommerceApp {

    public static void main(String[] args) {
        ECommerceApp app = new ECommerceApp();
        app.run();
    }

    private List<Product> products = new ArrayList<>();
    private ShoppingCart shoppingCart = new ShoppingCart();
    private UserAccount userAccount;
    private List<Order> orderHistory = new ArrayList<>();

    public ECommerceApp() {
        // Initialize some sample products
        products.add(new Product(1, "Laptop", 50000.00));
        products.add(new Product(2, "Mobile Phone", 14000.00));
        products.add(new Product(3, "Headphones", 399.99));
        products.add(new Product(4, "T-Shirt", 249.99));
        products.add(new Product(5, "Trousers", 350.00));
        products.add(new Product(6, "Fan", 2300.00));
        products.add(new Product(7, "Bag", 129.99));
        products.add(new Product(8, "Smartwatch", 799.99));
        products.add(new Product(9, "Speaker", 800.00));
        products.add(new Product(10, "Mouse", 674.99));

        // Initialize a sample user account
        userAccount = new UserAccount("John", "Doe", "a", "p");
    }

    public void run() {
        System.out.println("***************");
        System.out.println("E-Commerce App");
        System.out.println("***************\n");

        System.out.println("Welcome to E-Commerce App");
        Scanner scanner = new Scanner(System.in);
        boolean isLoggedIn = false;

        while (true) {
            if (!isLoggedIn) {
                isLoggedIn = login(scanner);
                if (!isLoggedIn) {
                    System.exit(0);
                }
            }

            System.out.println("\nMain Menu:\n");
            System.out.println("1. View available products");
            System.out.println("2. Add product to cart");
            System.out.println("3. View cart");
            System.out.println("4. Remove item from cart");
            System.out.println("5. Update quantity in cart");
            System.out.println("6. Search product by name");
            System.out.println("7. View order history");
            System.out.println("8. Checkout");
            System.out.println("9. Logout");
            System.out.println("10. Exit\n");
            System.out.print("Please enter your choice[1-10]: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("\n1. View available products\n");
                    displayProducts();
                    break;
                case 2:
                    System.out.println("\n2. Add product to cart\n");
                    addProductToCart(scanner);
                    break;
                case 3:
                    System.out.println("\n3. View cart\n");
                    viewCart();
                    break;
                case 4:
                    System.out.println("\n4. Remove item from cart\n");
                    removeItemFromCart(scanner);
                    break;
                case 5:
                    System.out.println("\n5. Update quantity in cart\n");
                    updateQuantityInCart(scanner);
                    break;
                case 6:
                    System.out.println("\n6. Search product by name\n");
                    searchProductByName(scanner);
                    break;
                case 7:
                    System.out.println("\n7. View order history\n");
                    viewOrderHistory();
                    break;
                case 8:
                    System.out.println("\n8. Checkout\n");
                    checkout();
                    break;
                case 9:
                    isLoggedIn = false;
                    System.out.println("\nLogged out successfully!\n");
                    break;
                case 10:
                    System.exit(0);
                default:
                    System.out.println("Invalid choice");
            }
        }
    }

    private boolean login(Scanner scanner) {
        System.out.println("\nLogin Form\n");
        System.out.print("Please enter your email:");
        String email = scanner.next();
        System.out.print("Please enter your password:");
        String password = scanner.next();

        if (userAccount.authenticate(email, password)) {
            System.out.println("\nLogin successful!");
            return true;
        } else {
            System.out.println("Invalid email or password.");
            return false;
        }
    }

    private void displayProducts() {
        System.out.println("Available Products:");
        for (Product product : products) {
            System.out.println(product);
        }
    }

    private void addProductToCart(Scanner scanner) {
        System.out.print("Enter product ID to add to cart:");
        int productId = scanner.nextInt();

        Product productToAdd = products.stream()
                .filter(product -> product.getId() == productId)
                .findFirst()
                .orElse(null);

        if (productToAdd != null) {
            System.out.print("Enter quantity:");
            int quantity = scanner.nextInt();

            if (quantity > 0) {
                shoppingCart.addProduct(productToAdd, quantity);
                System.out.println("\nProduct added to cart.");
            } else {
                System.out.println("\nQuantity should be greater than 0.");
            }
        } else {
            System.out.println("\nInvalid product ID.");
        }
    }

    private void viewCart() {
        System.out.println("Shopping Cart:");
        for (CartItem item : shoppingCart.getItems()) {
            System.out.println(item);
        }
        System.out.println("Total: Rs. " + shoppingCart.calculateTotal());
    }

    private void removeItemFromCart(Scanner scanner) {
        System.out.print("Enter product ID to remove from cart:");
        int productId = scanner.nextInt();

        if (shoppingCart.removeItem(productId)) {
            System.out.println("\nProduct removed from cart.");
        } else {
            System.out.println("\nProduct not found in cart.");
        }
    }

    private void updateQuantityInCart(Scanner scanner) {
        System.out.print("Enter product ID to update quantity:");
        int productId = scanner.nextInt();

        CartItem cartItem = shoppingCart.getItems().stream()
                .filter(item -> item.getProduct().getId() == productId)
                .findFirst()
                .orElse(null);

        if (cartItem != null) {
            System.out.print("Enter new quantity:");
            int newQuantity = scanner.nextInt();

            if (newQuantity > 0) {
                cartItem.setQuantity(newQuantity);
                System.out.println("\nQuantity updated successfully.");
            } else {
                System.out.println("\nQuantity should be greater than 0.");
            }
        } else {
            System.out.println("\nProduct not found in cart.");
        }
    }

    private void searchProductByName(Scanner scanner) {
        System.out.print("Enter product name to search:");
        String productName = scanner.next();

        List<Product> foundProducts = products.stream()
                .filter(product -> product.getName().toLowerCase().contains(productName.toLowerCase()))
                .collect(Collectors.toList());

        if (!foundProducts.isEmpty()) {
            System.out.println("\nFound Products:");
            foundProducts.forEach(System.out::println);
        } else {
            System.out.println("\nNo products found with the given name.");
        }
    }

    private void viewOrderHistory() {
        if (!orderHistory.isEmpty()) {
            System.out.println("Order History:");
            for (Order order : orderHistory) {
                System.out.println(order);
            }
        } else {
            System.out.println("No order history available.");
        }
    }

    private static class Order {
        private int orderId;
        private String date;
        private double totalAmount;

        public Order(int orderId, String date, double totalAmount) {
            this.orderId = orderId;
            this.date = date;
            this.totalAmount = totalAmount;
        }

        @Override
        public String toString() {
            return "\nOrder ID: " + orderId +
                    "\nDate: " + date +
                    "\nTotal Amount: Rs. " + totalAmount;
        }
    }

    private void checkout() {
        double total = shoppingCart.calculateTotal();
        System.out.println("Total amount to pay: Rs. " + total);

        // Get current date and time
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = now.format(formatter);

        // Create new Order object and add it to order history
        Order newOrder = new Order(orderHistory.size() + 1, formattedDateTime, total);
        orderHistory.add(newOrder);

        System.out.println("\nThank you for shopping with us!");
        shoppingCart.clear();
    }

    private static class Product {
        private int id;
        private String name;
        private double price;

        public Product(int id, String name, double price) {
            this.id = id;
            this.name = name;
            this.price = price;
        }

        public int getId() {
            return id;
        }

        public String getName() { // Added getName method
            return name;
        }

        @Override
        public String toString() {
            return id + ". " + name + " - Rs. " + price;
        }
    }

    private static class UserAccount {
        private String firstName;
        private String lastName;
        private String email;
        private String password;

        public UserAccount(String firstName, String lastName, String email, String password) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.email = email;
            this.password = password;
        }

        public boolean authenticate(String email, String password) {
            return this.email.equals(email) && this.password.equals(password);
        }
    }

    private static class ShoppingCart {
        private List<CartItem> items = new ArrayList<>();

        public void addProduct(Product product, int quantity) {
            items.add(new CartItem(product, quantity));
        }

        public boolean removeItem(int productId) {
            for (Iterator<CartItem> iterator = items.iterator(); iterator.hasNext();) {
                CartItem item = iterator.next();
                if (item.getProduct().getId() == productId) {
                    iterator.remove();
                    return true;
                }
            }
            return false;
        }

        public List<CartItem> getItems() {
            return items;
        }

        public double calculateTotal() {
            return items.stream()
                    .mapToDouble(item -> item.getProduct().price * item.getQuantity())
                    .sum();
        }

        public void clear() {
            items.clear();
        }
    }

    private static class CartItem {
        private Product product;
        private int quantity;

        public CartItem(Product product, int quantity) {
            this.product = product;
            this.quantity = quantity;
        }

        public Product getProduct() {
            return product;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) { // Added setQuantity method
            this.quantity = quantity;
        }

        @Override
        public String toString() {
            return product.name + " x " + quantity + " - Rs. " + (product.price * quantity);
        }
    }
}
