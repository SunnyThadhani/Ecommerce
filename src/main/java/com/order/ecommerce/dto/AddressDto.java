package com.order.ecommerce.dto;

import com.order.ecommerce.model.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDto {

    @NonNull private String address1;
    @NonNull private String address2;
    @NonNull private String city;
    @NonNull private String state;
    @NonNull private String zip;
    @NonNull private String email;
    @NonNull private String phone;

    public Address toAddressEntity() {
        Address address=new Address();
        address.setAddressId(UUID.randomUUID().toString());
        address.setAddress1(address1);
        address.setAddress2(address2);
        address.setCity(city);
        address.setState(state);
        address.setZip(zip);
        address.setEmail(email);
        address.setPhone(phone);
        address.setCreatedAt(LocalDate.now());
        address.setOrder(null);

        return address;
    }

//    private fun AddressDto.toAddressEntity() = Address(
//            addressId = UUID.randomUUID().toString(),
//    address1 = address1,
//    address2 = address2,
//    city = city,
//    state = state,
//    zip = zip,
//    email = email,
//    phone = phone,
//    createdAt = LocalDate.now(),
//    order = null
//            )
}
