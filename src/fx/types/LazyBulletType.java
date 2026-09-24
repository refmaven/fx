package fx.types;

import arc.graphics.Color;
import mindustry.content.Items;
import mindustry.entities.bullet.BasicBulletType;
import mindustry.type.Item;

/**
 * A convenient extension of {@link BasicBulletType} for creating
 * item-colored bullets with automatically calculated lifetime
 * and item-based damage.
 */
public class LazyBulletType extends BasicBulletType{
  
  private static final float
    DAMAGE_BASE = 1.25f,
    DAMAGE_COST_MULTIPLIER = 2.25f,
    DAMAGE_HARDNESS_MULTIPLIER = 0.30f,
    DAMAGE_EXPLOSIVENESS_MULTIPLIER = 0.35f,
    DAMAGE_FLAMMABILITY_MULTIPLIER = 0.20f,
    DAMAGE_RADIOACTIVITY_MULTIPLIER = 0.20f,
    DAMAGE_CHARGE_MULTIPLIER = 0.20f,
    DAMAGE_HEALTH_SCALING_MULTIPLIER = 0.25f,
    DEFAULT_BULLET_SIZE = 5f,
    DEFAULT_BULLET_RATIO = 1.5f;
  
  private static final String DEFAULT_BULLET_SPRITE_NAME = "bullet";
  
  public LazyBulletType(
    float speed,
    float damage,
    int range,
    Item item,
    float size,
    float ratio,
    String bulletSprite
  ){
    super(speed, damage, bulletSprite);
    
    this.width = size;
    this.height = size * ratio;
    this.backColor = getAmmoBackColor(item);
    this.frontColor = getAmmoFrontColor(item);
    this.lifetime = range / speed;
  }
  
  public LazyBulletType(
    float speed,
    float damage,
    int range,
    Item item,
    float size,
    float ratio
  ){
    this(
      speed,
      damage,
      range,
      item,
      size,
      ratio,
      DEFAULT_BULLET_SPRITE_NAME
    );
  }
  
  public LazyBulletType(
    float speed,
    float damage,
    int range,
    Item item,
    float size
  ){
    this(
      speed,
      damage,
      range,
      item,
      size,
      DEFAULT_BULLET_RATIO
    );
  }
  
  public LazyBulletType(
    float speed,
    float damage,
    int range,
    Item item
  ){
    this(
      speed,
      damage,
      range,
      item,
      DEFAULT_BULLET_SIZE
    );
  }
  
  public LazyBulletType(
    float speed,
    float damage,
    int range
  ){
    super(speed, damage);
    this.lifetime = range / speed;
  }
  
  public LazyBulletType(
    float speed,
    int range,
    Item item,
    float size,
    float ratio,
    String bulletSprite
  ){
    this(
      speed,
      lazyItemDamage(speed, item),
      range,
      item,
      size,
      ratio,
      bulletSprite
    );
  }
  
  public LazyBulletType(
    float speed,
    int range,
    Item item,
    float size,
    float ratio
  ){
    this(
      speed,
      range,
      item,
      size,
      ratio,
      DEFAULT_BULLET_SPRITE_NAME
    );
  }
  
  public LazyBulletType(
    float speed,
    int range,
    Item item,
    float size
  ){
    this(
      speed,
      range,
      item,
      size,
      DEFAULT_BULLET_RATIO
    );
  }
  
  public LazyBulletType(
    float speed,
    int range,
    Item item
  ){
    this(
      speed,
      range,
      item,
      DEFAULT_BULLET_SIZE
    );
  }
  
  public LazyBulletType(
    float speed,
    int range
  ){
    this(
      speed,
      range,
      Items.copper
    );
  }
  
  private static float lazyItemDamage(float speed, Item item){
    float cost = Math.max(item.cost, 0.5f);
    
    float materialFactor =
      DAMAGE_BASE
        + cost * DAMAGE_COST_MULTIPLIER
        + item.hardness * DAMAGE_HARDNESS_MULTIPLIER
        + item.explosiveness * DAMAGE_EXPLOSIVENESS_MULTIPLIER
        + item.flammability * DAMAGE_FLAMMABILITY_MULTIPLIER
        + item.radioactivity * DAMAGE_RADIOACTIVITY_MULTIPLIER
        + item.charge * DAMAGE_CHARGE_MULTIPLIER
        + item.healthScaling * DAMAGE_HEALTH_SCALING_MULTIPLIER;
    
    return speed * materialFactor;
  }
  
  private static Color getAmmoBackColor(Item item){
    return item.color;
  }
  
  private static Color getAmmoFrontColor(Item item){
    return item.color;
  }
}