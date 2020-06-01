package com.katsueitlab.katsueserver.staff.DTO;

import com.katsueitlab.katsueserver.staff.model.Staff;

import java.util.List;

public class StaffListDTO {

    private static List<Staff> staffList;

    public static List<Staff> getStaffList() {
        return staffList;
    }

    public void setStaffList(List<Staff> staffList) {
        this.staffList = staffList;
    }
}
