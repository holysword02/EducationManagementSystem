package com.ems.api.client;

import com.ems.api.domain.po.Dict;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "ems-basic-service")
public interface IbasicClient {

    @GetMapping("/dict/getById")
    Dict getDict(@RequestParam("id") Long id);

    @PostMapping("/dict/getByIds")
    List<Dict> getDicts(@RequestBody List<Long> ids);

}
