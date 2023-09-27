package com.ems.basic.controller;


import com.ems.basic.service.IDictService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/dict")
@RequiredArgsConstructor
public class DictController {

    private final IDictService dictService;

}
