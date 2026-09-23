/*
 * f(x): A Mindustry mod.
 * Copyright (C) 2026 refmaven
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <https://www.gnu.org/licenses/>.
 */
package fx.types;

import fx.FxHelper;

import mindustry.entities.bullet.BasicBulletType;
import mindustry.type.Item;

/**
 * A convenient extension of {@link BasicBulletType} for creating
 * item-colored bullets without manually configuring their lifetime
 * or appearance.
 *
 * <p>The range parameter is automatically converted into bullet lifetime
 * using {@code lifetime = range / speed}. When an item is provided,
 * its corresponding ammunition colors are used when available;
 * otherwise, the item's color is used as a fallback.</p>
 *
 * <p>The size and isLong parameters configure the bullet's dimensions.
 * Long bullets have a height 1.35 times their specified size.</p>
 *
 * @author refmaven
 */
public class LazyBulletType extends BasicBulletType {

    /**
     * Creates a bullet with custom dimensions, sprite, item colors,
     * and automatically calculated lifetime.
     *
     * @param speed bullet speed
     * @param damage bullet damage
     * @param range bullet range used to calculate lifetime
     * @param item item used to determine bullet colors
     * @param size bullet width and base height
     * @param isLong whether the bullet should have an increased height
     * @param bulletSprite bullet sprite name
     */
    public LazyBulletType(
        float speed,
        float damage,
        int range,
        Item item,
        float size,
        boolean isLong,
        String bulletSprite
    ){
        super(speed, damage, bulletSprite);

        this.width = size;
        this.height = isLong ? size * 1.35f : size;
        this.backColor = FxHelper.getAmmoBackColor(item);
        this.frontColor = FxHelper.getAmmoFrontColor(item);
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
        boolean isLong
    ){
        this(speed, damage, range, item, size, isLong, null);
    }

    /**
     * Creates a regular-sized bullet without an extended height.
     */
    public LazyBulletType(
        float speed,
        float damage,
        int range,
        Item item,
        float size
    ){
        this(speed, damage, range, item, size, false);
    }

    /**
     * Creates a bullet with the default size of 4.
     */
    public LazyBulletType(
        float speed,
        float damage,
        int range,
        Item item
    ){
        this(speed, damage, range, item, 4f);
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
}