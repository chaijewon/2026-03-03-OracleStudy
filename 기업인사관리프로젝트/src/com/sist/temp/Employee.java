package com.sist.temp;

import java.time.LocalTime;

class Employee {
    String id;
    String status = "미출근";
    LocalTime inTime;
    LocalTime outTime;

    public Employee(String id) {
        this.id = id;
    }
}
