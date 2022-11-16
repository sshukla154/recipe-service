package com.infosys.search;

import com.infosys.request.SearchCriteriaRequest;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SearchCriteria {
    private String filterKey;
    private Object value;
    private String operation;
    private String dataOption;

    public SearchCriteria() {
    }

    public SearchCriteria(String filterKey, String operation, Object value) {
        super();
        this.filterKey = filterKey;
        this.operation = operation;
        this.value = value;
    }

    public SearchCriteria(SearchCriteriaRequest request) {
        this.dataOption = request.getDataOption();
        this.filterKey = request.getFilterKey();
        this.operation = request.getOperation();
        this.value = request.getValue();
    }

}
