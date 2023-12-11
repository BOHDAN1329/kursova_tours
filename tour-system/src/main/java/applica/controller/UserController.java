package applica.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import applica.entity.*;
import applica.entity.enums.UserRole;
import applica.service.impl.UserServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    @Autowired
    UserServiceImpl service;

    @PostMapping("/sign-up")
    public void singUp(@RequestBody User user){
        service.signUp(user);
    }

    @PostMapping("/{id}/makeOrder")
    public void makeOrder(@RequestBody OrderDto order, @PathVariable Long id){
        service.makeOrder(order, id);
    }

    @GetMapping("/test")
    public User test(){
        return service.getAll().stream().filter(e -> e.getId() == 1).findFirst().get();
    }

    @PostMapping("/admin/{id}/operator")
    public String createOperator(@RequestBody User cashier, @PathVariable Long id){
        if (service.get(id).getUserRole().equals(UserRole.ADMIN)){
            service.createOperator(cashier);
            return "Done Successfully";
        }
        else {
            return "Only Admin can add new Cashier";
        }
    }

    @GetMapping("/admin/{id}/all")
    public List<User> getAllUsers(@PathVariable Long id){
        return service.get(id).getUserRole().equals(UserRole.ADMIN) ? service.getAll() : List.of();
    }

    @PostMapping("/admin/{id}/newTour")
    public String createTour(@RequestBody Tour tour, @PathVariable Long id){
        if (service.get(id).getUserRole().equals(UserRole.ADMIN)){
            service.addTour(tour);
            return "Done Successfully";
        }
        else {
            return "Only Admin can add new Tours";
        }
    }



    @GetMapping("/listTours")
    public String menu(){
        return service.menu();
    }

    @GetMapping("/orders")
    public List<Order> getAllOrders(){
        return service.getAllOrders();
    }

    @PatchMapping("/operator/{id}/updateStatus")
    public String updateOrderStatus(@PathVariable Long id,@RequestBody Order order){
        if (service.get(id).getUserRole().equals(UserRole.OPERATOR)){
            service.updateOrder(order);
            return "Done Successfully";
        }
        else {
            return "Only Admin can add new Tour";
        }
    }

    @GetMapping("/{id}/orders")
    public List<Order> checkUserOrder(@PathVariable Long id){
        return service.get(id).getOrders();
    }

}
