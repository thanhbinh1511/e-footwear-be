package vn.edu.hcmuaf.fit.efootwearspringboot.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import vn.edu.hcmuaf.fit.efootwearspringboot.dto.color.ColorCreateDto;
import vn.edu.hcmuaf.fit.efootwearspringboot.dto.color.ColorDto;
import vn.edu.hcmuaf.fit.efootwearspringboot.dto.color.ColorUpdateDto;
import vn.edu.hcmuaf.fit.efootwearspringboot.services.color.ColorService;
import vn.edu.hcmuaf.fit.efootwearspringboot.utils.response.HttpResponse;
import vn.edu.hcmuaf.fit.efootwearspringboot.utils.response.HttpResponseError;
import vn.edu.hcmuaf.fit.efootwearspringboot.utils.response.HttpResponseSuccess;
import vn.edu.hcmuaf.fit.efootwearspringboot.utils.result.BaseResult;
import vn.edu.hcmuaf.fit.efootwearspringboot.utils.result.DataResult;

@RestController
@RequestMapping("/colors")
public class ColorController {

    private ColorService colorService;

    @Autowired
    public ColorController(ColorService colorService) {
        this.colorService = colorService;
    }

    // find all
    @GetMapping
    public ResponseEntity<HttpResponse> findColors() {
        DataResult dataResult = colorService.findAll();
        return dataResult.getSuccess() ?
                ResponseEntity.ok(HttpResponseSuccess.success(dataResult.getData())) :
                ResponseEntity.badRequest().body(HttpResponseError.error(dataResult.getHttpStatus(), dataResult.getMessage()));
    }

    // find one
    @GetMapping("/{id}")
    public ResponseEntity<HttpResponse> findColor(@PathVariable("id") Long id) {
        DataResult dataResult = colorService.findColor(id);
        return dataResult.getSuccess() ?
                ResponseEntity.ok(HttpResponseSuccess.success(dataResult.getData())) :
                ResponseEntity.badRequest().body(HttpResponseError.error(dataResult.getHttpStatus(), dataResult.getMessage()));
    }

    // create a new color
    @PostMapping
    public ResponseEntity<HttpResponse> createColor(@RequestBody @Valid ColorCreateDto colorCreateDto) {
        ColorDto colorDto = ColorDto.builder()
                .codeColor(colorCreateDto.getCodeColor())
                .name(colorCreateDto.getName())
                .build();

        BaseResult baseResult = colorService.createColor(colorDto);
        return baseResult.getSuccess() ?
                ResponseEntity.ok(HttpResponseSuccess.success()) :
                ResponseEntity.badRequest().body(HttpResponseError.error(baseResult.getHttpStatus(), baseResult.getMessage()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<HttpResponse> updateColor(@RequestBody @Valid ColorUpdateDto colorUpdateDto, @PathVariable("id") Long id) {
        ColorDto colorDto = ColorDto.builder()
                .id(id)
                .codeColor(colorUpdateDto.getCodeColor())
                .name(colorUpdateDto.getName())
                .build();
        BaseResult baseResult = colorService.updateColor(colorDto);
        return baseResult.getSuccess() ?
                ResponseEntity.ok(HttpResponseSuccess.success()) :
                ResponseEntity.badRequest().body(HttpResponseError.error(baseResult.getHttpStatus(), baseResult.getMessage()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpResponse> deleteColor(@PathVariable("id") Long id) {
        BaseResult baseResult = colorService.deleteColor(id);
        return baseResult.getSuccess() ?
                ResponseEntity.ok(HttpResponseSuccess.success()) :
                ResponseEntity.badRequest().body(HttpResponseError.error(baseResult.getHttpStatus(), baseResult.getMessage()));
    }
}
