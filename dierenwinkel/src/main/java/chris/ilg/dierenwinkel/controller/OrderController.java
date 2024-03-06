package chris.ilg.dierenwinkel.controller;

import chris.ilg.dierenwinkel.model.Orders;
import chris.ilg.dierenwinkel.service.OrderService;
import chris.ilg.dierenwinkel.service.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderServiceImpl orderService;
    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

    @PostMapping("/add")
    public String add(@RequestBody Orders order){
        logger.info("Received new order A ZBEEEEEE: {}", order);
        orderService.saveOrder(order);
        return "New order is added";
    }
}