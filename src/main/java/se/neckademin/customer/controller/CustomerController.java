package se.neckademin.customer.controller;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import se.neckademin.customer.Model.Customer;
import se.neckademin.customer.Model.Order;
import se.neckademin.customer.Model.OrderList;
import se.neckademin.customer.Model.Purchases;
import se.neckademin.customer.exception.NotSavedCustomerException;
import se.neckademin.customer.exception.UrlException;
import se.neckademin.customer.service.CustomerService;
import se.neckademin.customer.service.ItemClient;
import se.neckademin.customer.service.OrderClient;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    private CustomerService customerService;
    private OrderClient orderClient;
    private ItemClient itemClient;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
        this.orderClient = new OrderClient();
        this.itemClient = new ItemClient();
    }

    @GetMapping("/all")
    public List<Customer> getAllCustomers() {
        return customerService.findAllCustomer();
    }

    @RequestMapping("/{id}")
    public Customer getCustomerById(@PathVariable("id") Long id) {
        return customerService.findCustomerById(id);
    }

    @PostMapping("/add")
    public Boolean addCustomer(@RequestBody Customer newCustomer) throws NotSavedCustomerException {
        return customerService.saveCustomer(newCustomer);
    }

    @RequestMapping("/{id}/orders")
    public OrderList getAllOrderByCustomerId(@PathVariable("id") Long id) throws UrlException {
        List<Purchases> allPurchase = orderClient.getAllPurchase(id);

        return (Objects.nonNull(allPurchase)) ?
                convertToOrderList(allPurchase)
                : null;
    }

    private OrderList convertToOrderList(List<Purchases> allPurchase) {
        return OrderList.builder()
                .customer(allPurchase.stream()
                        .findFirst()
                        .map(purchase -> customerService.findCustomerById(purchase.getCustomerId())).orElse(null)
                )
                .orderList(allPurchase.stream().map(this::map).collect(Collectors.toList()))
                .build();
    }

    @PostMapping("/addOrder")
    public Response addOrder(@RequestBody Purchases purchases) throws UrlException {
        Response response = new Response();
        try {
            orderClient.addOrder(purchases);
            response.setStatus(200);
            return response;
        } catch (Exception e) {
            response.setStatus(500);
            return response;
        }
    }

    private Order map(Purchases purchases) {
        return (Objects.nonNull(purchases)) ?
                Order.builder()
                        .id(purchases.getId())
                        .date(purchases.getDate())
                        .itemList(
                                (Objects.nonNull(purchases.getItems())) ?
                                        purchases.getItems().stream()
                                                .filter(Objects::nonNull)
                                                .map(item -> {
                                                    try {
                                                        return itemClient.getItemById(item);
                                                    } catch (UrlException e) {
                                                        throw new RuntimeException(e);
                                                    }
                                                })
                                                .collect(Collectors.toList())
                                        : null)
                        .build()
                : null;
    }
}