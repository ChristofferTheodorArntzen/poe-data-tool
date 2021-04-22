package com.carnnjoh.poedatatool.model;

public class InterpretedMod {

	private String relatedModId;
	private String genericText;
	private Float minValue;
	private Float maxValue;

	public InterpretedMod(String genericText) {
		this(genericText,null,null, null);
	}

	public InterpretedMod(String genericText, Float minValue) {
		this(genericText, minValue, null, null);
	}
	public InterpretedMod(String genericText, Float minValue, Float maxValue) {
		this(genericText, minValue, maxValue, null);
	}

	public InterpretedMod(String genericText, Float minValue, Float maxValue, String relatedModId) {
		this.genericText = genericText;
		this.minValue = minValue;
		this.maxValue = maxValue;
		this.relatedModId = relatedModId;
	}

	public String getGenericText() {
		return genericText;
	}

	public void setGenericText(String genericText) {
		this.genericText = genericText;
	}

	public Float getMinValue() {
		return minValue;
	}

	public void setMinValue(Float minValue) {
		this.minValue = minValue;
	}

	public Float getMaxValue() {
		return maxValue;
	}

	public void setMaxValue(Float maxValue) {
		this.maxValue = maxValue;
	}

	public String getRelatedModId() {
		return relatedModId;
	}

	public void setRelatedModId(String relatedModId) {
		this.relatedModId = relatedModId;
	}

	@Override
	public String toString() {
		return "InterpretedMod{" +
				"relatedModId='" + relatedModId + '\'' +
				", genericText='" + genericText + '\'' +
				", minValue=" + minValue +
				", maxValue=" + maxValue +
				'}';
	}
}
