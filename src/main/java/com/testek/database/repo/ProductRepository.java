package com.testek.database.repo;


import com.testek.consts.ProjectConst.DBSchema;
import com.testek.consts.ProjectConst.Databases;
import lombok.Getter;

import java.util.HashMap;
import java.util.List;

import static com.testek.database.config.DBQueryCommand.selectListNativeQueryDatabase;

@Getter
public class ProductRepository extends BaseRepository {
    public static List<HashMap<String, Object>> getProductById(String id) {
        String query = String.format("select * from %s.product where id = '%s'",
                getDBSchema(DBSchema.TESTEK_PRODUCT), id);
        return selectListNativeQueryDatabase(Databases.API_TESTING.getName(), query);
    }
}
