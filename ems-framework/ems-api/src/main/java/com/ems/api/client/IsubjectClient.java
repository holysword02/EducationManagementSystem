package com.ems.api.client;

import com.ems.api.domain.dto.SubjectDTO;
import com.ems.api.domain.po.Subject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "ems-subject-service")
public interface IsubjectClient {

    @GetMapping("/subject/getById")
    Subject getSubject(@RequestParam("id") Long id);

    @PostMapping("/subject/getByIds")
    List<SubjectDTO> getSubjects(@RequestBody List<Long> ids);

}
