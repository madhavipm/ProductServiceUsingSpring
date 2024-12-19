package dev.madhavi.productservicespring.services;

import dev.madhavi.productservicespring.dtos.FakeStoreProductDto;
import dev.madhavi.productservicespring.models.Category;
import dev.madhavi.productservicespring.models.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
@Service

public class FakeStoreProductService implements ProductService {
    private RestTemplate restTemplate;

    public FakeStoreProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Product getSingleProduct(long productId) {
        //call fakeStoreProductDto to fetch the product with given id == http call
        FakeStoreProductDto fakeStoreProductDto = restTemplate.getForObject("https://fakestoreapi.com/products/" + productId,
                FakeStoreProductDto.class);
        //making a method call to convert fakeStoreProduct dto to PRODUCT
        return convertFakeStoreProductDtoToProduct(fakeStoreProductDto);
    }
    @Override
    public List<Product> getAllProducts() {
        FakeStoreProductDto[] fakeStoreProductDtos = restTemplate.getForObject(
                "https://fakestoreapi.com/products" ,
                FakeStoreProductDto[].class
        );
        //conver list of fakestoreDtos to List of products
        List<Product> products = new ArrayList<>();
        for(FakeStoreProductDto fakeStoreProductDto : fakeStoreProductDtos) {
            products.add(convertFakeStoreProductDtoToProduct(fakeStoreProductDto));
        }
        return products;
    }
    private Product convertFakeStoreProductDtoToProduct(FakeStoreProductDto fakeStoreProductDto){
        //convert fakeStoreProduct dto to PRODUCT
        Product product = new Product();
        product.setId(fakeStoreProductDto.getId());
        product.setTitle(fakeStoreProductDto.getTitle());
        product.setPrice(fakeStoreProductDto.getPrice());
        Category category = new Category();
        category.setDescription(fakeStoreProductDto.getCategory());
        product.setCategory(category);
        return product;
    }

}

