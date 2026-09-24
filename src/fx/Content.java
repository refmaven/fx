package fx;

import static mindustry.type.ItemStack.with;
import static mindustry.type.Category.*;

import arc.graphics.Color;
import arc.struct.Seq;
import fx.types.LazyBulletType;
import mindustry.content.Fx;
import mindustry.content.Items;
import mindustry.content.Liquids;
import mindustry.entities.bullet.BulletType;
import mindustry.entities.pattern.ShootPattern;
import mindustry.entities.pattern.ShootSpread;
import mindustry.gen.Sounds;
import mindustry.type.ItemStack;
import mindustry.world.Block;
import mindustry.world.blocks.defense.turrets.ItemTurret;
import mindustry.world.blocks.production.GenericCrafter;
import mindustry.world.draw.DrawDefault;
import mindustry.world.draw.DrawFlame;
import mindustry.world.draw.DrawMulti;

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
      (exponent - 1) * BASE_ROOT_LINE_BUILD_REQUIREMENTS_EXPONENT_MULTIPLIER
    );
    
    return addItemStackArray(
      ItemStack.mult(BASE_ROOT_LINE_BUILD_REQUIREMENTS, multiplier),
      exponent > 1
        ? ItemStack.mult(LATER_ROOT_LINE_BASE_BUILD_REQUIREMENTS, multiplier)
        : ItemStack.empty
    );
  }
  
  public static final float toWorldUnit(float tile){
    return tile * 8;
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
  
  public static final float
    BASIC_SCRAP_SPEED = 8f,
    BASIC_COPPER_SPEED = 2f,
    TWOFOLD_COPPER_SPEED = 3.5f,
    TWOFOLD_LEAD_SPEED = 1.75f,
    TWOFOLD_SILICON_SPEED = 5.5f;
  
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
    
    // bullet types
    
    basicScrap = new LazyBulletType(
      BASIC_SCRAP_SPEED,
      BASIC_RANGE,
      Items.scrap,
      6
    ){{
      inaccuracy = 7f;
    }};
    
    basicCopper = new LazyBulletType(
      BASIC_COPPER_SPEED,
      BASIC_RANGE,
      Items.copper
    );
    
    twofoldCopper = new LazyBulletType(
      TWOFOLD_COPPER_SPEED,
      TWOFOLD_RANGE,
      Items.copper,
      6,
      2
    );
    
    // blocks
    
    glassblower = new GenericCrafter("glassblower"){
      {
        requirements(
          crafting,
          with(
            Items.copper, 55,
            Items.graphite, 40,
            Items.lead, 40,
            Items.silicon, 24
          )
        );
        
        craftEffect = Fx.smeltsmoke;
        outputItem = new ItemStack(Items.metaglass, 1);
        craftTime = 60f;
        size = 2;
        hasPower = hasItems = true;
        
        drawer = new DrawMulti(
          new DrawDefault(),
          new DrawFlame(Color.valueOf("ffc099"))
        );
        
        ambientSound = Sounds.loopSmelter;
        ambientSoundVolume = 0.06f;
        
        consumeItems(
          with(
            Items.lead, 1,
            Items.sand, 1
          )
        );
        
        consumeLiquid(Liquids.slag, 0.225f);
      }
    };
    
    // todo root
    
    root = new ItemTurret("root"){{
      requirements(turret, getNthRootLineRequirements(1));
      
      ammo(
        Items.sand, rootSand,
        Items.scrap, rootScrap,
        Items.copper, rootCopper
      );
      
      range = ROOT_RANGE;
    }};
    
    basic = new ItemTurret("basic"){
      {
        requirements(turret, getNthRootLineRequirements(2));
        
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
      }
    };
    
    twofold = new ItemTurret("twofold"){
      {
        requirements(turret, getNthRootLineRequirements(3));
        
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
        shootSound = Sounds.shootAlpha;
        shootY = 5.5f;
        reload = 45f;
        shootCone = 15f;
        health = 800;
        rotateSpeed = 8f;
      }
    };
    
    spreader = new ItemTurret("spreader"){
      {
        requirements(turret, with(Items.copper, 1));
        
        ammo(
          Items.scrap, basicScrap,
          Items.copper, basicCopper
        );
        
        range = SPREADER_RANGE;
        shoot = ShootSpread.circle(8);
        size = 2;
        shootY = 4f;
        shootSound = Sounds.shootAlpha;
        reload = 15f;
        shootCone = 8f;
        health = 1000;
        rotateSpeed = 1f;
      }
    };
  }
}