package com.example.employeeManagement.exceptions;  // Ensure it’s in the correct package

class CompanyNotFoundException extends RuntimeException {

    // Constructor that takes the company ID
    public CompanyNotFoundException(Long id) {
        super("Company with ID " + id + " not found.");
    }
}

