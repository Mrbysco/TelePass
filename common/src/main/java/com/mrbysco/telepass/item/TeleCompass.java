package com.mrbysco.telepass.item;

import com.mrbysco.telepass.platform.Services;
import com.mrbysco.telepass.registration.TeleDataComponents;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class TeleCompass extends Item {

	protected final CompassMaterial material;

	public TeleCompass(Properties properties, CompassMaterial material) {
		super(properties);
		this.material = material;
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand handIn) {
		ItemStack itemstack = player.getItemInHand(handIn);

		if (!level.isClientSide && itemstack.has(TeleDataComponents.OWNER.get())) {
			String ownerName = itemstack.getOrDefault(TeleDataComponents.OWNER.get(), "");
			if (ownerName.isEmpty()) return InteractionResultHolder.pass(itemstack);

			if (ownerName.equalsIgnoreCase(player.getGameProfile().getName())) {
				player.sendSystemMessage(Component.translatable("item.telepass.self"));
				return new InteractionResultHolder<>(InteractionResult.SUCCESS, itemstack);
			}
			Player owner = null;
			if (level.getServer() != null) {
				for (Player player1 : level.getServer().getPlayerList().getPlayers()) {
					if (player1.getGameProfile().getName().equalsIgnoreCase(ownerName)) {
						owner = player1;
						break;
					}
				}
			}

			if (owner != null) {
				if (owner.level().dimension().location() != player.level().dimension().location()) {
					player.sendSystemMessage(Component.translatable("item.telepass.dimension", ChatFormatting.RED + ownerName));
					return new InteractionResultHolder<>(InteractionResult.SUCCESS, itemstack);
				}

				if (!player.getAbilities().instabuild) {
					itemstack.hurtAndBreak(1, player, handIn == InteractionHand.MAIN_HAND ? EquipmentSlot.MAINHAND : EquipmentSlot.OFFHAND);
				}

				if (Services.PLATFORM.notFakePlayer(player)) {
					level.playSound((Player) null, player.getX(), player.getY(), player.getZ(), SoundEvents.CHORUS_FRUIT_TELEPORT, SoundSource.NEUTRAL, 0.5F, 0.4F / (level.random.nextFloat() * 0.4F + 0.8F));
					player.getCooldowns().addCooldown(this, 20);

					player.teleportTo(owner.getX(), owner.getY(), owner.getZ());
				}
			} else {
				player.sendSystemMessage(Component.translatable("item.telepass.offline", ChatFormatting.RED + ownerName));
			}
			return new InteractionResultHolder<>(InteractionResult.SUCCESS, itemstack);
		} else {
			return new InteractionResultHolder<>(InteractionResult.FAIL, itemstack);
		}
	}

	@Override
	public void inventoryTick(ItemStack stack, Level level, Entity entityIn, int itemSlot, boolean isSelected) {
		if (!level.isClientSide) {
			if (!stack.has(TeleDataComponents.OWNER.get())) {
				if (entityIn instanceof Player player && Services.PLATFORM.notFakePlayer(player)) {
					stack.set(TeleDataComponents.OWNER.get(), player.getGameProfile().getName());
				}
			}
		}
		super.inventoryTick(stack, level, entityIn, itemSlot, isSelected);
	}

	@Override
	public Component getName(ItemStack stack) {
		if (stack.has(TeleDataComponents.OWNER.get())) {
			String owner = stack.getOrDefault(TeleDataComponents.OWNER.get(), "");
			if (owner.isEmpty()) return super.getName(stack);
			return Component.literal(owner + "'s ").append(Component.translatable(this.getDescriptionId(stack)));
		} else {
			return super.getName(stack);
		}
	}
}
