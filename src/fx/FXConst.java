package fx;

import static mindustry.type.ItemStack.with;

import mindustry.content.Items;
import mindustry.type.ItemStack;

public class FXConst{

    private FXConst(){
        // utility class
    }

    public static final ItemStack[] BASE_ROOT_LINE_BUILD_REQUIREMENTS =
        with(Items.copper, 15, Items.lead, 12);

    public static final ItemStack[] LATER_ROOT_LINE_BASE_BUILD_REQUIREMENTS =
        with(Items.graphite, 8);

    public static final float
        BASE_ROOT_LINE_BUILD_REQUIREMENTS_BASE = 1.35f,
        BASE_ROOT_LINE_BUILD_REQUIREMENTS_EXPONENT_MULTIPLIER = 1.2f;

    public static final float
        TURRET_RANGE_BASE = 1.06899f,
        TURRET_RANGE_STARTING_POINT = 25f;

    public static final float
        BULLET_SPEED_STARTING_POINT = 4f,
        BULLET_SPEED_BASE = 1.035f;

    public static final int
        ROOT_RANGE = FXMath.nthTurretRange(1),
        BASIC_RANGE = FXMath.nthTurretRange(2),
        TWOFOLD_RANGE = FXMath.nthTurretRange(3),
        SPREADER_RANGE = FXMath.nthTurretRange(4);

    public static final float
        ROOT_BULLET_SPEED = FXMath.nthBulletSpeed(1),
        BASIC_BULLET_SPEED = FXMath.nthBulletSpeed(2),
        TWOFOLD_BULLET_SPEED = FXMath.nthBulletSpeed(3),
        SPREADER_BULLET_SPEED = FXMath.nthBulletSpeed(4);

    public static final float
        BASIC_SCRAP_SPEED = BASIC_BULLET_SPEED,
        BASIC_COPPER_SPEED = BASIC_BULLET_SPEED,
        TWOFOLD_COPPER_SPEED = TWOFOLD_BULLET_SPEED,
        TWOFOLD_LEAD_SPEED = TWOFOLD_BULLET_SPEED,
        TWOFOLD_SILICON_SPEED = TWOFOLD_BULLET_SPEED;
}