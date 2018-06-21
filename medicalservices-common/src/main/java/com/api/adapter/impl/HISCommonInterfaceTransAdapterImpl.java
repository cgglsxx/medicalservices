package com.api.adapter.impl;

import com.api.adapter.AHISTransAdapter;
import com.api.adapter.HISTransAdapterContainer;
import com.api.constant.IConst;
import com.api.result.GlobalErrorInfoException;
import com.api.result.ResultBody;
import com.api.result.messageenum.GlobalErrorInfoEnum;
import com.api.setting.HisSetting;
import com.api.util.XMLUtil;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.apache.cxf.transport.http.HTTPConduit;
import org.apache.cxf.transports.http.configuration.HTTPClientPolicy;
import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.xml.namespace.QName;
import java.util.Map;

/**
 * his交互适配器
 */
@Component
public class HISCommonInterfaceTransAdapterImpl extends AHISTransAdapter {
	@Autowired
	HisSetting setting;
	@PostConstruct
	public void init() {
		HISTransAdapterContainer.putAdapter(setting.getKey(), this);
	}
	@Override
	protected String handleData(Map param) {

		return XMLUtil.map2xmlBody(param,"Request").replaceAll("&lt;", "<").replaceAll("&gt;", ">");
	}


	@Override
	protected ResultBody handleResult(String resultMsg) throws GlobalErrorInfoException {
        Map hisData;
		try {
			hisData = XMLUtil.xmlBody2map(resultMsg,"Response");
		} catch (DocumentException e) {
			throw new GlobalErrorInfoException(GlobalErrorInfoEnum.XML_PARSING_ERROR);
		}
		//获取his交互结果码
		if(hisData.get("appCode")==null|| IConst.HIS_ERROR.equals(hisData.get("appCode").toString())){
            throw new GlobalErrorInfoException(GlobalErrorInfoEnum.HIS_RESULT_ERROR);
        }
        return  new ResultBody(hisData.get("result"));
	}


	@Override
	protected String handleConnection(String transportMsg, String methodName) throws GlobalErrorInfoException {
		Object[] objects;
		try {
			//获取webservice client
			JaxWsDynamicClientFactory clientFactory = JaxWsDynamicClientFactory.newInstance();
			Client client = clientFactory.createClient(setting.getWsdl());
			HTTPConduit conduit = (HTTPConduit) client.getConduit();
			HTTPClientPolicy policy = new HTTPClientPolicy();
			long timeout = 1000*10;//超时时间
			policy.setConnectionTimeout(timeout);//连接超时时间
			policy.setReceiveTimeout(timeout);//接收数据超时间
			conduit.setClient(policy);
			QName name = new QName(setting.getNamespace(), methodName);
			objects = client.invoke(name, transportMsg);
		} catch (Exception e) {
			e.printStackTrace();
			throw new GlobalErrorInfoException(GlobalErrorInfoEnum.HIS_INTERACTION_ERROR);
		}
		return objects[0].toString();

	}

}
