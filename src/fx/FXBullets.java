package fx;

import fx.types.LazyBulletType;
import mindustry.content.Items;
import mindustry.entities.bullet.BulletType;

public class FXBullets{
    private FXBullets(){}

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

    public static void load(){
        rootSand = new LazyBulletType(
            FXMath.nthBulletSpeed(1),
            FXConst.ROOT_RANGE,
            Items.sand
        );

        rootScrap = new LazyBulletType(
            FXMath.nthBulletSpeed(2),
            FXConst.ROOT_RANGE,
            Items.scrap
        );

        rootCopper = new LazyBulletType(
            FXMath.nthBulletSpeed(3),
            FXConst.ROOT_RANGE,
            Items.copper
        );

        basicScrap = new LazyBulletType(
            FXMath.nthBulletSpeed(4),
            FXConst.BASIC_RANGE,
            Items.scrap
        ){{
            inaccuracy = 7f;
        }};

        basicCopper = new LazyBulletType(
            FXMath.nthBulletSpeed(5),
            FXConst.BASIC_RANGE,
            Items.copper
        );

        basicSilicon = new LazyBulletType(
            FXMath.nthBulletSpeed(6),
            FXConst.BASIC_RANGE,
            Items.silicon
        );

        twofoldCopper = new LazyBulletType(
            FXMath.nthBulletSpeed(7),
            FXConst.TWOFOLD_RANGE,
            Items.copper,
            6f,
            2f
        );

        twofoldGraphite = new LazyBulletType(
            FXMath.nthBulletSpeed(8),
            FXConst.TWOFOLD_RANGE,
            Items.graphite
        );

        twofoldLead = new LazyBulletType(
            FXMath.nthBulletSpeed(9),
            FXConst.TWOFOLD_RANGE,
            Items.lead
        );

        twofoldMetaglass = new LazyBulletType(
            FXMath.nthBulletSpeed(10),
            FXConst.TWOFOLD_RANGE,
            Items.metaglass
        );

        twofoldSilicon = new LazyBulletType(
            FXMath.nthBulletSpeed(11),
            FXConst.TWOFOLD_RANGE,
            Items.silicon
        );
    }
}