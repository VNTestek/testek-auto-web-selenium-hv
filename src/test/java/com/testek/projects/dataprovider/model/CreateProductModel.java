package com.testek.projects.dataprovider.model;

import com.testek.datadriven.BaseModel;
import com.testek.datadriven.DataModel;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@ToString
public class CreateProductModel extends BaseModel {
    public DataModel userName;
    public DataModel password;
    public DataModel category;
    public DataModel supplier;
    public DataModel unit;
    public DataModel name;
    public DataModel code;
    public DataModel description;
    public DataModel price;
    public DataModel quantity;

    public CreateProductModel() {
        super();
        userName = createDataModelObj("UserName");
        password = createDataModelObj("Password");
        category = createDataModelObj("Category");
        supplier = createDataModelObj("Supplier");
        unit = createDataModelObj("Unit");
        name = createDataModelObj("Name");
        code = createDataModelObj("Code");
        description = createDataModelObj("Description");
        price = createDataModelObj("Price");
        quantity = createDataModelObj("Quantity");
    }
}
