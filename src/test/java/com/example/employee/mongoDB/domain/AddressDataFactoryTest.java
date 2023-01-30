package com.example.employee.mongoDB.domain;

import com.example.employee.mongoDB.entity.Address;

public class AddressDataFactoryTest {

    public static Address createAddress (String street, String city, String postNumber){
        final Address address = new Address();
        address.setStreet(street);
        address.setCity(city);
        address.setPostNumber(postNumber);

        return address;
    }

    public static Address createDefaultAddress(){
        return createAddress("street", "city", "postNumber");
    }


}
