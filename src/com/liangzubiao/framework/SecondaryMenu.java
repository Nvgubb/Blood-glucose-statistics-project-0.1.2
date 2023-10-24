package com.liangzubiao.framework;


import com.liangzubiao.dataClass.BloodSugerRecord;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class SecondaryMenu {
    private Work work = new Work();

    public boolean InsertSecondaryMenu() {
        try {
            Scanner scanner = new Scanner(System.in);
            boolean loop = false;

            do {
                System.out.println("======请输入你的血糖值（mmol/L）======");
                System.out.println("***注意：范围在3.9-7.8之间 (若要返回界面，输入0)***");

                if (scanner.hasNextFloat()) {//检测是否为数字
                    float data = scanner.nextFloat();

                    if (data == 0) {
                        return false;
                    }

                    if (work.checkData(data)) {//检验小数点后的位数小于等于2位 和其范围
                        //执行sql语句
                        //插入记录data，这里可以不使用事务
                        work.updateRecord(data);
                        loop = true;
                    }
                } else {
                    System.out.println("你输入的不是数字");
                    scanner.next(); // 跳过非数字的 token
                    scanner.nextLine(); // 清空输入缓冲区
                }
            } while (!loop);
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }


    public boolean ShowHistorySecondaryMenu() {
        Scanner scanner = new Scanner(System.in);
        boolean loop = false;

        System.out.println("===========历史记录二级菜单=========");
        do {
            System.out.println("1.按录入时间排序           2.按血糖值高低排序");
            System.out.println("3.同时按录入时间和血糖高低值排序      0.退出");
            System.out.println("请输入你的选择:");
            String next = scanner.next();
            switch (next) {
                case "1":
                    showHistoryOrderByTime();
                    break;
                case "2":
                    showHistoryOrderBySuger();
                    break;
                case "3":
                    showHistotyOrderByBoth();
                    break;
                case "0":
                    loop = true;
                    break;
                default:
                    System.out.println("你的输入有误，请重新输入!!");
                    break;
            }

        } while (!loop);
        return true;
    }


    public void showHistoryOrderByTime() {
        Scanner scanner = new Scanner(System.in);
        boolean loop = false;
        do {
            System.out.println("1.ASC  2.DESC  0.退出");
            System.out.println("请输入你的选择:");
            String next = scanner.next();
            switch (next) {
                case "1":
                    List<BloodSugerRecord> order_by_insert_time_asc = work.queryOrderBy("ORDER BY insert_time");
                    work.showList(order_by_insert_time_asc);
                    break;
                case "2":
                    List<BloodSugerRecord> order_by_insert_time_desc = work.queryOrderBy("ORDER BY insert_time DESC");
                    work.showList(order_by_insert_time_desc);
                    break;
                case "0":
                    loop = true;
                    break;
                default:
                    System.out.println("你的输入有误，请重新输入!!");
                    break;
            }

        } while (!loop);
    }

    public void showHistoryOrderBySuger() {
        Scanner scanner = new Scanner(System.in);
        boolean loop = false;
        do {
            System.out.println("1.ASC  2.DESC  0.退出");
            System.out.println("请输入你的选择:");
            String next = scanner.next();
            switch (next) {
                case "1":
                    List<BloodSugerRecord> order_by_blood_suger_value_asc = work.queryOrderBy("ORDER BY blood_suger_value ASC");
                    work.showList(order_by_blood_suger_value_asc);
                    break;
                case "2":
                    List<BloodSugerRecord> order_by_blood_suger_value_desc = work.queryOrderBy("ORDER BY blood_suger_value DESC");
                    work.showList(order_by_blood_suger_value_desc);
                    break;
                case "0":
                    loop = true;
                    break;
                default:
                    System.out.println("你的输入有误，请重新输入!!");
                    break;
            }

        } while (!loop);
    }

    public void showHistotyOrderByBoth() {
        Scanner scanner = new Scanner(System.in);
        boolean loop = false;
        do {
            System.out.println("1.AA  2.DD  3.AD  4.DA  0.退出");
            System.out.println("请输入你的选择:");
            String next = scanner.next();
            switch (next) {
                case "1":
                    List<BloodSugerRecord> bloodSugerRecordsAA = work.queryOrderBy("ORDER BY blood_suger_value ASC , insert_time ASC");
                    work.showList(bloodSugerRecordsAA);
                    break;

                case "2":
                    List<BloodSugerRecord> bloodSugerRecordsDD = work.queryOrderBy("ORDER BY blood_suger_value DESC , insert_time DESC");
                    work.showList(bloodSugerRecordsDD);
                    break;

                case "3":
                    List<BloodSugerRecord> bloodSugerRecordsAD = work.queryOrderBy("ORDER BY blood_suger_value ASC , insert_time DESC");
                    work.showList(bloodSugerRecordsAD);
                    break;

                case "4":
                    List<BloodSugerRecord> bloodSugerRecordsDA = work.queryOrderBy("ORDER BY blood_suger_value DESC , insert_time ASC");
                    work.showList(bloodSugerRecordsDA);
                    break;
                case "0":
                    loop = true;
                    break;

                default:
                    System.out.println("你的输入有误，请重新输入!!");
                    break;
            }

        } while (!loop);
    }

    public void simpleShow() {
        Scanner scanner = new Scanner(System.in);
        Float taget = null;
        boolean loop = false;
        do {
            System.out.println("1.Max  2.Min  0.退出");
            System.out.println("请输入你的选择:");
            String next = scanner.next();
            switch (next) {
                case "1":
                    if ((taget = work.simpleShowMAX()) != null) {
                        System.out.println(taget);
                    } else {
                        System.out.println("没有值");
                    }
                    break;
                case "2":
                    if ((taget = work.simpleShowMIN()) != null) {
                        System.out.println(taget);
                    } else {
                        System.out.println("没有值");
                    }
                    break;
                case "0":
                    loop = true;
                    break;
                default:
                    System.out.println("你的输入有误，请重新输入!!");
                    break;
            }

        } while (!loop);
    }


}