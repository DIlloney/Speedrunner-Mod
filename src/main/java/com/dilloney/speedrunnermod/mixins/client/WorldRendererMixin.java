package com.dilloney.speedrunnermod.mixins.client;

import com.dilloney.speedrunnermod.SpeedrunnerMod;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ComposterBlock;
import net.minecraft.block.PointedDripstoneBlock;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.sound.PositionedSoundInstance;
import net.minecraft.client.util.ParticleUtil;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.particle.ItemStackParticleEffect;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Random;

@Mixin(WorldRenderer.class)
public abstract class WorldRendererMixin {

    @Shadow @Final MinecraftClient client;
    @Shadow ClientWorld world;
    @Shadow abstract void playSong(@Nullable SoundEvent song, BlockPos songPosition);
    @Shadow abstract Particle spawnParticle(ParticleEffect parameters, boolean alwaysSpawn, double x, double y, double z, double velocityX, double velocityY, double velocityZ);
    @Shadow static final Identifier END_SKY;

    @Overwrite
    public void processWorldEvent(PlayerEntity source, int eventId, BlockPos pos, int data) {
        Random random = this.world.random;
        int i;
        float y;
        float z;
        double t;
        double ab;
        double ac;
        double s;
        switch(eventId) {
            case 1000:
                this.world.playSound(pos, SoundEvents.BLOCK_DISPENSER_DISPENSE, SoundCategory.BLOCKS, 1.0F, 1.0F, false);
                break;
            case 1001:
                this.world.playSound(pos, SoundEvents.BLOCK_DISPENSER_FAIL, SoundCategory.BLOCKS, 1.0F, 1.2F, false);
                break;
            case 1002:
                this.world.playSound(pos, SoundEvents.BLOCK_DISPENSER_LAUNCH, SoundCategory.BLOCKS, 1.0F, 1.2F, false);
                break;
            case 1003:
                this.world.playSound(pos, SoundEvents.ENTITY_ENDER_EYE_LAUNCH, SoundCategory.NEUTRAL, 1.0F, 1.2F, false);
                break;
            case 1004:
                this.world.playSound(pos, SoundEvents.ENTITY_FIREWORK_ROCKET_SHOOT, SoundCategory.NEUTRAL, 1.0F, 1.2F, false);
                break;
            case 1005:
                this.world.playSound(pos, SoundEvents.BLOCK_IRON_DOOR_OPEN, SoundCategory.BLOCKS, 1.0F, random.nextFloat() * 0.1F + 0.9F, false);
                break;
            case 1006:
                this.world.playSound(pos, SoundEvents.BLOCK_WOODEN_DOOR_OPEN, SoundCategory.BLOCKS, 1.0F, random.nextFloat() * 0.1F + 0.9F, false);
                break;
            case 1007:
                this.world.playSound(pos, SoundEvents.BLOCK_WOODEN_TRAPDOOR_OPEN, SoundCategory.BLOCKS, 1.0F, random.nextFloat() * 0.1F + 0.9F, false);
                break;
            case 1008:
                this.world.playSound(pos, SoundEvents.BLOCK_FENCE_GATE_OPEN, SoundCategory.BLOCKS, 1.0F, random.nextFloat() * 0.1F + 0.9F, false);
                break;
            case 1009:
                if (data == 0) {
                    this.world.playSound(pos, SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.BLOCKS, 0.5F, 2.6F + (random.nextFloat() - random.nextFloat()) * 0.8F, false);
                } else if (data == 1) {
                    this.world.playSound(pos, SoundEvents.ENTITY_GENERIC_EXTINGUISH_FIRE, SoundCategory.BLOCKS, 0.7F, 1.6F + (random.nextFloat() - random.nextFloat()) * 0.4F, false);
                }
                break;
            case 1010:
                if (Item.byRawId(data) instanceof MusicDiscItem) {
                    this.playSong(((MusicDiscItem)Item.byRawId(data)).getSound(), pos);
                } else {
                    this.playSong((SoundEvent)null, pos);
                }
                break;
            case 1011:
                this.world.playSound(pos, SoundEvents.BLOCK_IRON_DOOR_CLOSE, SoundCategory.BLOCKS, 1.0F, random.nextFloat() * 0.1F + 0.9F, false);
                break;
            case 1012:
                this.world.playSound(pos, SoundEvents.BLOCK_WOODEN_DOOR_CLOSE, SoundCategory.BLOCKS, 1.0F, random.nextFloat() * 0.1F + 0.9F, false);
                break;
            case 1013:
                this.world.playSound(pos, SoundEvents.BLOCK_WOODEN_TRAPDOOR_CLOSE, SoundCategory.BLOCKS, 1.0F, random.nextFloat() * 0.1F + 0.9F, false);
                break;
            case 1014:
                this.world.playSound(pos, SoundEvents.BLOCK_FENCE_GATE_CLOSE, SoundCategory.BLOCKS, 1.0F, random.nextFloat() * 0.1F + 0.9F, false);
                break;
            case 1015:
                this.world.playSound(pos, SoundEvents.ENTITY_GHAST_WARN, SoundCategory.HOSTILE, 10.0F, (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F, false);
                break;
            case 1016:
                this.world.playSound(pos, SoundEvents.ENTITY_GHAST_SHOOT, SoundCategory.HOSTILE, 10.0F, (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F, false);
                break;
            case 1017:
                this.world.playSound(pos, SoundEvents.ENTITY_ENDER_DRAGON_SHOOT, SoundCategory.HOSTILE, 10.0F, (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F, false);
                break;
            case 1018:
                this.world.playSound(pos, SoundEvents.ENTITY_BLAZE_SHOOT, SoundCategory.HOSTILE, 2.0F, (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F, false);
                break;
            case 1019:
                this.world.playSound(pos, SoundEvents.ENTITY_ZOMBIE_ATTACK_WOODEN_DOOR, SoundCategory.HOSTILE, 2.0F, (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F, false);
                break;
            case 1020:
                this.world.playSound(pos, SoundEvents.ENTITY_ZOMBIE_ATTACK_IRON_DOOR, SoundCategory.HOSTILE, 2.0F, (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F, false);
                break;
            case 1021:
                this.world.playSound(pos, SoundEvents.ENTITY_ZOMBIE_BREAK_WOODEN_DOOR, SoundCategory.HOSTILE, 2.0F, (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F, false);
                break;
            case 1022:
                this.world.playSound(pos, SoundEvents.ENTITY_WITHER_BREAK_BLOCK, SoundCategory.HOSTILE, 2.0F, (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F, false);
                break;
            case 1024:
                this.world.playSound(pos, SoundEvents.ENTITY_WITHER_SHOOT, SoundCategory.HOSTILE, 2.0F, (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F, false);
                break;
            case 1025:
                this.world.playSound(pos, SoundEvents.ENTITY_BAT_TAKEOFF, SoundCategory.NEUTRAL, 0.05F, (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F, false);
                break;
            case 1026:
                this.world.playSound(pos, SoundEvents.ENTITY_ZOMBIE_INFECT, SoundCategory.HOSTILE, 2.0F, (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F, false);
                break;
            case 1027:
                this.world.playSound(pos, SoundEvents.ENTITY_ZOMBIE_VILLAGER_CONVERTED, SoundCategory.NEUTRAL, 2.0F, (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F, false);
                break;
            case 1029:
                this.world.playSound(pos, SoundEvents.BLOCK_ANVIL_DESTROY, SoundCategory.BLOCKS, 1.0F, random.nextFloat() * 0.1F + 0.9F, false);
                break;
            case 1030:
                this.world.playSound(pos, SoundEvents.BLOCK_ANVIL_USE, SoundCategory.BLOCKS, 1.0F, random.nextFloat() * 0.1F + 0.9F, false);
                break;
            case 1031:
                this.world.playSound(pos, SoundEvents.BLOCK_ANVIL_LAND, SoundCategory.BLOCKS, 0.3F, this.world.random.nextFloat() * 0.1F + 0.9F, false);
                break;
            case 1032:
                this.client.getSoundManager().play(PositionedSoundInstance.ambient(SoundEvents.BLOCK_PORTAL_TRAVEL, random.nextFloat() * 0.4F + 0.8F, 0.25F));
                break;
            case 1033:
                this.world.playSound(pos, SoundEvents.BLOCK_CHORUS_FLOWER_GROW, SoundCategory.BLOCKS, 1.0F, 1.0F, false);
                break;
            case 1034:
                this.world.playSound(pos, SoundEvents.BLOCK_CHORUS_FLOWER_DEATH, SoundCategory.BLOCKS, 1.0F, 1.0F, false);
                break;
            case 1035:
                this.world.playSound(pos, SoundEvents.BLOCK_BREWING_STAND_BREW, SoundCategory.BLOCKS, 1.0F, 1.0F, false);
                break;
            case 1036:
                this.world.playSound(pos, SoundEvents.BLOCK_IRON_TRAPDOOR_CLOSE, SoundCategory.BLOCKS, 1.0F, random.nextFloat() * 0.1F + 0.9F, false);
                break;
            case 1037:
                this.world.playSound(pos, SoundEvents.BLOCK_IRON_TRAPDOOR_OPEN, SoundCategory.BLOCKS, 1.0F, random.nextFloat() * 0.1F + 0.9F, false);
                break;
            case 1039:
                this.world.playSound(pos, SoundEvents.ENTITY_PHANTOM_BITE, SoundCategory.HOSTILE, 0.3F, this.world.random.nextFloat() * 0.1F + 0.9F, false);
                break;
            case 1040:
                this.world.playSound(pos, SoundEvents.ENTITY_ZOMBIE_CONVERTED_TO_DROWNED, SoundCategory.NEUTRAL, 2.0F, (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F, false);
                break;
            case 1041:
                this.world.playSound(pos, SoundEvents.ENTITY_HUSK_CONVERTED_TO_ZOMBIE, SoundCategory.NEUTRAL, 2.0F, (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F, false);
                break;
            case 1042:
                this.world.playSound(pos, SoundEvents.BLOCK_GRINDSTONE_USE, SoundCategory.BLOCKS, 1.0F, this.world.random.nextFloat() * 0.1F + 0.9F, false);
                break;
            case 1043:
                this.world.playSound(pos, SoundEvents.ITEM_BOOK_PAGE_TURN, SoundCategory.BLOCKS, 1.0F, this.world.random.nextFloat() * 0.1F + 0.9F, false);
                break;
            case 1044:
                this.world.playSound(pos, SoundEvents.BLOCK_SMITHING_TABLE_USE, SoundCategory.BLOCKS, 1.0F, this.world.random.nextFloat() * 0.1F + 0.9F, false);
                break;
            case 1045:
                this.world.playSound(pos, SoundEvents.BLOCK_POINTED_DRIPSTONE_LAND, SoundCategory.BLOCKS, 2.0F, this.world.random.nextFloat() * 0.1F + 0.9F, false);
                break;
            case 1046:
                this.world.playSound(pos, SoundEvents.BLOCK_POINTED_DRIPSTONE_DRIP_LAVA_INTO_CAULDRON, SoundCategory.BLOCKS, 2.0F, this.world.random.nextFloat() * 0.1F + 0.9F, false);
                break;
            case 1047:
                this.world.playSound(pos, SoundEvents.BLOCK_POINTED_DRIPSTONE_DRIP_WATER_INTO_CAULDRON, SoundCategory.BLOCKS, 2.0F, this.world.random.nextFloat() * 0.1F + 0.9F, false);
                break;
            case 1048:
                this.world.playSound(pos, SoundEvents.ENTITY_SKELETON_CONVERTED_TO_STRAY, SoundCategory.NEUTRAL, 2.0F, (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F, false);
                break;
            case 1500:
                ComposterBlock.playEffects(this.world, pos, data > 0);
                break;
            case 1501:
                this.world.playSound(pos, SoundEvents.BLOCK_LAVA_EXTINGUISH, SoundCategory.BLOCKS, 0.5F, 2.6F + (random.nextFloat() - random.nextFloat()) * 0.8F, false);

                for(i = 0; i < 8; ++i) {
                    if (SpeedrunnerMod.CONFIG.defaultParticles) {
                        this.world.addParticle(ParticleTypes.LARGE_SMOKE, (double)pos.getX() + random.nextDouble(), (double)pos.getY() + 1.2D, (double)pos.getZ() + random.nextDouble(), 0.0D, 0.0D, 0.0D);
                    }
                }

                return;
            case 1502:
                this.world.playSound(pos, SoundEvents.BLOCK_REDSTONE_TORCH_BURNOUT, SoundCategory.BLOCKS, 0.5F, 2.6F + (random.nextFloat() - random.nextFloat()) * 0.8F, false);

                for(i = 0; i < 5; ++i) {
                    s = (double)pos.getX() + random.nextDouble() * 0.6D + 0.2D;
                    t = (double)pos.getY() + random.nextDouble() * 0.6D + 0.2D;
                    ab = (double)pos.getZ() + random.nextDouble() * 0.6D + 0.2D;
                    if (SpeedrunnerMod.CONFIG.defaultParticles) {
                        this.world.addParticle(ParticleTypes.SMOKE, s, t, ab, 0.0D, 0.0D, 0.0D);
                    }
                }

                return;
            case 1503:
                this.world.playSound(pos, SoundEvents.BLOCK_END_PORTAL_FRAME_FILL, SoundCategory.BLOCKS, 1.0F, 1.0F, false);

                for(i = 0; i < 16; ++i) {
                    s = (double)pos.getX() + (5.0D + random.nextDouble() * 6.0D) / 16.0D;
                    t = (double)pos.getY() + 0.8125D;
                    ab = (double)pos.getZ() + (5.0D + random.nextDouble() * 6.0D) / 16.0D;
                    if (SpeedrunnerMod.CONFIG.defaultParticles) {
                        this.world.addParticle(ParticleTypes.SMOKE, s, t, ab, 0.0D, 0.0D, 0.0D);
                    }
                }

                return;
            case 1504:
                PointedDripstoneBlock.createParticle(this.world, pos, this.world.getBlockState(pos));
                break;
            case 1505:
                BoneMealItem.createParticles(this.world, pos, data);
                this.world.playSound(pos, SoundEvents.ITEM_BONE_MEAL_USE, SoundCategory.BLOCKS, 1.0F, 1.0F, false);
                break;
            case 2000:
                Direction direction = Direction.byId(data);
                i = direction.getOffsetX();
                int j = direction.getOffsetY();
                int k = direction.getOffsetZ();
                t = (double)pos.getX() + (double)i * 0.6D + 0.5D;
                ab = (double)pos.getY() + (double)j * 0.6D + 0.5D;
                ac = (double)pos.getZ() + (double)k * 0.6D + 0.5D;

                for(int l = 0; l < 10; ++l) {
                    double g = random.nextDouble() * 0.2D + 0.01D;
                    double h = t + (double)i * 0.01D + (random.nextDouble() - 0.5D) * (double)k * 0.5D;
                    double m = ab + (double)j * 0.01D + (random.nextDouble() - 0.5D) * (double)j * 0.5D;
                    double n = ac + (double)k * 0.01D + (random.nextDouble() - 0.5D) * (double)i * 0.5D;
                    double o = (double)i * g + random.nextGaussian() * 0.01D;
                    double p = (double)j * g + random.nextGaussian() * 0.01D;
                    double q = (double)k * g + random.nextGaussian() * 0.01D;
                    if (SpeedrunnerMod.CONFIG.defaultParticles) {
                        this.world.addParticle(ParticleTypes.SMOKE, h, m, n, o, p, q);
                    }
                }

                return;
            case 2001:
                BlockState blockState = Block.getStateFromRawId(data);
                if (!blockState.isAir()) {
                    BlockSoundGroup blockSoundGroup = blockState.getSoundGroup();
                    this.world.playSound(pos, blockSoundGroup.getBreakSound(), SoundCategory.BLOCKS, (blockSoundGroup.getVolume() + 1.0F) / 2.0F, blockSoundGroup.getPitch() * 0.8F, false);
                }

                this.world.addBlockBreakParticles(pos, blockState);
                break;
            case 2002:
            case 2007:
                Vec3d vec3d = Vec3d.ofBottomCenter(pos);

                for(i = 0; i < 8; ++i) {
                    this.world.addParticle(new ItemStackParticleEffect(ParticleTypes.ITEM, new ItemStack(Items.SPLASH_POTION)), vec3d.x, vec3d.y, vec3d.z, random.nextGaussian() * 0.15D, random.nextDouble() * 0.2D, random.nextGaussian() * 0.15D);
                }

                float x = (float)(data >> 16 & 255) / 255.0F;
                y = (float)(data >> 8 & 255) / 255.0F;
                z = (float)(data >> 0 & 255) / 255.0F;
                ParticleEffect particleEffect = eventId == 2007 ? ParticleTypes.INSTANT_EFFECT : ParticleTypes.EFFECT;

                for(int aa = 0; aa < 100; ++aa) {
                    ab = random.nextDouble() * 4.0D;
                    ac = random.nextDouble() * 3.141592653589793D * 2.0D;
                    double ad = Math.cos(ac) * ab;
                    double ae = 0.01D + random.nextDouble() * 0.5D;
                    double af = Math.sin(ac) * ab;
                    Particle particle = this.spawnParticle(particleEffect, particleEffect.getType().shouldAlwaysSpawn(), vec3d.x + ad * 0.1D, vec3d.y + 0.3D, vec3d.z + af * 0.1D, ad, ae, af);
                    if (particle != null) {
                        float ag = 0.75F + random.nextFloat() * 0.25F;
                        particle.setColor(x * ag, y * ag, z * ag);
                        particle.move((float)ab);
                    }
                }

                this.world.playSound(pos, SoundEvents.ENTITY_SPLASH_POTION_BREAK, SoundCategory.NEUTRAL, 1.0F, random.nextFloat() * 0.1F + 0.9F, false);
                break;
            case 2003:
                double r = (double)pos.getX() + 0.5D;
                s = (double)pos.getY();
                t = (double)pos.getZ() + 0.5D;

                for(int u = 0; u < 8; ++u) {
                    this.world.addParticle(new ItemStackParticleEffect(ParticleTypes.ITEM, new ItemStack(Items.ENDER_EYE)), r, s, t, random.nextGaussian() * 0.15D, random.nextDouble() * 0.2D, random.nextGaussian() * 0.15D);
                }

                for(ab = 0.0D; ab < 6.283185307179586D; ab += 0.15707963267948966D) {
                    this.world.addParticle(ParticleTypes.PORTAL, r + Math.cos(ab) * 5.0D, s - 0.4D, t + Math.sin(ab) * 5.0D, Math.cos(ab) * -5.0D, 0.0D, Math.sin(ab) * -5.0D);
                    this.world.addParticle(ParticleTypes.PORTAL, r + Math.cos(ab) * 5.0D, s - 0.4D, t + Math.sin(ab) * 5.0D, Math.cos(ab) * -7.0D, 0.0D, Math.sin(ab) * -7.0D);
                }

                return;
            case 2004:
                for(i = 0; i < 20; ++i) {
                    s = (double)pos.getX() + 0.5D + (random.nextDouble() - 0.5D) * 2.0D;
                    t = (double)pos.getY() + 0.5D + (random.nextDouble() - 0.5D) * 2.0D;
                    ab = (double)pos.getZ() + 0.5D + (random.nextDouble() - 0.5D) * 2.0D;
                    if (SpeedrunnerMod.CONFIG.defaultParticles) {
                        this.world.addParticle(ParticleTypes.SMOKE, s, t, ab, 0.0D, 0.0D, 0.0D);
                        this.world.addParticle(ParticleTypes.FLAME, s, t, ab, 0.0D, 0.0D, 0.0D);
                    }
                }

                return;
            case 2005:
                BoneMealItem.createParticles(this.world, pos, data);
                break;
            case 2006:
                for(i = 0; i < 200; ++i) {
                    y = random.nextFloat() * 4.0F;
                    z = random.nextFloat() * 6.2831855F;
                    t = (double)(MathHelper.cos(z) * y);
                    ab = 0.01D + random.nextDouble() * 0.5D;
                    ac = (double)(MathHelper.sin(z) * y);
                    Particle particle2 = this.spawnParticle(ParticleTypes.DRAGON_BREATH, false, (double)pos.getX() + t * 0.1D, (double)pos.getY() + 0.3D, (double)pos.getZ() + ac * 0.1D, t, ab, ac);
                    if (particle2 != null) {
                        particle2.move(y);
                    }
                }

                if (data == 1) {
                    this.world.playSound(pos, SoundEvents.ENTITY_DRAGON_FIREBALL_EXPLODE, SoundCategory.HOSTILE, 1.0F, random.nextFloat() * 0.1F + 0.9F, false);
                }
                break;
            case 2008:
                this.world.addParticle(ParticleTypes.EXPLOSION, (double)pos.getX() + 0.5D, (double)pos.getY() + 0.5D, (double)pos.getZ() + 0.5D, 0.0D, 0.0D, 0.0D);
                break;
            case 2009:
                for(i = 0; i < 8; ++i) {
                    this.world.addParticle(ParticleTypes.CLOUD, (double)pos.getX() + random.nextDouble(), (double)pos.getY() + 1.2D, (double)pos.getZ() + random.nextDouble(), 0.0D, 0.0D, 0.0D);
                }

                return;
            case 3000:
                this.world.addParticle(ParticleTypes.EXPLOSION_EMITTER, true, (double)pos.getX() + 0.5D, (double)pos.getY() + 0.5D, (double)pos.getZ() + 0.5D, 0.0D, 0.0D, 0.0D);
                this.world.playSound(pos, SoundEvents.BLOCK_END_GATEWAY_SPAWN, SoundCategory.BLOCKS, 10.0F, (1.0F + (this.world.random.nextFloat() - this.world.random.nextFloat()) * 0.2F) * 0.7F, false);
                break;
            case 3001:
                this.world.playSound(pos, SoundEvents.ENTITY_ENDER_DRAGON_GROWL, SoundCategory.HOSTILE, 64.0F, 0.8F + this.world.random.nextFloat() * 0.3F, false);
                break;
            case 3002:
                if (data >= 0 && data < Direction.Axis.VALUES.length) {
                    ParticleUtil.spawnParticle(Direction.Axis.VALUES[data], this.world, pos, 0.125D, ParticleTypes.ELECTRIC_SPARK, UniformIntProvider.create(10, 19));
                } else {
                    ParticleUtil.spawnParticle(this.world, pos, ParticleTypes.ELECTRIC_SPARK, UniformIntProvider.create(3, 5));
                }
                break;
            case 3003:
                ParticleUtil.spawnParticle(this.world, pos, ParticleTypes.WAX_ON, UniformIntProvider.create(3, 5));
                this.world.playSound(pos, SoundEvents.ITEM_HONEYCOMB_WAX_ON, SoundCategory.BLOCKS, 1.0F, 1.0F, false);
                break;
            case 3004:
                ParticleUtil.spawnParticle(this.world, pos, ParticleTypes.WAX_OFF, UniformIntProvider.create(3, 5));
                break;
            case 3005:
                ParticleUtil.spawnParticle(this.world, pos, ParticleTypes.SCRAPE, UniformIntProvider.create(3, 5));
        }
    }

    static {
        if (SpeedrunnerMod.CONFIG.doomMode) {
            END_SKY = new Identifier("textures/environment/end_sky_doom_mode.png");
        } else {
            END_SKY = new Identifier("textures/environment/end_sky.png");
        }
    }
}