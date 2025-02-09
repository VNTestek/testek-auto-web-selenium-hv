package com.testek.projects.model;

import com.testek.datadriven.BaseModel;
import com.testek.datadriven.DataModel;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@ToString
public class LoginModel extends BaseModel {
    public DataModel userName;
    public DataModel password;

    public LoginModel() {
        super();
        userName = createDataModelObj("UserName");
        password = createDataModelObj("Password");
    }

}
