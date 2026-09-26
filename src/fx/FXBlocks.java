package fx;

import static mindustry.type.Category.*;
import static mindustry.type.ItemStack.with;

import fx.types.LazyItemTurret;
import mindustry.content.Items;
import mindustry.entities.pattern.ShootPattern;
import mindustry.entities.pattern.ShootSpread;
import mindustry.gen.Sounds;
import mindustry.world.Block;
import mindustry.world.blocks.defense.turrets.ItemTurret;

public class FXBlocks {
    private FXBlocks() {
    }

    public static Block
    // crafting
    glassblower,
            largeGlassblower,
            // turrets
            root,
            basic,
            twofold,
            spreader,
            factor,
            edifice;

    public static void load() {

        root = new LazyItemTurret("root",
                FXMath.getNthRootLineRequirements(1),
                FXConst.ROOT_RANGE,
                Items.sand, FXBullets.rootSand,
                Items.scrap, FXBullets.rootScrap,
                Items.copper, FXBullets.rootCopper);

        basic = new LazyItemTurret("basic",
                FXMath.getNthRootLineRequirements(2),
                FXConst.BASIC_RANGE,
                Items.scrap, FXBullets.basicScrap,
                Items.copper, FXBullets.basicCopper,
                Items.silicon, FXBullets.basicSilicon);

        twofold = new LazyItemTurret("twofold",
                FXMath.getNthRootLineRequirements(3),
                FXConst.TWOFOLD_RANGE,
                Items.copper, FXBullets.twofoldCopper,
                Items.graphite, FXBullets.twofoldGraphite,
                Items.lead, FXBullets.twofoldLead,
                Items.silicon, FXBullets.twofoldSilicon,
                Items.metaglass, FXBullets.twofoldMetaglass) {
            {
                shoot = new ShootPattern() {
                    {
                        shots = 2;
                    }
                };
                size = 2;
                shootY = 5.5f;
                reload = 45f;
                scaledHealth = 200;
            }
        };

        spreader = new ItemTurret("spreader") {
            {
                requirements(
                        turret,
                        with(Items.copper, 1));

                ammo(
                        Items.scrap, FXBullets.basicScrap,
                        Items.copper, FXBullets.basicCopper);

                range = FXConst.SPREADER_RANGE;
                shoot = ShootSpread.circle(8);
                size = 2;
                shootY = 4f;
                shootSound = Sounds.shoot;
                reload = 15f;
                shootCone = 8f;
                health = 1000;
                rotateSpeed = 1f;
            }
        };

    }
}