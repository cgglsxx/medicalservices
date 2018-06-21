package com.api.validation;

import java.util.Arrays;

public class ValidateResult {
	private boolean result = false; // 抽取结果

	private String[] errMsg; // 错误信息

	public ValidateResult(boolean result, String[] errMsg) {
		this.result = result;
		this.errMsg = errMsg;
	}

	public boolean isSuccess() {
		return result;
	}

	public String getErrMsg() {
		return Arrays.toString(errMsg);
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ValidateResult [result=" + result + ", errMsg="
				+ Arrays.toString(errMsg) + "]";
	}
}
