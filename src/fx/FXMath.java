package fx;

import arc.struct.Seq;
import mindustry.type.ItemStack;

public class FXMath{
    private FXMath(){}

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

    public static ItemStack[] getNthRootLineRequirements(int exponent){
        float multiplier = (float)Math.pow(
            FXConst.BASE_ROOT_LINE_BUILD_REQUIREMENTS_BASE,
            (exponent - 1) *
                FXConst.BASE_ROOT_LINE_BUILD_REQUIREMENTS_EXPONENT_MULTIPLIER
        );

        ItemStack[] baseRequirements = ItemStack.mult(
            FXConst.BASE_ROOT_LINE_BUILD_REQUIREMENTS,
            multiplier
        );

        ItemStack[] laterRequirements = exponent > 1
            ? ItemStack.mult(
                FXConst.LATER_ROOT_LINE_BASE_BUILD_REQUIREMENTS,
                multiplier
            )
            : ItemStack.empty;

        return addItemStackArray(
            baseRequirements,
            laterRequirements
        );
    }

    public static float toWorldUnit(float tile){
        return tile * 8f;
    }

    public static int toWorldUnit(int tile){
        return tile * 8;
    }

    public static int nthTurretRange(int exponent){
        return toWorldUnit(
            (int)Math.ceil(
                FXConst.TURRET_RANGE_STARTING_POINT *
                    Math.pow(
                        FXConst.TURRET_RANGE_BASE,
                        exponent - 1
                    )
            )
        );
    }

    /**
     * Returns the speed of the nth bullet in the global bullet progression.
     *
     * n = 1 is the first bullet.
     *
     * Speed increases quickly at first, then gradually approaches
     * a practical upper limit.
     */
    public static float nthBulletSpeed(int n){
        if(n < 1){
            throw new IllegalArgumentException(
                "Bullet index n must be at least 1."
            );
        }

        float progress =
            1f - (float)Math.exp(
                -(n - 1) * FXConst.BULLET_SPEED_GROWTH_RATE
            );

        return FXConst.BULLET_SPEED_STARTING_POINT
            + FXConst.BULLET_SPEED_MAX_INCREASE * progress;
    }
}