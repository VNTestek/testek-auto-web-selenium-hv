package com.testek.projects.dataprovider.model;

import com.testek.datadriven.BaseModel;
import com.testek.datadriven.DataModel;
import lombok.*;

import java.beans.PropertyEditor;

@Getter
@Setter
@Builder
@AllArgsConstructor
@ToString
public class CreateOrderModel extends BaseModel {
    public DataModel userName;
    public DataModel password;
    public DataModel customer;
    public DataModel employee;
    public DataModel unit;
    public DataModel addressOrder;
    public DataModel address;
    public DataModel email;
    public DataModel shippingPhone;
    public DataModel phoneNum;

    public CreateOrderModel() {
        super();
        userName = createDataModelObj("UserName");
        password = createDataModelObj("Password");
        unit = createDataModelObj("Unit");

        employee = createDataModelObj("Employee");
        customer = createDataModelObj("Customer");
        addressOrder = createDataModelObj("Address Order");
        address = createDataModelObj("Address");
        shippingPhone = createDataModelObj("Shipping Phone");
        phoneNum = createDataModelObj("Phone Number");
        email = createDataModelObj("Email");
    }
}
