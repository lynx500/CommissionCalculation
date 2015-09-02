package com.bmfn.my;

import com.bmfn.my.service.CFDService;
import com.bmfn.my.service.ReportService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class App {

    public static void main(String[] args) throws Exception {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("app-context.xml");
        CFDService cfdService = context.getBean(CFDService.class);

        ReportService reportService = context.getBean(ReportService.class);
//        DMAService dmaService = context.getBean(DMAService.class);
//        FXService fxService = context.getBean(FXService.class);
//
//        String comm = cfdService.getTotalCommissionsPerIB(12, "2015-07-31 17:00","2015-08-18 17:00");
//
//        System.out.println(comm);
//
//        String result = reportService.getCommissionsForIB(12);
//
//        System.out.println(result);

        List<Integer> list = reportService.getCodes();
        for(Integer i : list) {
            System.out.println(i);
        }
    }
}
