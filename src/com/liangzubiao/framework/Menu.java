package com.liangzubiao.framework;

import java.util.Scanner;

public class Menu {

    public void showMenu() {
        boolean loop = true;
        SecondaryMenu work = new SecondaryMenu();
        Scanner scanner = new Scanner(System.in);

        System.out.println("=============欢迎使用血糖统计系统============");
        do {
            System.out.println("    ====== 1.输入       2.历史记录查看 ======");
            System.out.println("    ====== 3.简单查看           4.退出 ======");
            System.out.println("请输入你的选择");
            String next = scanner.next();
            switch (next) {
                case "1":
                    if (work.InsertSecondaryMenu()) {
                        System.out.println("**输入成功！**");
                    }
                    break;
                case "2":
                    work.ShowHistorySecondaryMenu();
                    break;
                case "3":
                    work.simpleShow();
                    break;
                case "4":
                    loop = false;
                    break;
                default:
                    System.out.println("你的输入有误，请重新输入!!");
                    break;
            }
        } while (loop);
        System.out.println("**退出成功**");
    }
}
