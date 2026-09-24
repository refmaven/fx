package fx;

import static mindustry.type.Category.*;
import static mindustry.type.ItemStack.with;

import mindustry.content.Items;
import mindustry.entities.pattern.ShootPattern;
import mindustry.entities.pattern.ShootSpread;
import mindustry.gen.Sounds;
import mindustry.world.Block;
import mindustry.world.blocks.defense.turrets.ItemTurret;

public class FXBlocks{

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

    private FXBlocks(){
        // utility class
    }

    public static void load(){
        loadTurrets();
    }

    public static void loadTurrets(){

        root = new ItemTurret("root"){{
            requirements(
                turret,
                FXMath.getNthRootLineRequirements(1)
            );

            ammo(
                Items.sand, FXBulletTypes.rootSand,
                Items.scrap, FXBulletTypes.rootScrap,
                Items.copper, FXBulletTypes.rootCopper
            );

            range = FXConst.ROOT_RANGE;
        }};

        basic = new ItemTurret("basic"){{
            requirements(
                turret,
                FXMath.getNthRootLineRequirements(2)
            );

            ammo(
                Items.scrap, FXBulletTypes.basicScrap,
                Items.copper, FXBulletTypes.basicCopper,
                Items.silicon, FXBulletTypes.basicSilicon
            );

            range = FXConst.BASIC_RANGE;
            shootSound = Sounds.shootAlpha;
            shootY = 3f;
            reload = 10f;
            shootCone = 15f;
            health = 200;
            rotateSpeed = 8f;
        }};

        twofold = new ItemTurret("twofold"){{
            requirements(
                turret,
                FXMath.getNthRootLineRequirements(3)
            );

            ammo(
                Items.copper, FXBulletTypes.twofoldCopper,
                Items.graphite, FXBulletTypes.twofoldGraphite,
                Items.lead, FXBulletTypes.twofoldLead,
                Items.silicon, FXBulletTypes.twofoldSilicon,
                Items.metaglass, FXBulletTypes.twofoldMetaglass
            );

            range = FXConst.TWOFOLD_RANGE;

            shoot = new ShootPattern(){{
                shots = 2;
            }};

            size = 2;
            shootY = 5.5f;
            reload = 45f;
            shootCone = 15f;
            health = 800;
            rotateSpeed = 8f;
        }};

        spreader = new ItemTurret("spreader"){{
            requirements(
                turret,
                with(Items.copper, 1)
            );

            ammo(
                Items.scrap, FXBulletTypes.basicScrap,
                Items.copper, FXBulletTypes.basicCopper
            );

            range = FXConst.SPREADER_RANGE;
            shoot = ShootSpread.circle(8);
            size = 2;
            shootY = 4f;
            shootSound = Sounds.shoot;
            reload = 15f;
            shootCone = 8f;
            health = 1000;
            rotateSpeed = 1f;
        }};
    }
}