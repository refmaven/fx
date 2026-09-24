package fx;

import static mindustry.type.ItemStack.with;

import arc.graphics.Color;
import fx.types.LazyBulletType;
import mindustry.content.Fx;
import mindustry.content.Items;
import mindustry.content.Liquids;
import mindustry.entities.bullet.BulletType;
import mindustry.gen.Sounds;
import mindustry.type.Category;
import mindustry.type.ItemStack;
import mindustry.world.Block;
import mindustry.world.blocks.defense.turrets.ItemTurret;
import mindustry.world.blocks.production.GenericCrafter;
import mindustry.world.draw.DrawDefault;
import mindustry.world.draw.DrawFlame;
import mindustry.world.draw.DrawMulti;

public class FXCont {
  // \\//\\ CONSTANTS
  public static final int
  // \\ TURRET RANGES
  BASIC_RANGE = 160, TWOFOLD_RANGE = 220;
  public static final float
  // \\ TURRET BULLET SPEED
  BASIC_SCRAP_SPEED = 8f, BASIC_COPPER_SPEED = 5f,
      TWOFOLD_COPPER_SPEED = 3.5f, TWOFOLD_LEAD_SPEED = 1.75f, TWOFOLD_SILICON_SPEED = 5.5f;

  // \\//\\ CONTENT
  // \\ BULLET TYPES
  public static BulletType basicScrap, basicCopper;

  // \\ BLOCKS
  public static Block

  // PRODUCTION / CRAFTING
  glassblower,

      // TURRETS
      basic, twofold;

  public static void load() {

    // \\ BULLET TYPES START
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
    // \\ BULLET TYPES END

    // \\ BLOCKS START

    // PRODUCTION / CRAFTING START
    glassblower = new GenericCrafter("glassblower") {
      {
        requirements(Category.crafting, with(Items.copper, 55, Items.graphite, 40, Items.lead, 40, Items.silicon, 24));
        craftEffect = Fx.smeltsmoke;
        outputItem = new ItemStack(Items.metaglass, 1);
        craftTime = 60f;
        size = 2;
        hasPower = hasItems = true;
        drawer = new DrawMulti(new DrawDefault(), new DrawFlame(Color.valueOf("ffc099")));
        ambientSound = Sounds.loopSmelter;
        ambientSoundVolume = 0.06f;

        consumeItems(with(Items.lead, 1, Items.sand, 1));
        consumeLiquid(Liquids.slag, 0.2f);
      }
    };
    // PRODUCTION / CRAFTING END

    // TURRETS START
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
    // TURRETS END

    // \\ BLOCKS END
  }
}
