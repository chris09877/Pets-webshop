package chris.ilg.dierenwinkel.controller;

import chris.ilg.dierenwinkel.model.Orders;
import chris.ilg.dierenwinkel.service.OrderServiceImpl;
import chris.ilg.dierenwinkel.service.OrdersDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderServiceImpl orderService;
    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

//    @PostMapping("/create")
//    public String add(@RequestBody OrdersDto ordersDto){
//        logger.info("Received new order A ZBEEEEEE: {}", ordersDto);
//        Orders order = new Orders(ordersDto);
//        orderService.saveOrder(ordersDto);
//        return "New order is added";
//    }
@PostMapping("/create")
public ResponseEntity<?> add(@RequestBody OrdersDto ordersDto, HttpServletRequest request) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication == null || !authentication.isAuthenticated()) {
        return new ResponseEntity<>("User is not authenticated", HttpStatus.UNAUTHORIZED);
    }

    logger.info("Received new order dto  A ZBEEEEEE: {}", ordersDto);
    //Orders order = new Orders(ordersDto);
    orderService.saveOrder(ordersDto);
    return ResponseEntity.ok("New order is added");
}


    @GetMapping("/{id}")
    public ResponseEntity<Orders> get (@PathVariable int id)
    {
        logger.info("FIND ORDER WITH ID:", id);

        Orders order  = orderService.getOrderById(id);
        if (order != null) {
            return ResponseEntity.ok().body(order); // Return 200 OK response with the user entity
        } else {
            return ResponseEntity.notFound().build(); // Return 404 Not Found response if user not found
        }
    }

    @GetMapping("")
    public ResponseEntity<ArrayList<Orders>> getAll() {
        logger.info("Getting all orders");

        ArrayList<Orders> orders = orderService.getAllOrders();
        if (!orders.isEmpty()) {
            return ResponseEntity.ok(orders); // Return 200 OK response with the list of products
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null); // Return 500 Internal Server Error if products not found
        }

    }

    @PatchMapping("/update")
    public Orders updateOrder(@RequestParam Integer userId, @RequestBody OrdersDto updatedOrderDto) {
        logger.info("Updating the order with user ID: " + userId);
        return orderService.updateOrder(userId, updatedOrderDto);
    }

    @GetMapping("/find")
    public ResponseEntity<Orders> getOrderByUserId(@RequestParam Integer userId) {
        logger.info("Getting the order with user id: " + userId);
        Orders order = orderService.getOrderByUserId(userId);
        if (order != null ){
            return ResponseEntity.ok(order);
        }
        else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/exist")
    public ResponseEntity<Orders> getOrderByUserInfo(@RequestParam String sessionId) {
        logger.info("Getting the order with session id: " + sessionId);
        Orders order = orderService.getOrderByUserInfo(sessionId);
        if (order != null ){
            return ResponseEntity.ok(order);
        }
        else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @DeleteMapping("/{orderId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<String> deleteOrder(@PathVariable int orderId) {
        orderService.deleteOrder(orderId);
        return ResponseEntity.ok("Order with ID " + orderId + " deleted successfully.");

    }

}