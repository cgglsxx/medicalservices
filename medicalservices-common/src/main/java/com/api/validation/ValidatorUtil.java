package com.api.validation;

import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import java.util.List;

/**
 * 参数验证工具类
 */
public class ValidatorUtil {

    /**
     * 抽取参数验证错误信息
     * @param bindingResult
     * @param <T>
     * @return
     */
    public static <T> ValidateResult getParamErrorMessage(BindingResult bindingResult) {
        if (null == bindingResult) {
            return new ValidateResult(false,  
                    new String[]{"获取对象为空"});
        }
        int errorSize = bindingResult.getErrorCount();
        String[] errMsg = new String[errorSize];
        boolean result = true;
        if (errorSize > 0) {
            int i = 0;
            List error  = bindingResult.getAllErrors();
            for(Object obj:error){
                ObjectError objerror = (ObjectError) obj;
                errMsg[i] = objerror.getDefaultMessage();
                i++;
            }
            result = false;
        }
  
        return new ValidateResult(result, errMsg);  
    }  
}
