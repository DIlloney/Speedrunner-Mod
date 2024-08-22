package net.dillon.speedrunnermod.mixin.main.block;

import net.dillon.speedrunnermod.util.ItemUtil;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.SpawnerBlock;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SpawnerBlock.class)
public abstract class SpawnerBlockMixin extends BlockWithEntity {

    public SpawnerBlockMixin(Settings settings) {
        super(settings);
    }

    /**
     * Makes spawner blocks drop more experience when mined.
     */
    @Inject(method = "onStacksDropped", at = @At("TAIL"))
    private void onStacksDropped(BlockState state, ServerWorld world, BlockPos pos, ItemStack stack, boolean dropExperience, CallbackInfo ci) {
        int f = EnchantmentHelper.getLevel(ItemUtil.enchantment(world, Enchantments.FORTUNE), stack) * 172;
        int i = 512 + world.random.nextInt(524) + world.random.nextInt(128) + f;
        this.dropExperience(world, pos, i);
    }
}