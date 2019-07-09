package com.epam.brest.cources;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class DepartmentTest {

    Department department = new Department();


    @Test
    public void getDepartmentId() {
        department.setDepartmentId(15);
        Assert.assertTrue( department.getDepartmentId().equals(15));
    }

}