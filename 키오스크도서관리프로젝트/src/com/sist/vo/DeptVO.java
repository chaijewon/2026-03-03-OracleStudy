package com.sist.vo;

import lombok.Data;

/*
 *   DEPTNO                                    NOT NULL NUMBER(2)
     DNAME                                     NOT NULL VARCHAR2(30)
     LOC                                       NOT NULL VARCHAR2(50)
 */
@Data
public class DeptVO {
   private int deptno;
   private String dname,loc;
}







