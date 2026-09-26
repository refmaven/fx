package fx.types;

import arc.struct.OrderedMap;
import mindustry.type.*;
import mindustry.world.blocks.defense.turrets.ItemTurret;

public class LazyItemTurret extends ItemTurret {
  public LazyItemTurret(
      String name,
      ItemStack[] requirements,
      float range,
      Object... objects) {
    super(name);

    requirements(Category.turret, requirements);
    this.range = range;
    ammoTypes = OrderedMap.of(objects);
  }
}