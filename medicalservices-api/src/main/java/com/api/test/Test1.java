package com.api.test;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.junit.Test;

import javax.xml.namespace.QName;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by cjh on 2017/11/13. 省人民医院
 */
public class Test1 {

    //查询病人信息
    @Test
    public  void test1() throws Exception {
        Map param = new HashMap();
        param.put("SystemId","10001");
        param.put("ServiceType","N02");
        param.put("CardNo","0002005379");
        commonmethod(param,"QueryPatientInfoXML","Request");
    }
    //查询病种
    @Test
    public void test2() throws Exception {
        Map param = new HashMap();
        param.put("SystemId","10001");
        param.put("ServiceType","N00");
        commonmethod(param,"GetClinicTypeXML","root");
    }
    //查询排班科室
    @Test
    public void test3() throws Exception {
        Map param = new HashMap();
        param.put("SystemId","10001");
        param.put("ServiceType","N01");
        param.put("Date","2018-10-24");
        param.put("ClinicTypeCode","");
        commonmethod(param,"GetDeptListXML","root");
    }
    //查询排班医生
    @Test
    public void test4() throws Exception {
        Map param = new HashMap();
        param.put("SystemId","10001");
        param.put("ServiceType","03");
        param.put("Date","2018-10-24");
        param.put("EndDate","2018-10-30");
        param.put("Subject","儿科门诊");
        param.put("AP","0");
        param.put("ClinicTypeCode","");
        commonmethod(param,"GetDoctorListXML","root");
    }
    //查询排可预约列表
    @Test
    public void test5() throws Exception {
        Map param = new HashMap();
        param.put("SystemId","10001");
        param.put("ServiceType","04");
        param.put("Date","2018-10-24");
        param.put("Subject","儿科门诊");
        param.put("Doctor","龚莉莉/1269");
        param.put("AP","P");
        param.put("Branch","测试3");
        param.put("RegType","普通号");
        commonmethod(param,"GetTicketListXML","root");
    }
    //预约
    @Test
    public void test6() throws Exception {
        Map param = new HashMap();
        param.put("SystemId","10001");
        param.put("ServiceType","05");
        param.put("CardId","0002005379");
        param.put("CustName","测试11");
        param.put("Phone","11111111111111");
        param.put("Date","2018-10-24");
        param.put("AP","P");
        param.put("Doctor","龚莉莉/1269");
        param.put("Subject","儿科门诊");
        param.put("Branch","测试3");
        param.put("RegType","普通号");
        param.put("TicketId","1");
        param.put("StartTime","15:30:00");
        param.put("EndTime","16:00:00");
        param.put("Operator","银海");
        commonmethod(param,"ReserveXML","root");
    }
    //预约记录
    @Test
    public void test7() throws Exception {
        Map param = new HashMap();
        param.put("SystemId","10001");
        param.put("ServiceType","07");
        param.put("CardId","0002005379");
        param.put("BeginDate","2018-10-23");
        param.put("EndDate","2018-10-24");
        commonmethod(param,"GetReserveHistoryXML","root");
    }
    //取消预约
    @Test
    public void test8() throws Exception {
        Map param = new HashMap();
        param.put("SystemId","10001");
        param.put("ServiceType","07");
        param.put("CardId","0002005379");
        param.put("BeginDate","2018-10-23");
        param.put("EndDate","2018-10-24");
        commonmethod(param,"GetReserveHistoryXML","root");
    }
    //挂号
    @Test
    public void test9() throws Exception {
        Map param = new HashMap();
        param.put("SystemId","10001");
        param.put("ServiceType","10");
        param.put("Patient_Id","0001838179");
        param.put("Hid","128723");
        param.put("Dept_Code","302");
        param.put("Dept_Name","儿科门诊");
        param.put("Doctor_Code","1258");
        param.put("Doctor_Name","龚莉莉");
        param.put("Hb_Date","2018-10-24");
        param.put("Hb_Time","15:30:00");
        param.put("Am_Pm","下午");
        param.put("Operate_Type","2");
        param.put("Sum_Fee","4");
        param.put("Pay_Way","2");
        param.put("Flow_No","444");
        param.put("Reg_Type","挂号类型");
        param.put("Operator","银海");
        param.put("Queue_Number","1");
        commonmethod(param,"RegisterXML","root");
    }
    //取消挂号
    @Test
    public void test10() throws Exception {
        Map param = new HashMap();
        param.put("SystemId","10001");
        param.put("ServiceType","07");
        param.put("CardId","0002005379");
        param.put("BeginDate","2018-10-23");
        param.put("EndDate","2018-10-24");
        commonmethod(param,"GetReserveHistoryXML","root");
    }
    //预交金充值
    @Test
    public void test11() throws Exception {
        Map param = new HashMap();
        param.put("CardNo","0002005379");
        param.put("Amt","4");
        param.put("PayMode","CA");
        param.put("BankNo","");
        param.put("FlowNo","444");
        param.put("UserID","001");
        commonmethodforcz(param,"AddDeposit","Request");
    }
    public void commonmethod(Map param,String method,String root) throws Exception {
        String wsld = "http://132.232.50.16:8012/PreContractRegister.asmx?wsdl";
        String namspace = "http://provide.ws.hosws.greatsoft.net/";//http://tempuri.org/
        String transportMsg = XMLUtil.map2xmlBody(param,root);
        System.out.println("参数:"+transportMsg);
        //获取webservice client
        JaxWsDynamicClientFactory clientFactory = JaxWsDynamicClientFactory.newInstance();
        Client clientTemp = clientFactory.createClient(wsld);
        QName name = new QName(namspace, method);
        Object[] objects = null;
        objects = clientTemp.invoke(name, transportMsg);
        System.out.println(objects[0].toString());
    }
    public void commonmethodforcz(Map param,String method,String root) throws Exception {
        String wsld = "http://132.232.50.16:8011/SelfServiceForBank.asmx?wsdl";
        String namspace = "http://www.xjzysfot.com/SelfServiceForBank/";//http://tempuri.org/
        String transportMsg = XMLUtil.map2xmlBody(param,root);
        System.out.println("参数:"+transportMsg);
        //获取webservice client
        JaxWsDynamicClientFactory clientFactory = JaxWsDynamicClientFactory.newInstance();
        Client clientTemp = clientFactory.createClient(wsld);
        QName name = new QName(namspace, method);
        Object[] objects = null;
        objects = clientTemp.invoke(name, transportMsg);
        System.out.println(objects[0].toString());
    }

}
