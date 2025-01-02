package dev.madhavi.productservicespring.services;

import dev.madhavi.productservicespring.customexceptions.ProductNotFoundException;
import dev.madhavi.productservicespring.dtos.FakeStoreProductDto;
import dev.madhavi.productservicespring.models.Category;
import dev.madhavi.productservicespring.models.Product;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpMessageConverterExtractor;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
@Service("fakeStoreProductService")

public class FakeStoreProductService implements ProductService {
    private RestTemplate restTemplate;
    FakeStoreProductService (RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Product getSingleProduct(Long productId) {
      //call fakeStoreProductDto to fetch the product with given id == http call
        FakeStoreProductDto fakeStoreProductDto = restTemplate.getForObject("https://fakestoreapi.com/products/" + productId,
                FakeStoreProductDto.class);
       /* if(fakeStoreProductDto == null) {
            throw new ProductNotFoundException("Product with id " + productId + "not found");
        }
        making a method call to convert fakeStoreProduct dto to PRODUCT*/
        return convertFakeStoreProductDtoToProduct(fakeStoreProductDto);

       // throw new ArithmeticException();

    }
    @Override
    public List<Product> getAllProducts() {
        FakeStoreProductDto[] fakeStoreProductDtos = restTemplate.getForObject(
                "https://fakestoreapi.com/products" ,
                FakeStoreProductDto[].class
        );
        //convert list of fakeStoreDtos to List of products
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
    //partial update
    @Override
    public Product updateProduct(Long id, Product product) {
        RequestCallback requestCallback = restTemplate.httpEntityCallback(product, FakeStoreProductDto.class);
        HttpMessageConverterExtractor<FakeStoreProductDto> responseExtractor = new HttpMessageConverterExtractor(FakeStoreProductDto.class, restTemplate.getMessageConverters());
        FakeStoreProductDto response = restTemplate.execute("https://fakestoreapi.com/products" + id ,
                HttpMethod.PATCH, requestCallback, responseExtractor);
        return convertFakeStoreProductDtoToProduct(response);
    }
    //put complete replace
    @Override
    public Product replaceProduct(Long id, Product product) {
        RequestCallback requestCallback = restTemplate.httpEntityCallback(product, FakeStoreProductDto.class);
        HttpMessageConverterExtractor<FakeStoreProductDto> responseExtractor = new HttpMessageConverterExtractor(FakeStoreProductDto.class, restTemplate.getMessageConverters());
        FakeStoreProductDto response = restTemplate.execute("https://fakestoreapi.com/products" + id ,
                HttpMethod.PATCH, requestCallback, responseExtractor);
        return null;
    }

    @Override
    public void deleteProduct(Long id) {
    }
    public Product addNewProduct(Product product) {
        return null;
    }
}

