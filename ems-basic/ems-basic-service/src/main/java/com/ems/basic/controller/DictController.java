package com.ems.basic.controller;

import com.ems.basic.service.IDictService;
import common.treenode.TreeNode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/dict")
@RequiredArgsConstructor
public class DictController {

    private final IDictService dictService;

}
