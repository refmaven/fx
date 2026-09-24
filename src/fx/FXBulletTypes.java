package fx;

import fx.types.LazyBulletType;
import mindustry.content.Items;
import mindustry.entities.bullet.BulletType;

public class FXBulletTypes{

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

    private FXBulletTypes(){
        // utility class
    }

    public static void load(){
        rootSand = new LazyBulletType(
            FXConst.ROOT_BULLET_SPEED,
            FXConst.ROOT_RANGE,
            Items.sand
        );

        rootScrap = new LazyBulletType(
            FXConst.ROOT_BULLET_SPEED,
            FXConst.ROOT_RANGE,
            Items.scrap
        );

        rootCopper = new LazyBulletType(
            FXConst.ROOT_BULLET_SPEED,
            FXConst.ROOT_RANGE,
            Items.copper
        );

        basicScrap = new LazyBulletType(
            FXConst.BASIC_BULLET_SPEED,
            FXConst.BASIC_RANGE,
            Items.scrap
        ){{
            inaccuracy = 7f;
        }};

        basicCopper = new LazyBulletType(
            FXConst.BASIC_BULLET_SPEED,
            FXConst.BASIC_RANGE,
            Items.copper
        );

        basicSilicon = new LazyBulletType(
            FXConst.BASIC_BULLET_SPEED,
            FXConst.BASIC_RANGE,
            Items.silicon
        );

        twofoldCopper = new LazyBulletType(
            FXConst.TWOFOLD_COPPER_SPEED,
            FXConst.TWOFOLD_RANGE,
            Items.copper,
            6f,
            2f
        );

        twofoldGraphite = new LazyBulletType(
            FXConst.TWOFOLD_BULLET_SPEED,
            FXConst.TWOFOLD_RANGE,
            Items.graphite
        );

        twofoldLead = new LazyBulletType(
            FXConst.TWOFOLD_LEAD_SPEED,
            FXConst.TWOFOLD_RANGE,
            Items.lead
        );

        twofoldMetaglass = new LazyBulletType(
            FXConst.TWOFOLD_BULLET_SPEED,
            FXConst.TWOFOLD_RANGE,
            Items.metaglass
        );

        twofoldSilicon = new LazyBulletType(
            FXConst.TWOFOLD_SILICON_SPEED,
            FXConst.TWOFOLD_RANGE,
            Items.silicon
        );
    }
}