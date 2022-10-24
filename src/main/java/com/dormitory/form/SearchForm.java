package com.dormitory.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchForm {
    private String key;
    private String value;
    private Integer page;
    private Integer size;
}
