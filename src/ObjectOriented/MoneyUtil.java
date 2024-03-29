package ObjectOriented;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * 金额处理工具类
 */
public class MoneyUtil {
	public static DecimalFormat fnum=new DecimalFormat("##0.00000000000000000000");
	/**
	 * 格式化
	 */
	public static String moneyFormat(String str){
		if(str==null||str==""){
			return "0.00";
		}
		return fnum.format(new BigDecimal(str));
	}
	/**
	 * 金额相加
	 */
	public static String moneyAdd(String valueStr,String addStr){
		BigDecimal value=new BigDecimal(valueStr);
		BigDecimal augend=new BigDecimal(addStr);
		return fnum.format(value.add(augend));
	}
	/**
	 * 金额相加
	 * @param value 基础值
	 * @param augend 被加数
	 * @return
	 */
	public static BigDecimal moneyAdd(BigDecimal value,BigDecimal augend){
		return value.add(augend);
	}
	/**
	 * 金额相减
	 * @param valueStr 基础值
	 * @param minusStr 减数
	 * @return
	 */
	public static String moneySub(String valueStr,String minusStr){
		BigDecimal value= new BigDecimal(valueStr);
		BigDecimal subtrahend = new BigDecimal(minusStr);
		return fnum.format(value.subtract(subtrahend));
	}

	/**
	 * 金额相减
	 * @param value 基础值
	 * @param subtrahend 减数
	 * @return
	 */
	public static BigDecimal moneySub(BigDecimal value,BigDecimal subtrahend){
		return value.subtract(subtrahend);
	}
	/**
	 * 金额相乘
	 * @param valueStr 基础值
	 * @param mulStr 被乘数
	 * @return
	 */
	public static String moneyMul(String valueStr,String mulStr){
		BigDecimal value = new BigDecimal(valueStr);
		BigDecimal mulValue = new BigDecimal(mulStr);
		return fnum.format(value.multiply(mulValue));
	}

	/**
	 * 金额相乘
	 * @param value 基础值
	 * @param mulValue 被乘数
	 * @return
	 */
	public static BigDecimal moneyMul(BigDecimal value,BigDecimal mulValue){
		return value.multiply(mulValue);
	}
	/**
	 * 金额相除 <br/>
	 * 精确小位小数
	 * @param valueStr 基础值
	 * @param divideStr 被乘数
	 * @return
	 */
	public static String moneydiv(String valueStr,String divideStr){
		BigDecimal value = new BigDecimal(valueStr);
		BigDecimal divideValue = new BigDecimal(divideStr);
		return fnum.format(value.divide(divideValue, 2, BigDecimal.ROUND_HALF_UP));
	}

	/**
	 * 金额相除 <br/>
	 * 精确小位小数
	 * @param value 基础值
	 * @param divideValue 被乘数
	 * @return
	 */
	public static BigDecimal moneydiv(BigDecimal value,BigDecimal divideValue){
		return value.divide(divideValue, 2, BigDecimal.ROUND_HALF_UP);
	}
	/**
	 * 值比较大小
	 * <br/>如果valueStr大于等于compValueStr,则返回true,否则返回false
	 *  true 代表可用余额不足
	 * @param valueStr (需要消费金额)
	 * @param compValueStr (可使用金额)
	 * @return
	 */
	public static boolean moneyComp(String valueStr,String compValueStr){
		BigDecimal value = new BigDecimal(valueStr);
		BigDecimal compValue = new BigDecimal(compValueStr);
		//0:等于    >0:大于    <0:小于
		int result = value.compareTo(compValue);
		if(result >= 0){
			return true;
		}else{
			return false;
		}
	}

	/**
	 * 值比较大小
	 * <br/>如果valueStr大于等于compValueStr,则返回true,否则返回false
	 *  true 代表可用余额不足
	 * @param valueStr (需要消费金额)
	 * @param compValueStr (可使用金额)
	 * @return
	 */
	public static boolean moneyComp(BigDecimal valueStr,BigDecimal compValueStr){
		//0:等于    >0:大于    <0:小于
		int result = valueStr.compareTo(compValueStr);
		if(result >= 0){
			return true;
		}else{
			return false;
		}
	}

	/**
	 * 金额乘以，省去小数点
	 * @param valueStr 基础值
	 * @return
	 */
	public static String moneyMulOfNotPoint (String valueStr, String divideStr){
		BigDecimal value = new BigDecimal(valueStr);
		BigDecimal mulValue = new BigDecimal(divideStr);
		valueStr = fnum.format(value.multiply(mulValue));
		return fnum.format(value.multiply(mulValue)).substring(0, valueStr.length()-3);
	}
}
