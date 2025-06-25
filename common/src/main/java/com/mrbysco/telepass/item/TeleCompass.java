package com.mrbysco.telepass.item;

import com.mrbysco.telepass.platform.Services;
import com.mrbysco.telepass.registration.TeleDataComponents;
import com.mrbysco.telepass.util.PlayerUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class TeleCompass extends Item {

	protected final CompassMaterial material;

	public TeleCompass(Properties properties, CompassMaterial material) {
		super(properties);
		this.material = material;
	}

	@NotNull
	@Override
	public InteractionResult use(Level level, Player player, @NotNull InteractionHand handIn) {
		ItemStack itemstack = player.getItemInHand(handIn);

		if (!level.isClientSide && player instanceof ServerPlayer serverPlayer  && itemstack.has(TeleDataComponents.OWNER.get())) {
			String ownerName = itemstack.getOrDefault(TeleDataComponents.OWNER.get(), "");
			if (ownerName.isEmpty()) return InteractionResult.PASS;

			if (ownerName.equalsIgnoreCase(player.getGameProfile().getName())) {
				serverPlayer.sendSystemMessage(Component.translatable("item.telepass.self"));
				return InteractionResult.SUCCESS;
			}

			Player owner = PlayerUtil.getPlayerEntityByName(level, ownerName);
			if (owner != null) {
				if (!owner.level().dimension().location().equals(player.level().dimension().location())) {
					serverPlayer.sendSystemMessage(Component.translatable("item.telepass.dimension", ChatFormatting.RED + ownerName));
					return InteractionResult.SUCCESS;
				}

				if (!player.getAbilities().instabuild) {
					itemstack.hurtAndBreak(1, player, handIn == InteractionHand.MAIN_HAND ? EquipmentSlot.MAINHAND : EquipmentSlot.OFFHAND);
				}

				if (Services.PLATFORM.notFakePlayer(player)) {
					level.playSound((Player) null, player.getX(), player.getY(), player.getZ(), SoundEvents.CHORUS_FRUIT_TELEPORT, SoundSource.NEUTRAL, 0.5F, 0.4F / (level.random.nextFloat() * 0.4F + 0.8F));
					player.getCooldowns().addCooldown(itemstack, 20);

					player.teleportTo(owner.getX(), owner.getY(), owner.getZ());
				}
			} else {
				serverPlayer.sendSystemMessage(Component.translatable("item.telepass.offline", ChatFormatting.RED + ownerName));
			}
			return InteractionResult.SUCCESS;
		} else {
			return InteractionResult.FAIL;
		}
	}

	@Override
	public void inventoryTick(@NotNull ItemStack stack, ServerLevel level, @NotNull Entity entity, @Nullable EquipmentSlot slot) {
		if (!level.isClientSide) {
			if (!stack.has(TeleDataComponents.OWNER.get())) {
				if (entity instanceof Player player && Services.PLATFORM.notFakePlayer(player)) {
					stack.set(TeleDataComponents.OWNER.get(), player.getGameProfile().getName());
				}
			}
		}
		super.inventoryTick(stack, level, entity, slot);
	}

	@NotNull
	@Override
	public Component getName(ItemStack stack) {
		if (stack.has(TeleDataComponents.OWNER.get())) {
			String owner = stack.getOrDefault(TeleDataComponents.OWNER.get(), "");
			if (owner.isEmpty()) return super.getName(stack);
			return Component.literal(owner + "'s ").append(Component.translatable(this.getDescriptionId()));
		} else {
			return super.getName(stack);
		}
	}
}
