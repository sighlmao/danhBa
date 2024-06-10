package com.example.danhba;

public class Department {
    private String id;
    private String name;
    private String email;
    private String website;
    private String address;
    private String phoneNumber;
    private int logo;

    public Department() {
        // Constructor mặc định
    }

    public Department(String id, String name, String email, String website, String address, String phoneNumber, int logo) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.website = website;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.logo = logo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getLogo() {
        return logo;
    }

    public void setLogo(int logo) {
        this.logo = logo;
    }
}
