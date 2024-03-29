package vn.edu.hcmuaf.fit.efootwearspringboot.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.hcmuaf.fit.efootwearspringboot.dto.product.ProductCreateDto;
import vn.edu.hcmuaf.fit.efootwearspringboot.dto.product.ProductDto;
import vn.edu.hcmuaf.fit.efootwearspringboot.dto.product.ProductUpdateDto;
import vn.edu.hcmuaf.fit.efootwearspringboot.dto.product_image.ProductImageDto;
import vn.edu.hcmuaf.fit.efootwearspringboot.services.product.ProductService;
import vn.edu.hcmuaf.fit.efootwearspringboot.utils.response.HttpResponse;
import vn.edu.hcmuaf.fit.efootwearspringboot.utils.response.HttpResponseError;
import vn.edu.hcmuaf.fit.efootwearspringboot.utils.response.HttpResponseSuccess;
import vn.edu.hcmuaf.fit.efootwearspringboot.utils.result.BaseResult;
import vn.edu.hcmuaf.fit.efootwearspringboot.utils.result.DataResult;

@RestController
@RequestMapping(path = "/products")
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping("/search")
    public ResponseEntity<HttpResponse> getProductByQuery(@RequestParam("query") String query){
        DataResult dataResult = productService.findProducts(query);
        return dataResult.getSuccess() ?
                ResponseEntity.ok(HttpResponseSuccess.success(dataResult.getData())) :
                ResponseEntity.badRequest().body(HttpResponseError.error(dataResult.getHttpStatus(), dataResult.getMessage()));
    }
    @GetMapping
    public ResponseEntity<HttpResponse> getProducts() {
        DataResult dataResult = productService.findProducts();
        return dataResult.getSuccess() ?
                ResponseEntity.ok(HttpResponseSuccess.success(dataResult.getData())) :
                ResponseEntity.badRequest().body(HttpResponseError.error(dataResult.getHttpStatus(), dataResult.getMessage()));
    }

    @GetMapping("/category/slug/{slug}")
    public ResponseEntity<HttpResponse> getProductsByCateSlug(@PathVariable("slug") String slug) {
        DataResult dataResult = productService.findProductsByCateSlug(slug);
        return dataResult.getSuccess() ?
                ResponseEntity.ok(HttpResponseSuccess.success(dataResult.getData())) :
                ResponseEntity.badRequest().body(HttpResponseError.error(dataResult.getHttpStatus(), dataResult.getMessage()));
    }


    // get slug & color
    @GetMapping("/slug/{slug}/color/{id}")
    public ResponseEntity<HttpResponse> getProduct(@PathVariable("slug") String slug, @PathVariable("id") Long color_id) {
        DataResult dataResult = productService.findProduct(slug, color_id);
        return dataResult.getSuccess() ?
                ResponseEntity.ok(HttpResponseSuccess.success(dataResult.getData())) :
                ResponseEntity.badRequest().body(HttpResponseError.error(dataResult.getHttpStatus(), dataResult.getMessage()));
    }

    @GetMapping("/slug/{slug}")
    public ResponseEntity<HttpResponse> getProductsBySlug(@Valid @PathVariable("slug") String slug) {
        DataResult dataResult = productService.findProductsBySlug(slug);
        return dataResult.getSuccess() ?
                ResponseEntity.ok(HttpResponseSuccess.success(dataResult.getData())) :
                ResponseEntity.badRequest().body(HttpResponseError.error(dataResult.getHttpStatus(), dataResult.getMessage()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<HttpResponse> getProduct(@PathVariable(name = "id") Long id) {
        DataResult dataResult = productService.findProduct(id);
        return dataResult.getSuccess() ?
                ResponseEntity.ok(HttpResponseSuccess.success(dataResult.getData())) :
                ResponseEntity.badRequest().body(HttpResponseError.error(dataResult.getHttpStatus(), dataResult.getMessage()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpResponse> deleteProduct(@Valid @PathVariable(name = "id") Long id) {
        BaseResult baseResult = productService.deleteProduct(id);
        return baseResult.getSuccess() ?
                ResponseEntity.ok(HttpResponseSuccess.success()) :
                ResponseEntity.badRequest().body(HttpResponseError.error(baseResult.getHttpStatus(), baseResult.getMessage()));
    }

    @PostMapping()
    public ResponseEntity<HttpResponse> createProduct(@Valid @RequestBody ProductCreateDto productCreateDto) {
        ProductDto productDto = ProductDto.builder()
                .name(productCreateDto.getName())
                .originPrice(productCreateDto.getOriginPrice())
                .discountRate(productCreateDto.getDiscountRate())
                .description(productCreateDto.getDescription())
                .color(productCreateDto.getColor())
                .category(productCreateDto.getCategory())
                .images(productCreateDto.getImages())
                .build();

        BaseResult baseResult = productService.createProduct(productDto);

        return baseResult.getSuccess() ?
                ResponseEntity.ok(HttpResponseSuccess.success()) :
                ResponseEntity.badRequest().body(HttpResponseError.error(baseResult.getHttpStatus(), baseResult.getMessage()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<HttpResponse> updateProduct(@Valid @RequestBody ProductUpdateDto productUpdateDto, @PathVariable("id") Long id) {
        ProductDto productDto = ProductDto.builder()
                .id(id)
                .name(productUpdateDto.getName())
                .originPrice(productUpdateDto.getOriginPrice())
                .discountRate(productUpdateDto.getDiscountRate())
                .description(productUpdateDto.getDescription())
                .color(productUpdateDto.getColor())
                .category(productUpdateDto.getCategory())
                .images(productUpdateDto.getImages())
                .build();
        BaseResult baseResult = productService.updateProduct(productDto);
        return baseResult.getSuccess() ?
                ResponseEntity.ok(HttpResponseSuccess.success()) :
                ResponseEntity.badRequest().body(HttpResponseError.error(baseResult.getHttpStatus(), baseResult.getMessage()));
    }

    @GetMapping("/hot")
    public ResponseEntity<HttpResponse> getProductsHot() {
        DataResult dataResult = productService.findProductsHot();
        return dataResult.getSuccess() ?
                ResponseEntity.ok(HttpResponseSuccess.success(dataResult.getData())) :
                ResponseEntity.badRequest().body(HttpResponseError.error(dataResult.getHttpStatus(), dataResult.getMessage()));
    }

    @GetMapping("/new")
    public ResponseEntity<HttpResponse> getProductsNew() {
        DataResult dataResult = productService.findProductsNew();
        return dataResult.getSuccess() ?
                ResponseEntity.ok(HttpResponseSuccess.success(dataResult.getData())) :
                ResponseEntity.badRequest().body(HttpResponseError.error(dataResult.getHttpStatus(), dataResult.getMessage()));
    }
}
