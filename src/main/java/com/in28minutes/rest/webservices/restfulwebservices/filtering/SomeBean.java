package com.in28minutes.rest.webservices.restfulwebservices.filtering;

import com.fasterxml.jackson.annotation.JsonFilter;

//@JsonIgnoreProperties({"field1", "field2"}) // applies on class, it's for static filtering
@JsonFilter("SomeBeanFilter")
// for dynamic filtering which is used for different filtering logic applied on different REST API
public class SomeBean {
    private String field1;
    // @JsonIgnore
    // applies on properties, and even if you change the name of the property later, no need to change at any other place
    private String field2;
    private String field3;

    public SomeBean(String field1, String field2, String field3) {
        this.field1 = field1;
        this.field2 = field2;
        this.field3 = field3;
    }

    public String getField1() {
        return field1;
    }

    public String getField2() {
        return field2;
    }

    public String getField3() {
        return field3;
    }

    @Override
    public String toString() {
        return "SomeBean{" +
                "field1='" + field1 + '\'' +
                ", field2='" + field2 + '\'' +
                ", field3='" + field3 + '\'' +
                '}';
    }
}
