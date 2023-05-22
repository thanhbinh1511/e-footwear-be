package vn.edu.hcmuaf.fit.efootwearspringboot.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.hcmuaf.fit.efootwearspringboot.dto.order.OrderRequestDto;
import vn.edu.hcmuaf.fit.efootwearspringboot.dto.order.OrderRequestStatusDto;
import vn.edu.hcmuaf.fit.efootwearspringboot.services.order.OrderService;
import vn.edu.hcmuaf.fit.efootwearspringboot.utils.response.HttpResponse;
import vn.edu.hcmuaf.fit.efootwearspringboot.utils.response.HttpResponseError;
import vn.edu.hcmuaf.fit.efootwearspringboot.utils.response.HttpResponseSuccess;
import vn.edu.hcmuaf.fit.efootwearspringboot.utils.result.BaseResult;
import vn.edu.hcmuaf.fit.efootwearspringboot.utils.result.DataResult;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    private OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping()
    public ResponseEntity<HttpResponse> getOrders() {
        DataResult dataResult = orderService.getOrders();
        return dataResult.getSuccess() ?
                ResponseEntity.ok(HttpResponseSuccess.success(dataResult.getData())) :
                ResponseEntity.badRequest().body(HttpResponseError.error(dataResult.getHttpStatus(), dataResult.getMessage()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<HttpResponse> getOrder(@PathVariable("id") String id) {
        DataResult dataResult = orderService.getOrder(id);
        return dataResult.getSuccess() ?
                ResponseEntity.ok(HttpResponseSuccess.success(dataResult.getData())) :
                ResponseEntity.badRequest().body(HttpResponseError.error(dataResult.getHttpStatus(), dataResult.getMessage()));
    }


    @GetMapping("/account/{id}")
    public ResponseEntity<HttpResponse> getOrdersByAccountId(@PathVariable("id") Long accountId) {
        DataResult dataResult = orderService.getOrdersByAccountId(accountId);
        return dataResult.getSuccess() ?
                ResponseEntity.ok(HttpResponseSuccess.success(dataResult.getData())) :
                ResponseEntity.badRequest().body(HttpResponseError.error(dataResult.getHttpStatus(), dataResult.getMessage()));
    }

    @PostMapping()
    public ResponseEntity<HttpResponse> createOrder(@RequestBody @Valid OrderRequestDto orderRequestDto) {
        System.out.println(orderRequestDto);
        BaseResult baseResult = orderService.createOrder(orderRequestDto);
        return baseResult.getSuccess() ?
                ResponseEntity.ok(HttpResponseSuccess.success("Đặt hàng thành công")) :
                ResponseEntity.badRequest().body(HttpResponseError.error(baseResult.getHttpStatus(), baseResult.getMessage()));
    }

    @PutMapping()
    public ResponseEntity<HttpResponse> updateOrderStatus(@RequestBody OrderRequestStatusDto orderRequestStatusDto) {
        BaseResult baseResult = orderService.updateStatus(orderRequestStatusDto);
        return baseResult.getSuccess() ?
                ResponseEntity.ok(HttpResponseSuccess.success()) :
                ResponseEntity.badRequest().body(HttpResponseError.error(baseResult.getHttpStatus(), baseResult.getMessage()));
    }

    @GetMapping("/count")
    public ResponseEntity<HttpResponse> countOrder() {
        DataResult dataResult = orderService.countOrder();
        return dataResult.getSuccess() ?
                ResponseEntity.ok(HttpResponseSuccess.success(dataResult.getData())) :
                ResponseEntity.badRequest().body(HttpResponseError.error(dataResult.getHttpStatus(), dataResult.getMessage()));
    }

    @GetMapping("/hot")
    public ResponseEntity<HttpResponse> listOrderHot() {
        DataResult dataResult = orderService.listOrderHot();
        return dataResult.getSuccess() ?
                ResponseEntity.ok(HttpResponseSuccess.success(dataResult.getData())) :
                ResponseEntity.badRequest().body(HttpResponseError.error(dataResult.getHttpStatus(), dataResult.getMessage()));
    }

    @GetMapping("/total")
    public ResponseEntity<HttpResponse> totalByMonth() {
        DataResult dataResult = orderService.countByMonth();
        return dataResult.getSuccess() ?
                ResponseEntity.ok(HttpResponseSuccess.success(dataResult.getData())) :
                ResponseEntity.badRequest().body(HttpResponseError.error(dataResult.getHttpStatus(), dataResult.getMessage()));
    }

    @GetMapping("/totalPrice")
    public ResponseEntity<HttpResponse> totalPriceByMonth() {
        DataResult dataResult = orderService.totalByMonth();
        return dataResult.getSuccess() ?
                ResponseEntity.ok(HttpResponseSuccess.success(dataResult.getData())) :
                ResponseEntity.badRequest().body(HttpResponseError.error(dataResult.getHttpStatus(), dataResult.getMessage()));
    }
}
