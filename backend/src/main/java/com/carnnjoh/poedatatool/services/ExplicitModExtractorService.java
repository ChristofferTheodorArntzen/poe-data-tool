package com.carnnjoh.poedatatool.services;

import com.carnnjoh.poedatatool.model.InterpretedMod;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Service
public class ExplicitModExtractorService {

	Pattern regex = Pattern.compile("(\\d+(?:\\.\\d+)?)");

	public List<InterpretedMod> convertExplicitMods(List<String> explicitMods) {

		List<InterpretedMod> interpretedModList = new ArrayList<>();

		for (String explicitMod : explicitMods) {
			interpretedModList.add(convertStringModToGenericMod(explicitMod));
		}

		return interpretedModList;
	}

	public InterpretedMod convertStringModToGenericMod(String modString) {
		Float minValue = null;
		Float maxValue = null;

		String genericText = modString.replaceAll("(\\d+(?:\\.\\d+)?)", "#");

		if (genericText.startsWith("+") || genericText.startsWith("-")) {
			genericText = genericText.substring(1);
		}

		Matcher matcher = regex.matcher(modString);

		while (matcher.find()) {
			if (minValue == null) {
				minValue = Float.parseFloat(matcher.group(1));

				if (genericText.contains("reduced")) {
					minValue = minValue * -1;
				}

				continue;
			}

			if (maxValue == null) {
				maxValue = Float.parseFloat(matcher.group(1));
				continue;
			}
		}


		return new InterpretedMod(genericText, minValue, maxValue);
	}

	@Deprecated
	public InterpretedMod convertStringModToGenericModTextOld(String modsString) {
		Float minValue = null;
		Float maxValue = null;

		String genericText = modsString.replaceAll("(\\d+(?:\\.\\d+)?)", "#");

		//TODO: does not respect decimal point numbers
		String[] tempStringArray = modsString.split("(\\D+(?:\\.\\D+)?)");

		// Return fast if the array is empty
		if (tempStringArray.length == 0) {
			return new InterpretedMod(genericText);
		}

		for (String arrayValue : tempStringArray) {
			if (arrayValue == null || arrayValue.isBlank() || arrayValue.isEmpty()) {
				continue;
			}

			if (minValue == null) {
				minValue = Float.parseFloat(arrayValue);
				continue;
			}

			if (maxValue == null) {
				maxValue = Float.parseFloat(arrayValue);
				continue;
			}
		}

		return new InterpretedMod(genericText, minValue, maxValue);
	}


	//TODO: remove this if it is determined than the block above is next to flawless
//	public ExplicitMod getExplicitModFromString(String explicitMod) {
//
//		String genericModText;
//		Integer minValue = null;
//		Integer maxValue = null;
//
//		// Strict to regex extraction to common mod text
//		if (explicitMod.matches("\\+?[0-9]+\\% [A-Za-z\\ ]+")) {
//
//			// Extracting the number value of the explicit mod
//			String[] tempStringArray = explicitMod.split("%");
//			String numberAsString = tempStringArray[0];
//			if (tempStringArray[0].charAt(0) == '+') {
//				minValue = Integer.parseInt(numberAsString.substring(1));
//			} else {
//				minValue = Integer.parseInt(numberAsString);
//			}
//
//			// removing number from mod value and adding generic value(s)
//			genericModText = explicitMod.replaceFirst("[0-9]+", "#");
//
//		} else if (explicitMod.matches("\\+[0-9]+\\ [A-Za-z ]+")) {
//
//			String[] minValueString = explicitMod.split(" ");
//
//			minValue = Integer.parseInt(minValueString[0].substring(1));
//
//			genericModText = "+#";
//
//			for (int i = 1; i < minValueString.length; i++) {
//				genericModText += " " + minValueString[i];
//			}
//
//		} else {
//
//
//			genericModText = explicitMod;
//		}
//
//		return new ExplicitMod(genericModText, minValue, maxValue);
//	}

}

