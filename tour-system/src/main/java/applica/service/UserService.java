package applica.service;

import applica.entity.*;

import java.util.List;

public interface UserService {
    //user
    String signUp(User user);

    void update(User user);

    List<User> getAll();

    User get(Long id);

    //coffee
    void addTour(Tour tour);

    List<Tour> getAllCoffee();

    Tour getCoffeeById(Long id);


    //order

    void createOrder(Order order);

    List<Order> getAllOrders();

    void updateOrder(Order order);

    Order getOrderById(Long Id);

    //admin
    String createOperator(User user);

    void makeOrder(OrderDto dto, Long id);

    String menu();
}
