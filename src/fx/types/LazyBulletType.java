package fx.types;

import arc.graphics.Color;
import mindustry.content.Items;
import mindustry.entities.bullet.BasicBulletType;
import mindustry.type.Item;

/**
 * A convenience extension of {@link BasicBulletType} for creating bullets
 * whose properties are derived from their ammunition item.
 *
 * <p>{@code LazyBulletType} provides several constructor overloads for
 * progressively specifying bullet properties. When an {@link Item} is
 * supplied, the bullet automatically receives the item's color and,
 * when damage is not explicitly specified, its damage is calculated from
 * the item's material properties and the bullet's speed.</p>
 *
 * <p>The bullet's {@link #lifetime} is automatically calculated from its
 * effective range and speed:</p>
 *
 * <pre>
 * lifetime = range / speed
 * </pre>
 *
 * <p>This allows a bullet to travel approximately the requested range
 * regardless of its speed.</p>
 *
 * <p>When using an item-based constructor, damage is calculated from the
 * following item properties:</p>
 *
 * <ul>
 *     <li>{@link Item#cost}</li>
 *     <li>{@link Item#hardness}</li>
 *     <li>{@link Item#explosiveness}</li>
 *     <li>{@link Item#flammability}</li>
 *     <li>{@link Item#radioactivity}</li>
 *     <li>{@link Item#charge}</li>
 *     <li>{@link Item#healthScaling}</li>
 * </ul>
 *
 * <p>The resulting damage is proportional to projectile speed and a
 * material-dependent factor. This means faster bullets deal more damage,
 * while ammunition properties determine how strongly each material
 * contributes to that damage.</p>
 *
 * <p>The class is {@code final} because it is intended to be used directly
 * as a configured bullet type rather than as a base class for further
 * specialization.</p>
 */
public class LazyBulletType extends BasicBulletType{

    /**
     * Base contribution to the material damage factor.
     */
    private static final float DAMAGE_BASE = 1.25f;

    /**
     * Multiplier applied to the item's material cost.
     */
    private static final float DAMAGE_COST_MULTIPLIER = 2.25f;

    /**
     * Multiplier applied to the item's hardness.
     */
    private static final float DAMAGE_HARDNESS_MULTIPLIER = 0.30f;

    /**
     * Multiplier applied to the item's explosiveness.
     */
    private static final float DAMAGE_EXPLOSIVENESS_MULTIPLIER = 0.35f;

    /**
     * Multiplier applied to the item's flammability.
     */
    private static final float DAMAGE_FLAMMABILITY_MULTIPLIER = 0.20f;

    /**
     * Multiplier applied to the item's radioactivity.
     */
    private static final float DAMAGE_RADIOACTIVITY_MULTIPLIER = 0.20f;

    /**
     * Multiplier applied to the item's charge.
     */
    private static final float DAMAGE_CHARGE_MULTIPLIER = 0.20f;

    /**
     * Multiplier applied to the item's health scaling.
     */
    private static final float DAMAGE_HEALTH_SCALING_MULTIPLIER = 0.25f;

    /**
     * Default projectile width and height basis.
     */
    private static final float DEFAULT_BULLET_SIZE = 5f;

    /**
     * Default ratio between projectile height and width.
     */
    private static final float DEFAULT_BULLET_RATIO = 1.5f;

    /**
     * Default sprite used by item-colored bullets.
     */
    private static final String DEFAULT_BULLET_SPRITE_NAME = "bullet";

    /**
     * Creates a fully specified bullet.
     *
     * <p>The projectile dimensions are calculated as:</p>
     *
     * <pre>
     * width  = size
     * height = size * ratio
     * </pre>
     *
     * <p>The bullet's back and front colors are both taken from the
     * supplied ammunition item. Its lifetime is calculated automatically
     * from the supplied range and speed.</p>
     *
     * @param speed bullet speed
     * @param damage direct bullet damage
     * @param range effective travel range
     * @param item ammunition item used for the bullet's color
     * @param size bullet width
     * @param ratio height-to-width ratio
     * @param bulletSprite bullet sprite name
     */
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

    /**
     * Creates a bullet using the default bullet sprite.
     *
     * @param speed bullet speed
     * @param damage direct bullet damage
     * @param range effective travel range
     * @param item ammunition item used for the bullet's color
     * @param size bullet width
     * @param ratio height-to-width ratio
     */
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

    /**
     * Creates a bullet using the default height-to-width ratio.
     *
     * @param speed bullet speed
     * @param damage direct bullet damage
     * @param range effective travel range
     * @param item ammunition item used for the bullet's color
     * @param size bullet width
     */
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

    /**
     * Creates a bullet using the default projectile size and ratio.
     *
     * @param speed bullet speed
     * @param damage direct bullet damage
     * @param range effective travel range
     * @param item ammunition item used for the bullet's color
     */
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

    /**
     * Creates a bullet with explicitly specified speed and damage,
     * without an ammunition item.
     *
     * <p>The default {@link BasicBulletType} sprite is used and the
     * bullet lifetime is calculated from the supplied range and speed.</p>
     *
     * @param speed bullet speed
     * @param damage direct bullet damage
     * @param range effective travel range
     */
    public LazyBulletType(
        float speed,
        float damage,
        int range
    ){
        super(speed, damage);

        this.lifetime = range / speed;
    }

    /**
     * Creates an item-based bullet whose damage is automatically calculated.
     *
     * <p>The damage is calculated by {@link #lazyItemDamage(float, Item)}
     * using the supplied bullet speed and ammunition item's material
     * properties.</p>
     *
     * @param speed bullet speed
     * @param range effective travel range
     * @param item ammunition item
     * @param size bullet width
     * @param ratio height-to-width ratio
     * @param bulletSprite bullet sprite name
     */
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

    /**
     * Creates an automatically damaged bullet using the default sprite.
     *
     * @param speed bullet speed
     * @param range effective travel range
     * @param item ammunition item
     * @param size bullet width
     * @param ratio height-to-width ratio
     */
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

    /**
     * Creates an automatically damaged bullet using the default ratio.
     *
     * @param speed bullet speed
     * @param range effective travel range
     * @param item ammunition item
     * @param size bullet width
     */
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

    /**
     * Creates an automatically damaged bullet using the default size,
     * ratio, and sprite.
     *
     * @param speed bullet speed
     * @param range effective travel range
     * @param item ammunition item
     */
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

    /**
     * Creates an automatically damaged bullet using copper as the
     * default ammunition item.
     *
     * <p>This overload exists primarily as a convenient shorthand when
     * an item-specific color or material calculation is not otherwise
     * specified.</p>
     *
     * @param speed bullet speed
     * @param range effective travel range
     */
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

    /**
     * Calculates damage from projectile speed and ammunition material
     * properties.
     *
     * <p>The calculation first derives a material factor:</p>
     *
     * <pre>
     * materialFactor =
     *     base
     *     + cost * costMultiplier
     *     + hardness * hardnessMultiplier
     *     + explosiveness * explosivenessMultiplier
     *     + flammability * flammabilityMultiplier
     *     + radioactivity * radioactivityMultiplier
     *     + charge * chargeMultiplier
     *     + healthScaling * healthScalingMultiplier
     * </pre>
     *
     * <p>Final damage is then:</p>
     *
     * <pre>
     * damage = speed * materialFactor
     * </pre>
     *
     * <p>The item's cost is clamped to a minimum of {@code 0.5f} so that
     * materials with a zero or very low cost still contribute a baseline
     * amount to the calculation.</p>
     *
     * @param speed bullet speed
     * @param item ammunition item whose material properties determine
     *             the damage multiplier
     * @return calculated bullet damage
     */
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

    /**
     * Gets the back color used by an ammunition item.
     *
     * <p>Currently this simply returns {@link Item#color}. The method is
     * kept separate so the color-selection behavior can be changed
     * independently of the constructors.</p>
     *
     * @param item ammunition item
     * @return ammunition item's color
     */
    private static Color getAmmoBackColor(Item item){
        return item.color;
    }

    /**
     * Gets the front color used by an ammunition item.
     *
     * <p>Currently this simply returns {@link Item#color}. The method is
     * kept separate so the color-selection behavior can be changed
     * independently of the constructors.</p>
     *
     * @param item ammunition item
     * @return ammunition item's color
     */
    private static Color getAmmoFrontColor(Item item){
        return item.color;
    }
}