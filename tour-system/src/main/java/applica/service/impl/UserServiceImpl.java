package applica.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import applica.entity.*;
import applica.entity.enums.OrderStatus;
import applica.entity.enums.UserRole;
import applica.repo.CoffeeRepository;
import applica.repo.OrderRepository;
import applica.repo.UserRepository;
import applica.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    CoffeeRepository coffeeRepository;


    @Override
    public String signUp(User user) {
        user.setUserRole(UserRole.CUSTOMER);
        userRepository.save(user);
        return "Signed Up";
    }

    @Override
    public void update(User user) {
        userRepository.save(user);
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User get(Long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public void addTour(Tour tour) {
        coffeeRepository.save(tour);
    }

    @Override
    public List<Tour> getAllCoffee() {
        return coffeeRepository.findAll();
    }

    @Override
    public Tour getCoffeeById(Long id) {
        return coffeeRepository.findById(id).get();
    }





    @Override
    public void createOrder(Order order) {
        orderRepository.save(order);
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public void updateOrder(Order order) {
        Order old = orderRepository.findById(order.getId()).get();
        old.setOrderStatus(order.getOrderStatus());
        orderRepository.save(old);
    }

    @Override
    public Order getOrderById(Long Id) {
        return orderRepository.findById(Id).get();
    }

    @Override
    public String createOperator(User user) {
        user.setUserRole(UserRole.OPERATOR);
        userRepository.save(user);
        return "Signed Up a Cashier";
    }

    @Override
    public void makeOrder(OrderDto dto, Long id) {
        Order order = new Order();
        order.setTour(
                coffeeRepository.findAll().stream()
                        .filter(tour -> tour.getName().equals(dto.getTour()))
                        .findFirst().get()
        );
        order.setOrderStatus(OrderStatus.OPENED);

//                .coffee(
//                        coffeeRepository.findAll().stream()
//                                .filter(coffee -> coffee.getName().equals(dto.getCoffee()))
//                                .findFirst().get()
//                )
//                .topping(
//                        toppingRepository.findAll().stream()
//                                .filter(topping -> topping.getName().equals(dto.getTopping()))
//                                .findFirst().get()
//                )
//                .user(userRepository.findById(id).get())
//                .orderStatus(OrderStatus.OPENED)
//                .build();
        order.setUser(userRepository.findById(id).get());
        order.setPrice(order.getTour().getPrice());
        orderRepository.save(order);
    }

    @Override
    public String menu() {

        return new StringBuilder()
                .append("Tours: \n")
                .append(coffeeRepository.findAll().stream()
                        .map(Tour::toString)
                        .collect(Collectors.joining()))
                .toString();
    }


}
