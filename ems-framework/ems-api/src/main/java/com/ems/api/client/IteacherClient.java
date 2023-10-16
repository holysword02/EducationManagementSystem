package com.ems.api.client;

import com.ems.api.domain.po.Student;
import com.ems.api.domain.po.Teacher;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "ems-teacher-service")
public interface IteacherClient {

    @GetMapping("/teacher/getById")
    Teacher getTeacher(@RequestParam("id") Long id);

    @PostMapping("/teacher/getByIds")
    List<Teacher> getTeachers(@RequestBody List<Long> ids);

    @GetMapping("/teacher/getByUsername")
    Teacher getByUsername(@RequestParam("username") String username);
}
