package fx;

import arc.graphics.Color;
import arc.struct.ObjectMap;

import mindustry.content.Items;
import mindustry.graphics.Pal;
import mindustry.type.Item;

public class FXHelper {

	public static final float toWorldUnit(float tile){
		return tile * 8;
	}

	public static final int toWorldUnit(int tile){
		return tile * 8;
	}

	public static final ObjectMap<Item, Color> ammoBackColors = ObjectMap.of(
			Items.copper, Pal.copperAmmoBack,
			Items.graphite, Pal.graphiteAmmoBack,
			Items.silicon, Pal.siliconAmmoBack,
			Items.metaglass, Pal.glassAmmoBack,
			Items.scrap, Pal.scrapAmmoBack,
			Items.surgeAlloy, Pal.surgeAmmoBack,
			Items.blastCompound, Pal.blastAmmoBack,
			Items.thorium, Pal.thoriumAmmoBack);

	public static final ObjectMap<Item, Color> ammoFrontColors = ObjectMap.of(
			Items.copper, Pal.copperAmmoFront,
			Items.graphite, Pal.graphiteAmmoFront,
			Items.silicon, Pal.siliconAmmoFront,
			Items.metaglass, Pal.glassAmmoFront,
			Items.scrap, Pal.scrapAmmoFront,
			Items.surgeAlloy, Pal.surgeAmmoFront,
			Items.blastCompound, Pal.blastAmmoFront,
			Items.thorium, Pal.thoriumAmmoFront);

	public static Color getItemColor(Item item) {
		return item.color;
	}

	public static Color getItemColor(Item item, boolean brighter) {
		return brighter
				? item.color.cpy().shiftSaturation(0.1f).shiftValue(0.2f)
				: item.color;
	}

	public static Color getAmmoBackColor(Item item) {
		return ammoBackColors.get(item, getItemColor(item));
	}

	public static Color getAmmoFrontColor(Item item) {
		return ammoFrontColors.get(item, getItemColor(item, true));
	}
}