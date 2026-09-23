package fx;

import arc.graphics.*;
import arc.math.*;
import arc.struct.*;
import fx.types.LazyBulletType;
import mindustry.*;
import mindustry.content.*;
import mindustry.entities.*;
import mindustry.entities.abilities.*;
import mindustry.entities.bullet.*;
import mindustry.entities.effect.*;
import mindustry.entities.part.DrawPart.*;
import mindustry.entities.part.*;
import mindustry.entities.pattern.*;
import mindustry.game.*;
import mindustry.gen.*;
import mindustry.graphics.*;
import mindustry.type.*;
import mindustry.type.unit.*;
import mindustry.world.*;
import mindustry.world.blocks.*;
import mindustry.world.blocks.campaign.*;
import mindustry.world.blocks.defense.*;
import mindustry.world.blocks.defense.turrets.*;
import mindustry.world.blocks.distribution.*;
import mindustry.world.blocks.environment.*;
import mindustry.world.blocks.heat.*;
import mindustry.world.blocks.legacy.*;
import mindustry.world.blocks.liquid.*;
import mindustry.world.blocks.logic.*;
import mindustry.world.blocks.payloads.*;
import mindustry.world.blocks.power.*;
import mindustry.world.blocks.production.*;
import mindustry.world.blocks.sandbox.*;
import mindustry.world.blocks.storage.*;
import mindustry.world.blocks.units.*;
import mindustry.world.consumers.*;
import mindustry.world.draw.*;
import mindustry.world.meta.*;

import static mindustry.Vars.*;
import static mindustry.type.ItemStack.*;

public class FxContent {
  public static int basicRange = 160;
  public static float basicScrapSpeed = 8f;
  public static BulletType basicScrap;
  public static Block
  // turret
  basic;

  public static void load() {
    basicScrap = new LazyBulletType(
        basicScrapSpeed,
        0.5f,
        basicRange,
        Items.scrap, 5, true) {
      {
        inaccuracy = 7f;
        rotateSpeed = 25f;
      }
    };

    basic = new ItemTurret("basic") {
      {
        requirements(Category.turret, with(Items.copper, 35));
        ammo(
            Items.scrap, basicScrap);
        shootSound = Sounds.shootDuo;
        shootY = 3f;
        reload = 10f;
        range = basicRange;
        shootCone = 15f;
        ammoUseEffect = Fx.casing1;
        health = 250;
      }
    };
  }
}
