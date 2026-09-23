package fx.types;

import fx.FXHelper;
import mindustry.content.Items;
import mindustry.entities.bullet.BasicBulletType;
import mindustry.type.Item;

/**
 * A convenient extension of {@link BasicBulletType} for creating
 * item-colored bullets with automatically calculated lifetime
 * and optional item-based damage.
 *
 * <p>The range parameter is converted into bullet lifetime using
 * {@code lifetime = range / speed}. When an item is provided,
 * its corresponding ammunition colors are used when available;
 * otherwise, the item's color is used as a fallback.</p>
 *
 * <p>The ratio parameter controls the bullet's height relative
 * to its width. For example, a ratio of {@code 1.5f} makes the
 * bullet 1.5 times as tall as it is wide.</p>
 *
 * @author refmaven
 */
public class LazyBulletType extends BasicBulletType {

    private static final float DAMAGE_RATIO_CONSTANT = 7.2f;

    /**
     * Creates a bullet with custom dimensions, sprite, item colors,
     * and automatically calculated lifetime.
     *
     * @param speed bullet speed
     * @param damage bullet damage
     * @param range bullet range used to calculate lifetime
     * @param item item used to determine bullet colors
     * @param size bullet width
     * @param ratio height multiplier relative to the bullet's width
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
        this.backColor = FXHelper.getAmmoBackColor(item);
        this.frontColor = FXHelper.getAmmoFrontColor(item);
        this.lifetime = range / speed;
    }

    /**
     * Creates a bullet without a custom sprite.
     */
    public LazyBulletType(
        float speed,
        float damage,
        int range,
        Item item,
        float size,
        float ratio
    ){
        this(speed, damage, range, item, size, ratio, null);
    }

    /**
     * Creates a bullet with the default height ratio of 1.5.
     */
    public LazyBulletType(
        float speed,
        float damage,
        int range,
        Item item,
        float size
    ){
        this(speed, damage, range, item, size, 1.5f);
    }

    /**
     * Creates a bullet with the default size of 2.75 and height ratio of 1.5.
     */
    public LazyBulletType(
        float speed,
        float damage,
        int range,
        Item item
    ){
        this(speed, damage, range, item, 2.75f);
    }

    /**
     * Creates a bullet with manually configured appearance
     * and automatically calculated lifetime.
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
     * Creates an item-colored bullet with automatically calculated damage.
     *
     * <p>Damage is calculated using:
     * {@code item.cost * speed * DAMAGE_RATIO_CONSTANT}.</p>
     *
     * @param speed bullet speed
     * @param range bullet range used to calculate lifetime
     * @param item item used to determine damage and bullet colors
     * @param size bullet width
     * @param ratio height multiplier relative to the bullet's width
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
        super(speed, lazyItemDamage(speed, item), bulletSprite);

        this.width = size;
        this.height = size * ratio;
        this.backColor = FXHelper.getAmmoBackColor(item);
        this.frontColor = FXHelper.getAmmoFrontColor(item);
        this.lifetime = range / speed;
    }

    /**
     * Creates an item-colored bullet with automatically calculated damage
     * and no custom sprite.
     */
    public LazyBulletType(
        float speed,
        int range,
        Item item,
        float size,
        float ratio
    ){
        this(speed, range, item, size, ratio, null);
    }

    /**
     * Creates an item-colored bullet with the default height ratio of 1.5.
     */
    public LazyBulletType(
        float speed,
        int range,
        Item item,
        float size
    ){
        this(speed, range, item, size, 1.5f);
    }

    /**
     * Creates an item-colored bullet with the default size of 2.75
     * and automatically calculated damage.
     */
    public LazyBulletType(
        float speed,
        int range,
        Item item
    ){
        this(speed, range, item, 2.75f);
    }

    /**
     * Creates a copper-based bullet with automatically calculated damage.
     *
     * <p>This constructor uses copper as the item for the damage formula.</p>
     */
    public LazyBulletType(
        float speed,
        int range
    ){
        super(speed, lazyItemDamage(speed, Items.copper));

        this.lifetime = range / speed;
    }

    /**
     * Calculates bullet damage from item cost and bullet speed.
     *
     * @param speed bullet speed
     * @param item item used as the damage basis
     * @return calculated bullet damage
     */
    private static float lazyItemDamage(float speed, Item item){
        return item.cost * speed * DAMAGE_RATIO_CONSTANT;
    }
}