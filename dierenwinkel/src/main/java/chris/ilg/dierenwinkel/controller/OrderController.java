package chris.ilg.dierenwinkel.controller;

import chris.ilg.dierenwinkel.model.Order;
import chris.ilg.dierenwinkel.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("/add")
    public String add(@RequestBody Order order){
        orderService.saveOrder(order);
        return "New student is added";
    }
}
