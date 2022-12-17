package com.dochero.documentrevisionservice.search;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class SearchCriteria {
    private String key;
    private String operation;
    private Object value;
    private Object orderBy;
}
