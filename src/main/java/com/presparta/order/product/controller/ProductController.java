package com.presparta.order.product.controller;

import com.presparta.order.product.dto.ProductCreateRequest;
import com.presparta.order.product.dto.ProductResponse;
import com.presparta.order.product.dto.ProductUpdateRequest;
import com.presparta.order.product.entity.Product;
import com.presparta.order.product.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ProductResponse create(@RequestBody ProductCreateRequest request) {
        Product product = productService.create(request.name(), request.price(), request.stock());
        return ProductResponse.from(product);
    }

    @GetMapping("/{productId}")
    public ProductResponse get(@PathVariable Long productId) {
        return ProductResponse.from(productService.get(productId));
    }

    @GetMapping
    public Page<ProductResponse> getPage(Pageable pageable) {
        return productService.getPage(pageable).map(ProductResponse::from);
    }

    @PutMapping("/{productId}")
    public ProductResponse update(@PathVariable Long productId, @RequestBody ProductUpdateRequest request) {
        Product product = productService.update(productId, request.name(), request.price(), request.stock());
        return ProductResponse.from(product);
    }

    @DeleteMapping("/{productId}")
    public void delete(@PathVariable Long productId) {
        productService.delete(productId);
    }
}
