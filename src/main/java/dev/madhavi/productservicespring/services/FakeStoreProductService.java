package dev.madhavi.productservicespring.services;

import dev.madhavi.productservicespring.customexceptions.ProductNotFoundException;
import dev.madhavi.productservicespring.dtos.FakeStoreProductDto;
import dev.madhavi.productservicespring.models.Category;
import dev.madhavi.productservicespring.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.redis.core.RedisTemplate;
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
    private RedisTemplate<String , Object> redisTemplate;
    FakeStoreProductService (RestTemplate restTemplate , RedisTemplate<String , Object> redisTemplate) {
        this.restTemplate = restTemplate;
        this.redisTemplate = redisTemplate;
    }
    @Override
    public Product getSingleProduct(Long productId) {
    //Trying to fetch the product from redis and also return type is object not product
        Product product  = (Product) redisTemplate.opsForHash().get("PRODUCTS" , "PRODUCT_" + productId);

        if(product != null){
            //cache hit
            return product;
        }
      //call fakeStoreProductDto to fetch the product with given id == http call
        FakeStoreProductDto fakeStoreProductDto = restTemplate.getForObject("https://fakestoreapi.com/products/" + productId,
                FakeStoreProductDto.class);
       /* if(fakeStoreProductDto == null) {
            throw new ProductNotFoundException("Product with id " + productId + "not found");
        }
        making a method call to convert fakeStoreProduct dto to PRODUCT*/
        //cache miss
        //convert fakestoreproduct to product and store the product in redis then return

        product = convertFakeStoreProductDtoToProduct(fakeStoreProductDto);
        redisTemplate.opsForHash().put("PRODUCTS" , "PRODUCT_" + productId,product);

        return convertFakeStoreProductDtoToProduct(fakeStoreProductDto);

       // throw new ArithmeticException();

    }
    @Override
    public Page<Product> getAllProducts(int pageNumber , int pageSize) {
        FakeStoreProductDto[] fakeStoreProductDtos = restTemplate.getForObject(
                "https://fakestoreapi.com/products" ,
                FakeStoreProductDto[].class
        );
        //convert list of fakeStoreDtos to List of products
        List<Product> products = new ArrayList<>();
        for(FakeStoreProductDto fakeStoreProductDto : fakeStoreProductDtos) {
            products.add(convertFakeStoreProductDtoToProduct(fakeStoreProductDto));
        }
        return new PageImpl<>(products);
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

