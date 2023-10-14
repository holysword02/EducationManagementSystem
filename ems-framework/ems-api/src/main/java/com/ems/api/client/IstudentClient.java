package com.ems.api.client;

import com.ems.api.domain.po.Classes;
import com.ems.api.domain.po.Student;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "ems-student-service")
public interface IstudentClient {

    @GetMapping("/student/getById")
    Student getStudent(@RequestParam("id") Long id);

    @GetMapping("/class/getById")
    Classes getClass(@RequestParam("id") Long id);

    @PostMapping("/class/getByIds")
    List<Classes> getClasses(@RequestBody List<Long> ids);
}
