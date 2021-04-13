package com.carnnjoh.poedatatool.services;

import com.carnnjoh.poedatatool.model.InterpretedMod;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class InterpretedModExtractorServiceTest {

	private List<String> exampleModList = Arrays.asList(
		"118% increased Physical Damage",
		"10% increased Attack Speed",
		"+50% to Global Critical Strike Multiplier",
		"+9 to Intelligence",
		"Regenerate 2.3 Life per second",
		"Reflects 3 Physical Damage to Melee Attackers",
		"With at least 40 Intelligence in Radius, Blight inflicts Withered for 2 seconds",
		"5% increased Attack Speed while holding a Shield",
		"Totems gain +9% to all Elemental Resistances",
		"3% reduced Mana Cost of Skills",
		"Regenerate 10.6 Life per second",
		"Adds 4 to 56 Lightning Damage",
		"Bow Attacks fire an additional Arrow",
		"Adds 3 to 5 Cold Damage to Spells while Dual Wielding",
		"5% increased Impale Effect",
		"Call of Steel deals Reflected Damage with 42% increased Area of Effect",
		"Deals 27 to 41 Physical Damage",
		"Adds 1 to 3 Chaos Damage to Attacks",
		"Regenerate 3 Life per second",
		"603% more Physical Damage with Unarmed Attacks",
		"25% reduced Projectile Speed",
		"23% reduced Enemy Stun Threshold",
		"Deals 116% of Base Damage",
		"Fires 8 additional Arrows",
		"15% reduced Duration of Supported Skills and Damaging Ailments they inflict"
	);

	private final String increasedMod1 = "118% increased Physical Damage";
	private final String increasedMod2 = "10% increased Attack Speed";
	private final String increasedMod3 = "+50% to Global Critical Strike Multiplier";
	private final String increasedMod4 = "99% increased Energy Shield";
	private final String increasedMod5 = "+18% to Lightning Resistance";

	private List<String> increasedModList = Arrays.asList(increasedMod1, increasedMod2, increasedMod3);

	private final String toMod1 = "+9 to Intelligence";
	private final String toMod2 = "+30 to Evasion Rating";
	private final String toMod3 = "+28 to maximum Life";

	private List<String> toModList = Arrays.asList(toMod1, toMod2, toMod3);


	private final String inBetweenNumberMod1 = "Regenerate 2.3 Life per second";
	private final String inBetweenNumberMod2 = "Reflects 3 Physical Damage to Melee Attackers";

	@Autowired
	ExplicitModExtractorService explicitModExtractorService;

	@Before
	public void startUp() {

	}

	@Test
	public void testConvertStringModToGenericModText() {

		StaticModList staticModList = new StaticModList();

		for(String modText : staticModList.modList) {

			System.out.println("-------- Start -------");

			System.out.println("mod text before: " + modText);

			InterpretedMod interpretedMod = explicitModExtractorService.convertStringModToGenericMod(modText);

			System.out.println(String.format("Generic mod text: %s, minValue: %.2f, maxValue: %.2f",
				interpretedMod.getGenericText(),
				interpretedMod.getMinValue(),
				interpretedMod.getMaxValue()
			));
			System.out.println("--------- End ---------");
		}
	}

	@Test
	public void testExtractIncreasedMod() {

		InterpretedMod interpretedMod = explicitModExtractorService.convertStringModToGenericMod(increasedMod1);
		assertExplicitMod(interpretedMod, "#% increased Physical Damage", 118f);

		interpretedMod = explicitModExtractorService.convertStringModToGenericMod(increasedMod2);
		assertExplicitMod(interpretedMod, "#% increased Attack Speed", 10f);

		interpretedMod = explicitModExtractorService.convertStringModToGenericMod(increasedMod3);
		assertExplicitMod(interpretedMod, "+#% to Global Critical Strike Multiplier", 50f);

		interpretedMod = explicitModExtractorService.convertStringModToGenericMod(increasedMod4);
		assertExplicitMod(interpretedMod, "#% increased Energy Shield", 99f);

		interpretedMod = explicitModExtractorService.convertStringModToGenericMod(increasedMod5);
		assertExplicitMod(interpretedMod, "+#% to Lightning Resistance", 18f);
	}

	@Test
	public void testToMods() {
		InterpretedMod interpretedMod = explicitModExtractorService.convertStringModToGenericMod(toMod1);
		assertExplicitMod(interpretedMod, "+# to Intelligence", 9f);

		interpretedMod = explicitModExtractorService.convertStringModToGenericMod(toMod2);
		assertExplicitMod(interpretedMod, "+# to Evasion Rating", 30f);

		interpretedMod = explicitModExtractorService.convertStringModToGenericMod(toMod3);
		assertExplicitMod(interpretedMod, "+# to maximum Life", 28f);
	}

	private void assertExplicitMod(InterpretedMod interpretedMod, String genericModText, Float minValue) {
		assertNotNull(interpretedMod);

		assertNotNull(interpretedMod.getGenericText());
		assertEquals(genericModText, interpretedMod.getGenericText());

		assertNotNull(interpretedMod.getMinValue());
		assertEquals(minValue, interpretedMod.getMinValue(), 0.0);
	}

	private void assertExplicitMod(InterpretedMod interpretedMod, String genericModText, Float minValue, Float maxValue) {
		assertNotNull(interpretedMod);

		assertNotNull(interpretedMod.getGenericText());
		assertEquals(genericModText, interpretedMod.getGenericText());

		assertNotNull(interpretedMod.getMinValue());
		assertEquals(minValue, interpretedMod.getMinValue(), 0.0);

		assertNotNull(interpretedMod.getMaxValue());
		assertEquals(maxValue, interpretedMod.getMaxValue(), 0.0);
	}


}
