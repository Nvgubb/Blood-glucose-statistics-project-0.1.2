package com.liangzubiao.framework;

import com.liangzubiao.dataClass.BloodSugerRecord;
import com.liangzubiao.utilJDBC.Connector;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class Work {


    //检测数据的正确性（范围，结构）
    public boolean checkData(float data) {
        String pattern = "^\\d+\\.\\d{1,2}$";
        String str = Float.toString(Math.abs(data));
        boolean loop = false;
        if (str.matches(pattern)) {
            if (data >= 3.9f && data <= 7.8f) {
                loop = true;
            } else {
                System.out.println("数字范围有误");
            }
        } else {
            System.out.println("数字结构有误");
        }
        return loop;
    }


    public List<BloodSugerRecord> queryOrderBy(Connection connection, String order) {//基于事务
        try {
            QueryRunner queryRunner = new QueryRunner(Connector.getDataSource());
            String sql = "SELECT blood_suger_value `value` , blood_suger_unit unit , insert_time insertTime FROM blood_suger " + order;

            return (List<BloodSugerRecord>) queryRunner.query(connection, sql, new BeanListHandler(BloodSugerRecord.class));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }


    public List<BloodSugerRecord> queryOrderBy(String order) {//不基于事务
        try {
            QueryRunner queryRunner = new QueryRunner(Connector.getDataSource());
            String sql = "SELECT blood_suger_value `value` , blood_suger_unit unit , insert_time insertTime FROM blood_suger " + order;

            return (List<BloodSugerRecord>) queryRunner.query(sql, new BeanListHandler(BloodSugerRecord.class));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public void updateRecord(Connection connection, float data) throws SQLException {//基于事务
        QueryRunner queryRunner = new QueryRunner(Connector.getDataSource());
        String sql = "insert into blood_suger(blood_suger_value , insert_time) values (? , ?)";
        queryRunner.update(connection, sql, data, new Timestamp(new Date().getTime()));
    }

    public void updateRecord(float data) throws SQLException {//不基于事务
        QueryRunner queryRunner = new QueryRunner(Connector.getDataSource());
        String sql = "insert into blood_suger(blood_suger_value , insert_time) values (? , ?)";
        queryRunner.update(sql, data, new Timestamp(new Date().getTime()));
    }

    public Float simpleShowMAX() {
        try {
            QueryRunner queryRunner = new QueryRunner(Connector.getDataSource());
            String sql = "SELECT MAX(m_value) value FROM(SELECT blood_suger_value m_value FROM blood_suger ORDER BY insert_time DESC LIMIT 0,5)temp";
            return queryRunner.query(sql, new ScalarHandler<>());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public Float simpleShowMIN()  {
        try {
            QueryRunner queryRunner = new QueryRunner(Connector.getDataSource());
            String sql = "SELECT MIN(m_value) value FROM(SELECT blood_suger_value m_value FROM blood_suger ORDER BY insert_time DESC LIMIT 0,5)temp";
            return queryRunner.query(sql, new ScalarHandler<>());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public void showList(List list) {
        list.forEach(System.out::println);
    }
}
