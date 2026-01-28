package com.presparta.order.product.service;

import com.presparta.order.product.entity.Product;
import com.presparta.order.product.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    @Transactional(readOnly = true)
    public Product checkProduct(Long productId){
        return productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("상품이 존재하지 않습니다."));
    }

    @Transactional
    public Product create(String name, Long price, Long stock){
        Product product = new Product(name, price, stock);
        return productRepository.save(product);
    }

    @Transactional(readOnly = true)
    public Product get(Long productId) {
        return checkProduct(productId);
    }

    @Transactional(readOnly = true)
    public Page<Product> getPage(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    public Product update(Long productId, String name, Long price, Long stock) {
        Product product = checkProduct(productId);
        product.update(name, price, stock);
        return product;
    }

    public void delete(Long productId) {
        Product product = checkProduct(productId);
        product.delete();
    }
}
