package com.api.adapter.impl;

import com.alibaba.fastjson.JSONObject;
import com.api.adapter.AHISTransAdapter;
import com.api.constant.IConst;
import com.api.result.GlobalErrorInfoException;
import com.api.result.ResultBody;
import com.api.result.messageenum.GlobalErrorInfoEnum;
import com.api.util.HttpUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import java.util.Map;

/**
 * his交互适配器
 */
@Component
public class HISCommonInterfaceTransAdapterImpl extends AHISTransAdapter {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Override
	protected String handleData(Map param) {
		return JSONObject.toJSONString(param);
	}


	@Override
	protected ResultBody handleResult(String resultMsg) throws GlobalErrorInfoException {
        Map hisData = JSONObject.parseObject(resultMsg);
        ResultBody result = new ResultBody();
		//获取his交互结果码
		if(hisData != null && hisData.get("msgCode")!=null){
			result.setCode(hisData.get("msgCode").toString());
			result.setMessage(hisData.get("msg")+"");
			result.setResult(hisData.get("data"));
        }else{
			logger.error("his业务调用失败,返回如下："+hisData);
			throw new GlobalErrorInfoException(GlobalErrorInfoEnum.HIS_RESULT_ERROR);
		}
		return  result;
	}


	@Override
	protected String handleConnection(String transportMsg, String url) throws GlobalErrorInfoException {
        try {
            return HttpUtil.doPost(url,transportMsg);
        } catch (Exception e) {
			logger.error("his调用失败:"+e);
            throw new GlobalErrorInfoException(GlobalErrorInfoEnum.HIS_INTERACTION_ERROR);
        }
	}

}
