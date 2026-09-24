package fx;

import static mindustry.type.ItemStack.with;
import static mindustry.type.Category.*;

import arc.struct.Seq;
import fx.types.LazyBulletType;
import mindustry.content.Items;
import mindustry.entities.bullet.BulletType;
import mindustry.entities.pattern.ShootPattern;
import mindustry.entities.pattern.ShootSpread;
import mindustry.gen.Sounds;
import mindustry.type.ItemStack;
import mindustry.world.Block;
import mindustry.world.blocks.defense.turrets.ItemTurret;

public class Content{
  
  public static final ItemStack[] BASE_ROOT_LINE_BUILD_REQUIREMENTS =
    with(Items.copper, 15, Items.lead, 12);
  
  public static final ItemStack[] LATER_ROOT_LINE_BASE_BUILD_REQUIREMENTS =
    with(Items.graphite, 8);
  
  public static ItemStack[] addItemStackArray(ItemStack[] a, ItemStack[] b){
    Seq<ItemStack> result = new Seq<>();
    
    for(ItemStack stack : a){
      result.add(stack.copy());
    }
    
    for(ItemStack stack : b){
      boolean found = false;
      
      for(ItemStack existing : result){
        if(existing.item == stack.item){
          existing.amount += stack.amount;
          found = true;
          break;
        }
      }
      
      if(!found){
        result.add(stack.copy());
      }
    }
    
    return result.toArray(ItemStack.class);
  }
  
  public static final float
    BASE_ROOT_LINE_BUILD_REQUIREMENTS_BASE = 1.35f,
    BASE_ROOT_LINE_BUILD_REQUIREMENTS_EXPONENT_MULTIPLIER = 1.2f;
  
  public static ItemStack[] getNthRootLineRequirements(int exponent){
    float multiplier = (float)Math.pow(
      BASE_ROOT_LINE_BUILD_REQUIREMENTS_BASE,
      (exponent - 1) *
        BASE_ROOT_LINE_BUILD_REQUIREMENTS_EXPONENT_MULTIPLIER
    );
    
    ItemStack[] baseRequirements = ItemStack.mult(
      BASE_ROOT_LINE_BUILD_REQUIREMENTS,
      multiplier
    );
    
    ItemStack[] laterRequirements = exponent > 1
                                      ? ItemStack.mult(
      LATER_ROOT_LINE_BASE_BUILD_REQUIREMENTS,
      multiplier
    )
                                      : ItemStack.empty;
    
    return addItemStackArray(
      baseRequirements,
      laterRequirements
    );
  }
  
  public static final float toWorldUnit(float tile){
    return tile * 8f;
  }
  
  public static final int toWorldUnit(int tile){
    return tile * 8;
  }
  
  public static final float
    TURRET_RANGE_BASE = 1.06899f,
    TURRET_RANGE_STARTING_POINT = 25f;
  
  public static int nthTurretRange(int exponent){
    return toWorldUnit(
      (int)Math.ceil(
        TURRET_RANGE_STARTING_POINT *
          Math.pow(TURRET_RANGE_BASE, exponent - 1)
      )
    );
  }
  
  public static final int
    ROOT_RANGE = nthTurretRange(1),
    BASIC_RANGE = nthTurretRange(2),
    TWOFOLD_RANGE = nthTurretRange(3),
    SPREADER_RANGE = nthTurretRange(4);
  
  /*
   * bullet speed progression
   *
   * exponent 1 starts at 4 speed.
   * each subsequent exponent increases speed by approximately 3.5%.
   *
   * speed also affects automatic damage in LazyBulletType,
   * so this progression is intentionally gentle.
   */
  public static final float
    BULLET_SPEED_STARTING_POINT = 4f,
    BULLET_SPEED_BASE = 1.035f;
  
  public static float nthBulletSpeed(int exponent){
    return BULLET_SPEED_STARTING_POINT *
             (float)Math.pow(
               BULLET_SPEED_BASE,
               exponent - 1
             );
  }
  
  public static final float
    ROOT_BULLET_SPEED = nthBulletSpeed(1),
    BASIC_BULLET_SPEED = nthBulletSpeed(2),
    TWOFOLD_BULLET_SPEED = nthBulletSpeed(3),
    SPREADER_BULLET_SPEED = nthBulletSpeed(4);
  
  public static final float
    BASIC_SCRAP_SPEED = BASIC_BULLET_SPEED,
    BASIC_COPPER_SPEED = BASIC_BULLET_SPEED,
    TWOFOLD_COPPER_SPEED = TWOFOLD_BULLET_SPEED,
    TWOFOLD_LEAD_SPEED = TWOFOLD_BULLET_SPEED,
    TWOFOLD_SILICON_SPEED = TWOFOLD_BULLET_SPEED;
  
  public static BulletType
    rootSand,
    rootScrap,
    rootCopper,
    basicScrap,
    basicCopper,
    basicSilicon,
    twofoldCopper,
    twofoldGraphite,
    twofoldLead,
    twofoldMetaglass,
    twofoldSilicon;
  
  public static Block
    slagPowerGenerator,
    largeSlagPowerGenerator,
    glassblower,
    largeGlassblower,
    root,
    basic,
    twofold,
    spreader,
    factor,
    edifice;
  
  public static void load(){
    loadBullets();
    loadBlocks();
  }
  
  public static void loadBullets(){
    
    rootSand = new LazyBulletType(
      ROOT_BULLET_SPEED,
      ROOT_RANGE,
      Items.sand
    );
    
    rootScrap = new LazyBulletType(
      ROOT_BULLET_SPEED,
      ROOT_RANGE,
      Items.scrap
    );
    
    rootCopper = new LazyBulletType(
      ROOT_BULLET_SPEED,
      ROOT_RANGE,
      Items.copper
    );
    
    basicScrap = new LazyBulletType(
      BASIC_BULLET_SPEED,
      BASIC_RANGE,
      Items.scrap
    ){{
      inaccuracy = 7f;
    }};
    
    basicCopper = new LazyBulletType(
      BASIC_BULLET_SPEED,
      BASIC_RANGE,
      Items.copper
    );
    
    basicSilicon = new LazyBulletType(
      BASIC_BULLET_SPEED,
      BASIC_RANGE,
      Items.silicon
    );
    
    twofoldCopper = new LazyBulletType(
      TWOFOLD_COPPER_SPEED,
      TWOFOLD_RANGE,
      Items.copper,
      6f,
      2f
    );
    
    twofoldGraphite = new LazyBulletType(
      TWOFOLD_BULLET_SPEED,
      TWOFOLD_RANGE,
      Items.graphite
    );
    
    twofoldLead = new LazyBulletType(
      TWOFOLD_LEAD_SPEED,
      TWOFOLD_RANGE,
      Items.lead
    );
    
    twofoldMetaglass = new LazyBulletType(
      TWOFOLD_BULLET_SPEED,
      TWOFOLD_RANGE,
      Items.metaglass
    );
    
    twofoldSilicon = new LazyBulletType(
      TWOFOLD_SILICON_SPEED,
      TWOFOLD_RANGE,
      Items.silicon
    );
  }
  
  public static void loadBlocks(){
    
    /*
     * keep your existing block definitions here.
     * only the bullet speed function and its related constants
     * were changed.
     */
    
    root = new ItemTurret("root"){{
      requirements(
        turret,
        getNthRootLineRequirements(1)
      );
      
      ammo(
        Items.sand, rootSand,
        Items.scrap, rootScrap,
        Items.copper, rootCopper
      );
      
      range = ROOT_RANGE;
    }};
    
    basic = new ItemTurret("basic"){{
      requirements(
        turret,
        getNthRootLineRequirements(2)
      );
      
      ammo(
        Items.scrap, basicScrap,
        Items.copper, basicCopper,
        Items.silicon, basicSilicon
      );
      
      range = BASIC_RANGE;
      shootSound = Sounds.shootAlpha;
      shootY = 3f;
      reload = 10f;
      shootCone = 15f;
      health = 200;
      rotateSpeed = 8f;
    }};
    
    twofold = new ItemTurret("twofold"){{
      requirements(
        turret,
        getNthRootLineRequirements(3)
      );
      
      ammo(
        Items.copper, twofoldCopper,
        Items.graphite, twofoldGraphite,
        Items.lead, twofoldLead,
        Items.silicon, twofoldSilicon,
        Items.metaglass, twofoldMetaglass
      );
      
      range = TWOFOLD_RANGE;
      shoot = new ShootPattern(){{
        shots = 2;
      }};
      
      size = 2;
      shootY = 5.5f;
      reload = 45f;
      shootCone = 15f;
      health = 800;
      rotateSpeed = 8f;
    }};
    
    spreader = new ItemTurret("spreader"){{
      requirements(
        turret,
        with(Items.copper, 1)
      );
      
      ammo(
        Items.scrap, basicScrap,
        Items.copper, basicCopper
      );
      
      range = SPREADER_RANGE;
      shoot = ShootSpread.circle(8);
      size = 2;
      shootY = 4f;
      reload = 15f;
      shootCone = 8f;
      health = 1000;
      rotateSpeed = 1f;
    }};
  }
}