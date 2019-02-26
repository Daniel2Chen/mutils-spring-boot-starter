package cn.minsin.core.tools.math;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;

/**
 * 数值计算类
 * 
 * @author mintonzhang
 * @date 2019年2月21日
 * @since 0.2.
 */
public class BigDecimals extends Number {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5274572947988215980L;

	private BigDecimal bigDecimal;

	public BigDecimals(char[] val) {
		bigDecimal = new BigDecimal(val);
	}

	public BigDecimals(char[] in, MathContext mc) {
		bigDecimal = new BigDecimal(in);
	}

	public BigDecimals(String val) {
		bigDecimal = new BigDecimal(val);
	}

	public BigDecimals(String val, MathContext mc) {
		bigDecimal = new BigDecimal(val, mc);
	}

	public BigDecimals(double val) {
		bigDecimal = new BigDecimal(val);
	}

	public BigDecimals(double val, MathContext mc) {
		bigDecimal = new BigDecimal(val, mc);
	}

	public BigDecimals(BigInteger val) {
		bigDecimal = new BigDecimal(val);
	}

	public BigDecimals(BigInteger val, MathContext mc) {
		bigDecimal = new BigDecimal(val, mc);
	}

	public BigDecimals(BigInteger unscaledVal, int scale) {
		bigDecimal = new BigDecimal(unscaledVal, scale);
	}

	public BigDecimals(BigInteger unscaledVal, int scale, MathContext mc) {
		bigDecimal = new BigDecimal(unscaledVal, scale, mc);
	}

	public BigDecimals(int val) {
		bigDecimal = new BigDecimal(val);
	}

	public BigDecimals(int val, MathContext mc) {
		bigDecimal = new BigDecimal(val, mc);
	}

	public BigDecimals(long val) {
		bigDecimal = new BigDecimal(val);
	}

	public BigDecimals(long val, MathContext mc) {
		bigDecimal = new BigDecimal(val, mc);
	}

	public int intValue() {
		return bigDecimal.intValue();
	}

	public long longValue() {
		return bigDecimal.longValue();
	}

	public float floatValue() {
		return bigDecimal.floatValue();
	}

	public double doubleValue() {
		return bigDecimal.doubleValue();
	}

	/**
	 * @return 0
	 */
	public static BigDecimals zero() {
		return new BigDecimals(0);
	}

	/**
	 * @return 1
	 */
	public static BigDecimals one() {
		return new BigDecimals(1);
	}

	public BigDecimals add(BigDecimal augend) {

		bigDecimal = bigDecimal.add(augend);
		return this;
	}

	public BigDecimals add(BigDecimals augend) {

		bigDecimal = bigDecimal.add(augend.getValue());
		return this;
	}

	public BigDecimals subtract(BigDecimal subtrahend) {

		bigDecimal = bigDecimal.subtract(subtrahend);
		return this;
	}

	public BigDecimals subtract(BigDecimals subtrahend) {
		bigDecimal = bigDecimal.subtract(subtrahend.getValue());
		return this;
	}

	public BigDecimals multiply(BigDecimal multiplicand) {

		bigDecimal = bigDecimal.multiply(multiplicand);
		return this;
	}

	public BigDecimals multiply(BigDecimals multiplicand) {
		bigDecimal = bigDecimal.multiply(multiplicand.getValue());
		return this;
	}

	public BigDecimals divide(BigDecimal divisor, int scale, RoundingMode roundingMode) {

		bigDecimal = bigDecimal.divide(divisor, scale, roundingMode);
		return this;
	}

	public BigDecimals divide(BigDecimal divisor, RoundingMode roundingMode) {

		bigDecimal = bigDecimal.divide(divisor, roundingMode);
		return this;
	}

	public BigDecimals divide(BigDecimals divisor, int scale, RoundingMode roundingMode) {

		bigDecimal = bigDecimal.divide(divisor.getValue(), scale, roundingMode);
		return this;
	}

	public BigDecimals divide(BigDecimals divisor, RoundingMode roundingMode) {

		bigDecimal = bigDecimal.divide(divisor.getValue(), roundingMode);
		return this;
	}

	public BigDecimals divide(BigDecimal divisor) {

		bigDecimal = bigDecimal.divide(divisor);
		return this;
	}

	public BigDecimals divide(BigDecimals divisor) {

		bigDecimal = bigDecimal.divide(divisor.getValue());
		return this;
	}

	public BigDecimals round(MathContext mc) {

		bigDecimal = bigDecimal.round(mc);
		return this;
	}

	public BigDecimals setScale(int newScale, RoundingMode roundingMode) {

		bigDecimal = bigDecimal.setScale(newScale, roundingMode);
		return this;
	}

	public BigDecimals setScale(int newScale) {

		bigDecimal = bigDecimal.setScale(newScale,RoundingMode.HALF_UP);
		return this;
	}

	public String toString() {

		return bigDecimal.toString();
	}

	public long longValueExact() {

		return bigDecimal.longValueExact();
	}

	public int intValueExact() {

		return bigDecimal.intValueExact();
	}

	public short shortValueExact() {

		return bigDecimal.shortValueExact();
	}

	public byte byteValueExact() {

		return bigDecimal.byteValueExact();
	}

	public BigDecimal getValue() {
		return bigDecimal;
	}
}
