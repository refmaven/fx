package fx;

import static mindustry.type.ItemStack.with;

import mindustry.content.Items;
import mindustry.type.ItemStack;

public class FXConst{
    private FXConst(){}
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

    /*
     * Bullet speed progression.
     *
     * The first bullet starts at 4.
     * Later bullets become faster, but the progression slows down
     * instead of increasing forever.
     */
    public static final float
        BULLET_SPEED_STARTING_POINT = 4f,
        BULLET_SPEED_MAX_INCREASE = 4f,
        BULLET_SPEED_GROWTH_RATE = 0.08f;

    public static final int
        ROOT_RANGE = FXMath.nthTurretRange(1),
        BASIC_RANGE = FXMath.nthTurretRange(2),
        TWOFOLD_RANGE = FXMath.nthTurretRange(3),
        SPREADER_RANGE = FXMath.nthTurretRange(4);
}