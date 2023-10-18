package com.ems.api.domain.vo;

import com.ems.api.domain.dto.SubjectDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubjectVO {
    private List<SubjectDTO> records;
    private Long total;
    private Long size;
    private Long current;

}
