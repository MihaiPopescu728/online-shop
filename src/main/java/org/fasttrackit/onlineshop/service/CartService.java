package org.fasttrackit.onlineshop.service;

import org.fasttrackit.onlineshop.domain.Cart;
import org.fasttrackit.onlineshop.domain.Customer;
import org.fasttrackit.onlineshop.persistance.CartRepository;
import org.fasttrackit.onlineshop.transfer.AddProductsToCartRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@Service
public class CartService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CartService.class);

    private final CartRepository cartRepository;
    private final CustomerService customerService;

    public CartService(CartRepository cartRepository, CustomerService customerService) {
        this.cartRepository = cartRepository;
        this.customerService = customerService;
    }

    @Transactional
    public void addProductToCart(AddProductsToCartRequest request) {
        Cart cart = cartRepository.findById(request.getCustomerId()).orElse(new Cart());
        if (cart.getCustomer() == null) {
            LOGGER.info("New cart will be create. Retrieving customerto map the relationship",
                    request.getCustomerId());
        }

        Customer customer = customerService.getCustomer(request.getCustomerId());
        cart.setCustomer(customer);
        cartRepository.save(cart);
    }

}
