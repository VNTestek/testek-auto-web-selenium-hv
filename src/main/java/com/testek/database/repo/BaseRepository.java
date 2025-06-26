package com.testek.database.repo;


import com.testek.consts.FrameConst;
import com.testek.consts.ProjectConst;

public class BaseRepository {
    final static String env = (FrameConst.ExecuteConfig.EXE_ENV.equalsIgnoreCase("dev") ? "dev" : FrameConst.ExecuteConfig.EXE_ENV).toUpperCase();

    public static String getDBSchema(ProjectConst.DBSchema dbSchema) {
        switch (env) {
            case "DEV":
                return dbSchema.getDevEnv();
            case "UAT":
                return dbSchema.getUatEnv();
            case "PRD":
                return dbSchema.getPrdEnv();
            default:
                return dbSchema.getSitEnv();
        }
    }
}
