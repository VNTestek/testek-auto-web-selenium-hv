package com.testek.projects.dataprovider.model;

import com.testek.datadriven.BaseModel;
import com.testek.datadriven.DataModel;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@ToString
public class CreateCusModel extends BaseModel {
    public DataModel name;
    public DataModel phone;
    public DataModel email;
    public DataModel contact;
    public DataModel country;
    public DataModel city;
    public DataModel address;
    public DataModel postalCode;

    public CreateCusModel() {
        super();
        name = createDataModelObj("name");
        phone = createDataModelObj("phone");
        email = createDataModelObj("email");
        contact = createDataModelObj("contact");
        country = createDataModelObj("country");
        city = createDataModelObj("city");
        address = createDataModelObj("address");
        postalCode = createDataModelObj("postalCode");
    }
}
