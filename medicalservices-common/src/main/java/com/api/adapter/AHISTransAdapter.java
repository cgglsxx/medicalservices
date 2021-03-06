package com.api.adapter;

import com.api.result.GlobalErrorInfoException;
import com.api.result.ResultBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * 抽象类 定义了与his交互流程
 */
public abstract class AHISTransAdapter {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	public ResultBody sendMsg(Map param,String url) throws GlobalErrorInfoException {

		//处理传入的参数
		String transportMsg = handleData(param);
		logger.info("his交互参数:"+transportMsg);
		//与医院交互获取返回字符串
		String resultMsg = handleConnection(transportMsg,url);
		logger.info("his返回参数:"+resultMsg);
		//对医院返回的字符串进行封装
		ResultBody backData = handleResult(resultMsg);
		return backData;
	}

	/**
	 * 封装调用参数
	 * @param param
	 * @return
	 */
	protected abstract String handleData(Map param);

	/**
	 * 处理his返回结果
	 * @param resultMsg
	 * @return
	 * @throws Exception
	 */
	protected abstract ResultBody handleResult(String resultMsg) throws GlobalErrorInfoException;

	/**
	 * his交互
	 * @param transportMsg 参数
	 * @param methodName 方法名
	 * @return
	 * @throws Exception
	 */
	protected abstract String handleConnection(String transportMsg, String methodName) throws GlobalErrorInfoException;




}
