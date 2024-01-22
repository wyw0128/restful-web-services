package com.in28minutes.rest.webservices.restfulwebservices.filtering;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class FilteringController {
    @GetMapping("/filtering")
    public MappingJacksonValue filtering() { // field1, field3
        SomeBean someBean = new SomeBean("value1", "value2", "value3");
        // MappingJacksonValue // used for dynamic filtering which is we want to return different attribute of a same bean with different REST API
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(someBean);

        FilterProvider filters = generateFilterHelper(new String[]{"field1", "field3"});
        // after creating the filter, we set it to mappingJacksonValue
        mappingJacksonValue.setFilters(filters);

        return mappingJacksonValue;
    }

    @GetMapping("/filtering-list")
    public MappingJacksonValue filteringList() { // field2, field3
        List<SomeBean> list = Arrays.asList(new SomeBean("value1", "value2", "value3"),
                new SomeBean("value4", "value5", "value6"));
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(list);

        FilterProvider filters = generateFilterHelper(new String[]{"field2", "field3"});
        mappingJacksonValue.setFilters(filters);
        return mappingJacksonValue;
    }

    private FilterProvider generateFilterHelper(String[] filterFields) {
        // SimpleBeanPropertyFilter: Simple PropertyFilter implementation that only uses property name to determine whether to serialize property as is, or to filter it out.
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept(filterFields);
        // FilterProvider allows you to define a number of filters
        FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);
        return filters;
    }
}
