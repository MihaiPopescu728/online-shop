package org.fasttrackit.onlineshop.transfer;

import javax.validation.constraints.NotNull;

public class AddProductsToCartRequest {

    @NotNull
    private Long productId;
    @NotNull
    private Long customerId;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    @Override
    public String toString() {
        return "AddProductsToCartRequest{" +
                "productId=" + productId +
                ", customerId=" + customerId +
                '}';
    }
}
