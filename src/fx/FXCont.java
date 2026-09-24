package fx;

import static mindustry.type.ItemStack.with;

import fx.types.LazyBulletType;
import mindustry.content.Items;
import mindustry.entities.bullet.BulletType;
import mindustry.gen.Sounds;
import mindustry.type.Category;
import mindustry.world.Block;
import mindustry.world.blocks.defense.turrets.ItemTurret;

public class FXCont {
  // fixed values
  public static final int BASIC_RANGE = 160, TWOFOLD_RANGE = 220;
  public static final float BASIC_SCRAP_SPEED = 8f, BASIC_COPPER_SPEED = 5f,
      TWOFOLD_COPPER_SPEED = 3.5f, TWOFOLD_LEAD_SPEED = 1.75f, TWOFOLD_SILICON_SPEED = 5.5f;

  // content
  public static BulletType basicScrap, basicCopper;
  public static Block
  // production/crafting
  glassblower,
  // turret
  basic, twofold;

  public static void load() {
    basicScrap = new LazyBulletType(
        BASIC_SCRAP_SPEED,
        0.5f,
        BASIC_RANGE,
        Items.scrap) {
      {
        inaccuracy = 7f;
      }
    };

    basicCopper = new LazyBulletType(BASIC_COPPER_SPEED, 2f, BASIC_RANGE, Items.copper) {
    };
    basic = new ItemTurret("basic") {
      {
        requirements(Category.turret, with(Items.copper, 18, Items.lead, 10));
        ammo(
            Items.scrap, basicScrap,
            Items.copper, basicCopper);
        shootSound = Sounds.shootAlpha;
        shootY = 3f;
        reload = 10f;
        range = BASIC_RANGE;
        shootCone = 15f;
        health = 200;
        rotateSpeed = 8f;
      }
    };

    twofold = new ItemTurret("twofold") {
      {
        requirements(Category.turret, with(Items.copper, 56, Items.lead, 48, Items.graphite, 12));
        size = 2;
        ammo(
          Items.scrap, basicScrap,
          Items.copper, basicCopper);
      shootSound = Sounds.shootAlpha;
      shootY = 3f;
      reload = 10f;
      range = BASIC_RANGE;
      shootCone = 15f;
      health = 200;
      rotateSpeed = 8f;
      }
    };
  }
}
