package com.bmfn.my;

import com.bmfn.my.service.CFDService;
import com.bmfn.my.service.DMAService;
import com.bmfn.my.service.FXService;
import com.bmfn.my.service.ReportService;
import net.serenitybdd.junit.runners.SerenityRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;

@RunWith(SerenityRunner.class)
public class AppTestStory {

    String commissionInPC;
    String commCFD, commDMA, commFX;
    String startDate = "2015-07-31 17:00";
    String endDate = "2015-08-18 17:00";

    ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("app-context.xml");
    CFDService cfdService = context.getBean(CFDService.class);
    DMAService dmaService = context.getBean(DMAService.class);
    FXService fxService = context.getBean(FXService.class);

    ReportService reportService = context.getBean(ReportService.class);
    List<Integer> list = reportService.getCodes();

    ArrayList<String> results = new ArrayList<>();

    @Test
    public void testCommissions() {

        for(Integer code : list) {
            if (code == 1444) {
                continue;
            } else {
                commCFD = cfdService.getTotalCommissionsPerIB(code, startDate, endDate);
                commDMA = dmaService.getTotalCommissionsPerIB(code, startDate, endDate);
                commFX = fxService.getTotalCommissionsPerIB(code, startDate, endDate);

                Float commSum = (Float.parseFloat(getComm(commCFD)) + Float.parseFloat(getComm(commDMA)) + Float.parseFloat(getComm(commFX)));
                String sum = Float.toString(commSum);

                commissionInPC = reportService.getCommissionsForIB(code);
                Float newComm = Float.parseFloat(commissionInPC.substring(1, commissionInPC.length()-1));
                String newComm2 = Float.toString(newComm);

                results = checkingCommissions(sum, newComm2, code);
            }
        }

        for(String res : results) {
            System.out.println(res);
        }
    }

    public String getComm(String comm) {
        String result = "";
        String cutComm = comm.substring(1, comm.length()-1);
        if (cutComm.equals("null")||cutComm.equals("0")) {
            result = "0";
        } else {
            float num = round(comm, 2);
            result = Float.toString(num);
        }
        return result;
    }

    private float round(String comm, int scale) {
        String newComm = comm.substring(2, comm.length()-1);
        float number = Float.parseFloat(newComm);
        int pow = 10;
        for (int i = 1; i < scale; i++)
            pow *= 10;
        float tmp = number * pow;
        return (float) (int) ((tmp - (int) tmp) >= 0.5f ? tmp + 1 : tmp) / pow;
    }

    public ArrayList<String> checkingCommissions(String sum, String newComm2, int code) {
        if (sum.equals(newComm2)) {
            System.out.println("");
        } else {
            String failure = "Incorrect in ib: " + code + ", sum was: " + sum + ", comm were: " + newComm2;
            System.out.println(failure);
            results.add(failure);
        }
        return results;
    }
}
