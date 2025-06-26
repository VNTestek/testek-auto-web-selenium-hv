package com.testek.database.config;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.hibernate.transform.Transformers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Slf4j
public class DBQueryCommand {
    /**
     * Get data  from the database and parse to your object
     *
     * @param queryCommand     :Your query command
     * @param targetClassName: Your mapping object
     * @return : Data Object
     */
    public static <R> R selectSingleNativeQueryDatabase(String dbName, String queryCommand, Class<R> targetClassName) {
        R result = null;
        Session session = null;
        try {
            session = DatabaseManager.openDBSession(dbName);
            result = (R) session.createNativeQuery(queryCommand)
                    .setResultTransformer(Transformers.aliasToBean(targetClassName)).getSingleResult();
        } catch (Exception e) {
            log.error("Error - Get Data From Database..{}", e.toString());
        } finally {
            DatabaseManager.closeDBSession(session);
        }
        return result;
    }

    /**
     * Get data  from the database and parse to your object
     *
     * @param queryCommand     :Your query command
     * @param targetClassName: Your mapping object
     * @return : Data Object
     */
    public static <R> List<R> selectListNativeQueryDatabase(String dbName, String queryCommand, Class<R> targetClassName) {
        List<R> result = new ArrayList<>();
        Session session = null;
        try {
            session = DatabaseManager.openDBSession(dbName);
            result = session.createNativeQuery(queryCommand).setResultTransformer(Transformers.aliasToBean(targetClassName)).getResultList();
        } catch (Exception e) {
            log.error("Error - Get Data From Database..{}", e.toString());
        } finally {
            DatabaseManager.closeDBSession(session);
        }
        return result;
    }

    /**
     * Get data  from the database and parse to your object
     *
     * @param queryCommand :Your query command
     * @return : A list of Hashmap data
     */
    public static List<HashMap<String, Object>> selectListNativeQueryDatabase(String dbName, String queryCommand) {
        List result = new ArrayList();
        Session session = null;
        try {
            session = DatabaseManager.openDBSession(dbName);
            result = session.createNativeQuery(queryCommand).setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE).getResultList();
        } catch (Exception e) {
            log.error("Error - Get Data From Database.. {}",e.toString());
        } finally {
            DatabaseManager.closeDBSession(session);
        }
        return result;
    }

    /**
     * Get data from the database and parse to your object
     *
     * @param queryCommand :Your query command
     * @return : Hashmap data
     */
    public static HashMap<String, Object> selectSingleNativeQueryDatabase(String dbName, String queryCommand) {
        HashMap<String, Object> result = new HashMap<>();
        Session session = null;
        try {
            session = DatabaseManager.openDBSession(dbName);
            result = (HashMap<String, Object>) session.createNativeQuery(queryCommand)
                    .setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE).getSingleResult();
        } catch (Exception e) {
            log.error("Error - Get Data From Database.. {}",e.toString());
        } finally {
            DatabaseManager.closeDBSession(session);
        }
        return result;
    }

    /**
     * Get list data in database
     * @return list hashmap data
     */
    public static List<HashMap<String, Object>> selectMultipleNativeQueryDatabase(String dbName, String queryCommand) {
        List<HashMap<String, Object>> result = null;
        Session session = null;
        try {
            session = DatabaseManager.openDBSession(dbName);
            result = (List<HashMap<String, Object>>) session.createNativeQuery(queryCommand)
                    .setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE).getResultList();
        } catch (Exception e) {
            log.error("Error - Get Data From Database.. {}",e.toString());
        } finally {
            DatabaseManager.closeDBSession(session);
        }
        return result;
    }

    /**
     * Update your data to the database
     *
     * @param queryCommand : Your command
     * @return : a string result (Success or Error)
     */
    public static String updateNativeQueryDatabase(String dbName, String queryCommand) {
        return updateDatabase(dbName, queryCommand, "UPDATE");
    }

    /**
     * Insert your data to the database
     *
     * @param queryCommand : Your command
     * @return : a string result (Success or Error)
     */
    public static String insertNativeQueryDatabase(String dbName, String queryCommand) {
        return updateDatabase(dbName, queryCommand, "INSERT");
    }

    /**
     * Delete your data to the database
     *
     * @param queryCommand : Your command
     * @return : a string result (Success or Error)
     */
    public static String deleteNativeQueryDatabase(String dbName, String queryCommand) {
        return updateDatabase(dbName, queryCommand, "DELETE");
    }

    /**
     * Update your data to DB {@link #updateNativeQueryDatabase(String, String)}
     * {@link #insertNativeQueryDatabase(String, String)} {@link #deleteNativeQueryDatabase(String, String)}
     *
     * @param queryCommand
     * @param type
     * @return
     */
    private static String updateDatabase(String dbName, String queryCommand, String type) {
        log.info("{} - command: {}", type.toUpperCase(), queryCommand);
        String result = "Success";
        Session session = null;
        try {
            session = DatabaseManager.openDBSession(dbName);
            session.clear();
            session.beginTransaction();
            Query query = session.createNativeQuery(queryCommand);
            query.executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            log.error("VDebug: Error - {} Database..\n{}", type, e);
            result = "Error";
        } finally {
            DatabaseManager.closeDBSession(session);
        }
        return result;
    }

}
