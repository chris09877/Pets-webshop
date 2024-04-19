package chris.ilg.dierenwinkel.controller;

import chris.ilg.dierenwinkel.model.Orders;
import chris.ilg.dierenwinkel.service.OrderServiceImpl;
import chris.ilg.dierenwinkel.service.OrdersDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderServiceImpl orderService;
    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

@PostMapping(value = "/create", consumes = "application/json")
public ResponseEntity<?> add(@RequestBody OrdersDto ordersDto, HttpServletRequest request) {
    HttpSession session = request.getSession(false); // Get existing session if it exists
    if (session == null) {
        logger.info("No session found, user is not authenticated.");
        return new ResponseEntity<>("User is not authenticated", HttpStatus.UNAUTHORIZED);
    }

    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication == null || /*authentication instanceof AnonymousAuthenticationToken ||*/ !authentication.isAuthenticated()) {
        logger.info("User is not properly authenticated, session ID: {}", session.getId());
        return new ResponseEntity<>("User is not authenticated", HttpStatus.UNAUTHORIZED);
    }

    logger.info("Session ID: {}", session.getId());
    logger.info("Received new order dto A ZBEEEEEE: {}", ordersDto);
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

//    @GetMapping("/find")
//    public ResponseEntity<Orders> getOrderByUserId(@RequestParam Integer userId, HttpServletRequest request) {
//        logger.info("Getting the order with user id: " + userId);
//        ArrayList<Orders> orderArray = orderService.getAllOrdersByUserId(userId);
//        if (orderArray != null ){
//            orderArray.forEach(order -> {
//                if (order.getUserInfo() === request.getSession()){
//                    return ResponseEntity.ok(order);
//                }
//                else {
//                    logger.info("No order found with the matching user info");
//                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
//                }
//            });
//        }
//        else {
//            logger.info("No order found with the matching id");
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
//        }
//    }
@GetMapping("/find")
public ResponseEntity<List<Orders>> getOrderByUserId(@RequestParam Integer userId, HttpServletRequest request) {
    logger.info("Getting the orders for user id: {}", userId);
    List<Orders> orderList = orderService.getAllOrdersByUserId(userId);

    if (orderList == null || orderList.isEmpty()) {
        logger.info("No orders found for user id {}", userId);
        return ResponseEntity.notFound().build();
    }

    // Assuming UserInfo is something you can retrieve from the session (this needs to be correctly implemented)
    String currentUserInfo = (String) request.getSession().getAttribute("UserInfo");  // Example: how you might get user info from session

    // Filter orders that match the session user info
    List<Orders> filteredOrders = orderList.stream()
            .filter(order -> order.getUserInfo().equals(currentUserInfo))
            .collect(Collectors.toList());

    if (filteredOrders.isEmpty()) {
        logger.info("No order found with the matching user info for user id {}", userId);
        return ResponseEntity.notFound().build();
    }

    return ResponseEntity.ok(filteredOrders);
}


    @GetMapping("/exist")
    public ResponseEntity<Orders> getOrderByUserInfo(@RequestParam String sessionId) {
        logger.info("Getting the order with user info: " + sessionId);
        Orders order = orderService.getOrderByUserInfo(sessionId);
        if (order != null ){
            return ResponseEntity.ok(order);
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("/{orderId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<String> deleteOrder(@PathVariable int orderId) {
        orderService.deleteOrder(orderId);
        return ResponseEntity.ok("Order with ID " + orderId + " deleted successfully.");

    }

}