package com.testek.projects.dataprovider.model;

import com.testek.datadriven.BaseModel;
import com.testek.datadriven.DataModel;
import lombok.*;

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
        public DataModel oderAddress;
        public DataModel billAddress;
        public DataModel email;
        public DataModel shipperPhone;
        public DataModel phoneNum;

        public CreateOrderModel() {
            super();
            userName = createDataModelObj("UserName");
            password = createDataModelObj("Password");
            employee = createDataModelObj("Employee");
            customer = createDataModelObj("Customer");
            oderAddress = createDataModelObj("Order Address");
            billAddress = createDataModelObj("Bill Address");
            shipperPhone = createDataModelObj("Shipping Phone");
            phoneNum = createDataModelObj("Phone Number");
            email = createDataModelObj("Email");
        }
}
