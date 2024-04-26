package chris.ilg.dierenwinkel.controller;

import chris.ilg.dierenwinkel.model.OrderProduct;
import chris.ilg.dierenwinkel.model.Orders;
import chris.ilg.dierenwinkel.model.Product;
import chris.ilg.dierenwinkel.repository.OrderRepo;
import chris.ilg.dierenwinkel.service.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.jaxb.SpringDataJaxb;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.web.servlet.function.ServerResponse.status;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderServiceImpl orderService;
    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private OrderProductServiceImpl orderProductServiceImpl;

    @Autowired
    private ProductServiceImpl productService;

    @Autowired
    private OrderRepo orderRepo;
    @PostMapping("/create")
    public ResponseEntity<?> add(@RequestBody OrdersDto ordersDto, HttpServletRequest request) {
        HttpSession session = request.getSession(true); // Get existing session if it exists
        if (session == null) {
            //logger.info("No session found, user is not authenticated. Session id:" + session.getId());
            return new ResponseEntity<>("User is not authenticated", HttpStatus.UNAUTHORIZED);
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            logger.info("User is not properly authenticated, session ID is null");
            return new ResponseEntity<>("User is not authenticated", HttpStatus.UNAUTHORIZED);
        }

        logger.info("Session ID: {}", request.getSession().getId());
        logger.info("Received new order dto A ZBEEEEEE: {}", ordersDto);
        orderService.saveOrder(ordersDto);
        return ResponseEntity.ok("New order is added");
    }


    @GetMapping("/{id}")
    public ResponseEntity<Orders> get(@PathVariable int id, HttpServletRequest request) {
        String sessionId = request.getSession().getId();
        Orders orderWithSession = orderService.getOrderByUserInfo(sessionId);
        if (orderWithSession == null){
            logger.info("Order with user info: " + sessionId + " NOT FOUND");
            return ResponseEntity.notFound().build(); // Return 404 Not Found response if user not found
        }

        if (orderWithSession.getId() == id){
            logger.info("FOUND ORDER WITH ID:", id);
            return ResponseEntity.ok().body(orderWithSession); // Return 200 OK response with the user entity
        } else {
            logger.info("NO USER FOUND WITH MATCHING USERINFO: " + sessionId +  " AND ORDER WITH ID:", id);
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
    public ResponseEntity<?> updateOrder(HttpServletRequest request, @RequestParam String userInfo, @RequestBody OrderProductDto orderProductDto) {
        logger.info("Finding the order with session ID: " + userInfo);
        Orders updateOrder = orderService.getOrderByUserInfo(userInfo);
        if (updateOrder == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("No order found with this session id in the user info field:" + userInfo);
        }
        Product product = productService.getProductById(orderProductDto.getProductId());
        OrderProduct op = orderProductServiceImpl.create(orderProductDto,product, updateOrder);
        if (op == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong during the creation of the order product:");
        }
        List<OrderProductDto> listOPD = orderProductServiceImpl.getAllByOrderId(updateOrder.getId());
        return ResponseEntity.ok().body(listOPD);
    }

    @PatchMapping("/finalize")
    public ResponseEntity<?> finalizeOrder(HttpServletRequest request,@RequestBody OrdersDto orderDto) {
        logger.info("Finding the order with session ID: " + request.getSession().getId());
        Orders updateOrder = orderService.getOrderByUserInfo(request.getSession().getId());
        if (updateOrder == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("No order found with this session id in the user info field:" +request.getSession().getId());
        }
        updateOrder.setContent("Name: "+ orderDto.getContent()+ "\nPhone:"+ orderDto.getId()+ "\nAddress:" + orderDto.getUserInfo());
        updateOrder.setDate(orderDto.getDate());
        orderRepo.save(updateOrder);
        return ResponseEntity.ok().body("ORDER FINALIZED");
    }


    @GetMapping("/find")
    public ResponseEntity<List<OrderProductDto>> getOrderByUserId(@RequestParam Integer userId, HttpServletRequest request) {
        logger.info("Getting the orders for user id: {}", userId);
        List<Orders> orderList = orderService.getAllOrdersByUserId(userId);

        if (orderList == null || orderList.isEmpty()) {
            logger.info("No orders found for user id {}", userId);
            return ResponseEntity.notFound().build();
        }

        // Assuming UserInfo is something you can retrieve from the session (this needs to be correctly implemented)
        String currentUserInfo = request.getSession().getId();  // Example: how you might get user info from session

        // Filter orders that match the session user info
        List<Orders> filteredOrders = orderList.stream()
                .filter(order -> order.getUserInfo().equals(currentUserInfo))
                .collect(Collectors.toList());

        if (filteredOrders.isEmpty()) {
            logger.info("No order found with the matching user info ", currentUserInfo);
            return ResponseEntity.notFound().build();
        } else if (filteredOrders.size() > 1) {
            logger.info("Problem more than one order has be found");
        }
        Orders filteredOrder = filteredOrders.get(0);
        List<OrderProductDto> listOPD = orderProductServiceImpl.getAllByOrderId(filteredOrder.getId());
        logger.info("the list if products size:" + listOPD.size() + " The to string: " + listOPD.toString());
        return ResponseEntity.ok(listOPD);
    }


    @GetMapping("/exist")
    public ResponseEntity<?> getOrderByUserInfo(@RequestParam String sessionId) {
        logger.info("Getting the order with user info: " + sessionId);
        Orders order = orderService.getOrderByUserInfo(sessionId);
        if (order != null) {
            return ResponseEntity.ok().body(Collections.singletonMap("orderId", order.getId()));
        } else {
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