package com.testek.projects.dataprovider.model;

import com.testek.datadriven.BaseModel;
import com.testek.datadriven.DataModel;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@ToString
public class CreateSupModel extends BaseModel {
    public DataModel supName;
    public DataModel supPhone;
    public DataModel supContact;
    public DataModel supCountry;
    public DataModel supCity;
    public DataModel supAddress;
    public DataModel supPostalCode;

    public CreateSupModel() {
        super();
        supName = createDataModelObj("SupName");
        supPhone = createDataModelObj("SupPhone");
        supContact = createDataModelObj("SupContact");
        supCountry = createDataModelObj("SupCountry");
        supCity = createDataModelObj("SupCity");
        supAddress = createDataModelObj("SupAddress");
        supPostalCode = createDataModelObj("SupPostalCode");
    }
}
