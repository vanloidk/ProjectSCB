package scb.com.vn.ultility.jdbc;

import java.io.BufferedReader;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import oracle.sql.BLOB;
import oracle.sql.OPAQUE;
import oracle.xdb.XMLType;

// package com.iflex.fcat.xjava.jdbc.JDBCResultSet;
//public final class JDBCResultSet
//package com.iflex.fcat.xjava.jdbc.JDBCEngine;
//package com.iflex.fcat.xml_sql.JFOracleUtils;
public class JDBCEngine {

    public static ArrayList<Object> executeQuery(String query, ArrayList<?> p_args, Connection connection) throws Exception {
        return executeQuery(query, p_args, connection, null);
    }

    public static ArrayList<Object> executeQueryWithoutCloseConnection(String query, ArrayList<?> p_args, Connection connection, Class<?> clazz) throws Exception {
        ResultSet p_rs = null;
        PreparedStatement statement = null;
        ArrayList<Object> arrlst = new ArrayList<Object>();
        Object l_obj = null;
        ResultSetMetaData rsmd = null;

        try {
            statement = connection.prepareStatement(query);
            setArguments(statement, p_args);

            // Thuc hien xu ly du lieu
            p_rs = statement.executeQuery();
            if (p_rs == null) {
                return arrlst;
            }

            rsmd = p_rs.getMetaData();

            if (clazz == null || clazz == HashMap.class) {
                // Lay du lieu
                while (p_rs.next()) {
                    HashMap<String, Object> _hm = new HashMap<String, Object>();
                    for (int k = 1; k <= rsmd.getColumnCount(); k++) {
                        l_obj = getObject(k, rsmd.getColumnType(k), p_rs);
                        _hm.put(rsmd.getColumnName(k).toLowerCase(), l_obj);
                    }
                    arrlst.add(_hm);
                }
            } else {  // Lay du lieu
                while (p_rs.next()) {
                    arrlst.add(setColnameAndValueIntoClz(rsmd, p_rs, clazz));
                }
            }// end if

            return arrlst;
        } catch (Exception e) {
            // System.out.println(setFieldname);
            e.printStackTrace();
            throw e;
            //return arrlst;
        } finally {
            if (p_rs != null) {
                p_rs.close();
            }
            if (statement != null) {
                statement.close();
            }
        }
    }

    public static ArrayList<Object> executeQuery(String query, ArrayList<?> p_args, Connection connection,
            Class<?> clazz) throws Exception {
        ResultSet p_rs = null;
        PreparedStatement statement = null;
        ArrayList<Object> arrlst = new ArrayList<Object>();
        Object l_obj = null;
        ResultSetMetaData rsmd = null;

        try {
            statement = connection.prepareStatement(query);
            setArguments(statement, p_args);

            // Thuc hien xu ly du lieu
            p_rs = statement.executeQuery();
            if (p_rs == null) {
                return arrlst;
            }

            rsmd = p_rs.getMetaData();

            if (clazz == null || clazz == HashMap.class) {
                // Lay du lieu
                while (p_rs.next()) {
                    HashMap<String, Object> _hm = new HashMap<String, Object>();
                    for (int k = 1; k <= rsmd.getColumnCount(); k++) {
                        l_obj = getObject(k, rsmd.getColumnType(k), p_rs);
                        _hm.put(rsmd.getColumnName(k).toLowerCase(), l_obj);
                    }
                    arrlst.add(_hm);
                }
            } else {  // Lay du lieu
                while (p_rs.next()) {
                    arrlst.add(setColnameAndValueIntoClz(rsmd, p_rs, clazz));
                }
            }// end if

            return arrlst;
        } catch (Exception e) {
            // System.out.println(setFieldname);
            e.printStackTrace();
            throw e;
            //return arrlst;
        } finally {
            if (p_rs != null) {
                p_rs.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    public static int executeUpdate(String query, ArrayList<?> p_args, Connection connection) throws Exception {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(query);
            setArguments(statement, p_args);
            return statement.executeUpdate(); // 1 or 0(nothing)
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
            //return -99;
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (Exception ignore) {
                }
            }
//            if (connection != null) {
//                try {
//                    connection.close();
//                } catch (Exception ignore) {
//                }
//            }
        }
    }

    /*
     * public static int executeInsert(Class<?> clazz, Connection connection) {
     * String query = createStatementInsertFromClass(clazz); ArrayList<?> p_agrs
     * = createListFromClass(clazz);
     * 
     * PreparedStatement statement = null; try { statement =
     * connection.prepareStatement(query); setArguments(statement, p_agrs);
     * return statement.executeUpdate(); } catch (Exception e) {
     * e.printStackTrace(); return -99; } finally { try { if (statement != null)
     * statement.close(); } catch (Exception e) { } } }
     */
    // =-========= functions =============
    private static void setArguments(PreparedStatement p_stmt, ArrayList<?> p_args) throws Exception {
        Object l_obj = null;

        if (p_args == null) {
            return;
        }

        for (int l_i = 0; l_i < p_args.size(); l_i++) {
            l_obj = p_args.get(l_i);

            if (l_obj == null) {
                l_obj = "";
            }

            if ((l_obj instanceof Double)) {
                p_stmt.setDouble(l_i + 1, ((Double) l_obj).doubleValue());
            } else if ((l_obj instanceof Integer)) {
                p_stmt.setInt(l_i + 1, ((Integer) l_obj).intValue());
            } else if ((l_obj instanceof Long)) {
                p_stmt.setLong(l_i + 1, ((Long) l_obj).longValue());
            } else if ((l_obj instanceof BigDecimal)) {
                p_stmt.setBigDecimal(l_i + 1, (BigDecimal) l_obj);
            } else if ((l_obj instanceof String)) {
                p_stmt.setString(l_i + 1, (String) l_obj);
            } else if ((l_obj instanceof java.sql.Date)) {
                p_stmt.setDate(l_i + 1, (java.sql.Date) l_obj);
            } else if ((l_obj instanceof Timestamp)) {
                p_stmt.setTimestamp(l_i + 1, (Timestamp) l_obj);
            } else if ((l_obj instanceof java.util.Date)) {
                p_stmt.setDate(l_i + 1, new java.sql.Date(((java.util.Date) l_obj).getTime()));
            } else if ((l_obj instanceof byte[])) {
                Blob blob = (Blob) l_obj;
                p_stmt.setBlob(l_i + 1, blob);
            } // else if ((l_obj instanceof ArrayList)) {
            // l_list_obj = ((ArrayList) l_obj).get(0);
            // if ((l_list_obj instanceof Integer)) {
            // l_int = (Integer) l_list_obj;
            // if (SQL_TYPES.contains(l_int))
            // p_stmt.setNull(l_i + 1, l_int.intValue());
            // else
            // p_stmt.setNull(l_i + 1, l_int.intValue());
            // } else if ((l_list_obj instanceof String)) {
            // l_str = (String) l_list_obj;
            // p_stmt.setObject(l_i + 1, XMLType.createXML(p_con, l_str));
            // }
            // }
            else {
                throw new Exception("Invalid object type detected in argument position[" + l_i + "], Class Name["
                        + l_obj.getClass().getName() + "]");
            }
        }
    }

    private static String getStringValOfClob(Clob p_clob) throws Exception {
        BufferedReader l_reader = null;
        StringBuffer l_sb = null;
        String l_str = null;

        if (p_clob == null) {
            return null;
        }
        l_reader = new BufferedReader(p_clob.getCharacterStream());
        l_sb = new StringBuffer();
        while ((l_str = l_reader.readLine()) != null) {
            l_sb.append(l_str);
        }
        return l_sb.toString();
    }

    protected BLOB getOracleBlob(byte[] p_data, Connection p_conn) throws SQLException {
        BLOB l_tempBlob = null;
        OutputStream l_tempBlobOutputStream = null;
        try {
            l_tempBlob = BLOB.createTemporary(p_conn, true, 10);
            l_tempBlob.open(1);
            l_tempBlobOutputStream = l_tempBlob.setBinaryStream(0L);
            l_tempBlobOutputStream.write(p_data);
            l_tempBlobOutputStream.flush();
            l_tempBlobOutputStream.close();
            l_tempBlob.close();
        } catch (SQLException sqlexp) {
            sqlexp.printStackTrace();
            l_tempBlob.freeTemporary();
        } catch (Exception exp) {
            exp.printStackTrace();
            l_tempBlob.freeTemporary();
        }
        return l_tempBlob;
    }

    private static Object getObject(int column, int columnType, ResultSet resultSet) throws Exception {
        Object l_obj = null;

        switch (columnType) {
            case java.sql.Types.LONGVARCHAR:
            case java.sql.Types.VARCHAR:
            case java.sql.Types.NVARCHAR:
            case java.sql.Types.CHAR:
                l_obj = resultSet.getString(column);
                break;
            case java.sql.Types.TIMESTAMP:
            case java.sql.Types.DATE:
            case java.sql.Types.TIME:
                l_obj = resultSet.getTimestamp(column);
                break;
            case java.sql.Types.DOUBLE:
            case java.sql.Types.FLOAT:
                l_obj = new Double(resultSet.getDouble(column));
                break;
            case java.sql.Types.INTEGER:
            case java.sql.Types.SMALLINT:
            case java.sql.Types.TINYINT:
                l_obj = new Integer(resultSet.getInt(column));
                break;
            case java.sql.Types.BIGINT:
            case java.sql.Types.NUMERIC:
            case java.sql.Types.DECIMAL:
            case 2001:
                l_obj = resultSet.getBigDecimal(column);

                if (l_obj != null) {
                    break;
                }
                l_obj = new BigDecimal("0.0");
                break;

            case java.sql.Types.BLOB:
                Blob l_blob = null;
                long l_long = 1L;

                l_blob = resultSet.getBlob(column);

                if (l_blob == null || l_blob.length() == 0) {
                    l_obj = null;
                } else {
                    l_obj = resultSet.getBlob(column).getBytes(l_long, (int) l_blob.length());
                }
                break;
            case -4:
            case -3:
            case -2:
                l_obj = resultSet.getBytes(column);
                break;
            case 1003:
            case 2007:
                XMLType l_xml_type = null;
                if (((l_obj = resultSet.getObject(column)) instanceof XMLType)) {
                    l_xml_type = (XMLType) l_obj;
                } else if ((l_obj instanceof OPAQUE)) {
                    l_xml_type = XMLType.createXML((OPAQUE) l_obj);
                }
                l_obj = l_xml_type.getStringVal();
                break;
            case 2005:
                Clob l_clob = null;
                l_clob = resultSet.getClob(column);
                l_obj = getStringValOfClob(l_clob);
                break;

            default:
                l_obj = resultSet.getObject(column);
        } // end switch

        return l_obj;
    }

    private static Object setColnameAndValueIntoClz(ResultSetMetaData rsmd, ResultSet resultSet, Class clazz) throws Exception {
        Object objToInvoke = clazz.getConstructor().newInstance();
        Object l_obj = null;
        String _fieldname = null;
        String _columnname = null;

        for (int k = 1; k <= rsmd.getColumnCount(); k++) {
            _columnname = rsmd.getColumnName(k).toLowerCase();
            _fieldname = "set" + _columnname.substring(0, 1).toUpperCase() + _columnname.substring(1);

            switch (rsmd.getColumnType(k)) {
                case java.sql.Types.LONGVARCHAR:
                case java.sql.Types.VARCHAR:
                case java.sql.Types.NVARCHAR:
                case java.sql.Types.CHAR:
                    clazz.getMethod(_fieldname, String.class).invoke(objToInvoke, resultSet.getString(k));
                    break;

                case java.sql.Types.TIMESTAMP:
                case java.sql.Types.DATE:
                case java.sql.Types.TIME:
                    if (resultSet.getTimestamp(k) == null) {
                        clazz.getMethod(_fieldname, java.util.Date.class).invoke(objToInvoke, (Object) null);
                    } else {
                        clazz.getMethod(_fieldname, java.util.Date.class).invoke(objToInvoke, new java.util.Date(((java.sql.Timestamp) resultSet.getTimestamp(k)).getTime()));
                    }
                    break;

                case java.sql.Types.DOUBLE:
                case java.sql.Types.FLOAT:
                    clazz.getMethod(_fieldname, BigDecimal.class).invoke(objToInvoke, resultSet.getDouble(k));
                    break;

                case java.sql.Types.INTEGER:
                case java.sql.Types.SMALLINT:
                case java.sql.Types.TINYINT:
                    clazz.getMethod(_fieldname, Integer.class).invoke(objToInvoke, resultSet.getInt(k));
                    break;

                case java.sql.Types.BIGINT:
                case java.sql.Types.NUMERIC:
                case java.sql.Types.DECIMAL:
//                case 2001:
                    l_obj = resultSet.getBigDecimal(k);
                    if (l_obj != null) {
                        break;
                    }
                    l_obj = new BigDecimal("0.0");

                    if (clazz.getDeclaredField(_columnname).getType().equals(int.class)) {
                        clazz.getMethod(_fieldname, int.class).invoke(objToInvoke, ((BigDecimal) l_obj).intValue());
                    } else if (clazz.getDeclaredField(_columnname).getType().equals(Integer.class)) {
                        clazz.getMethod(_fieldname, Integer.class).invoke(objToInvoke, ((BigDecimal) l_obj).intValue());
                    } else {
                        clazz.getMethod(_fieldname, BigDecimal.class).invoke(objToInvoke, l_obj);
                    }
                    break;

                case java.sql.Types.BLOB:
                    Blob l_blob = null;
                    long l_long = 1L;

                    l_blob = resultSet.getBlob(k);

                    if (l_blob == null || l_blob.length() == 0) {
                        l_obj = null;
                    } else {
                        l_obj = resultSet.getBlob(k).getBytes(l_long, (int) l_blob.length());
                    }
                    clazz.getMethod(_fieldname, byte[].class).invoke(objToInvoke, l_obj);
                    break;
                case -4:
                case -3:
                case -2:
                    l_obj = resultSet.getBytes(k);
                    clazz.getMethod(_fieldname, byte[].class).invoke(objToInvoke, l_obj);
                    break;
                case 1003:
                case 2007:
                    XMLType l_xml_type = null;
                    if (((l_obj = resultSet.getObject(k)) instanceof XMLType)) {
                        l_xml_type = (XMLType) l_obj;
                    } else if ((l_obj instanceof OPAQUE)) {
                        l_xml_type = XMLType.createXML((OPAQUE) l_obj);
                    }
                    l_obj = l_xml_type.getStringVal();
                    clazz.getMethod(_fieldname, String.class).invoke(objToInvoke, l_obj);
                    break;
                case 2005:
                    Clob l_clob = null;
                    l_clob = resultSet.getClob(k);
                    l_obj = getStringValOfClob(l_clob);
                    clazz.getMethod(_fieldname, String.class).invoke(objToInvoke, l_obj);
                    break;

                default:
                    l_obj = resultSet.getObject(k);
                    clazz.getMethod(_fieldname, Object.class).invoke(objToInvoke, l_obj);
            } // end switch
        }
        return objToInvoke;
    }

    private static String createStatementInsertFromClass(Class clazz) {
        String formatInsertStatement = "INSERT INTO %1$s(%2$s) VALUES(%3$s)";
        String tablename = clazz.getSimpleName();
        String fieldnames = "";
        String nbrsign = "";

        for (int i = 0; i < clazz.getDeclaredMethods().length; i++) {
            fieldnames += fieldnames + getSimpleFieldFromMethod(clazz.getDeclaredMethods()[i]) + ",";
            nbrsign += nbrsign + "?,";
        }
        fieldnames = fieldnames.substring(0, fieldnames.length() - 1);
        nbrsign = nbrsign.substring(0, nbrsign.length() - 1);
        return String.format(formatInsertStatement, tablename, fieldnames, nbrsign);
    }

    private static ArrayList createListFromClass(Class clazz) {
        ArrayList p_args = new ArrayList();
        for (int i = 0; i < clazz.getDeclaredFields().length; i++) {
            p_args.add(clazz.getDeclaredFields()[i]);
        }
        return p_args;
    }

    private static String getSimpleFieldFromMethod(Method method) {
        if (method.getName().length() > 3) {
            String prefix = method.getName().substring(0, 2);
            if (!prefix.equalsIgnoreCase("get")) {
                return null;
            }
            return method.getName().substring(3);
        }
        return null;
    }
}
