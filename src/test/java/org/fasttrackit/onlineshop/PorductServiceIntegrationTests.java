package org.fasttrackit.onlineshop;

import org.fasttrackit.onlineshop.domain.Product;
import org.fasttrackit.onlineshop.service.ProductService;
import org.fasttrackit.onlineshop.transfer.SaveProductRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.TransactionSystemException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;


@RunWith(SpringRunner.class)
@SpringBootTest
public class PorductServiceIntegrationTests {
    private ProductService productService;

    @Test
    public void testCreatepProduct_whenValidRestest_thenProductIsSaved() {
        SaveProductRequest request = new SaveProductRequest();
        request.setName("Banana" + System.currentTimeMillis());
        request.setQuantity(100);
        request.setDescription("Healthy food");
        Product createdProduct = productService.createProduct(request);
        assertThat(createdProduct, notNullValue());
        assertThat(createdProduct.getId(), notNullValue());
        assertThat(createdProduct.getId(), greaterThan(0L));
        assertThat(createdProduct.getName(), is(request.getName()));
        assertThat(createdProduct.getPrice(), is(request.getPrice()));
        assertThat(createdProduct.getDescription(), is(request.getDescription()));

    }

    @Test(expected = TransactionSystemException.class)
    void testCreatePorduct_whenInvalidRequest_thenThrowException() {
        SaveProductRequest request = new SaveProductRequest();

        // leaving request properties with default null values
        // to validate the negative flow
        productService.createProduct(request);

    }
}
