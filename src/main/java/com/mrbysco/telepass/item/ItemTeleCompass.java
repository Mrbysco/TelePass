package com.mrbysco.telepass.item;

import com.mrbysco.telepass.Reference;
import com.mrbysco.telepass.init.TeleGroup;
import com.mrbysco.telepass.util.PlayerUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.Util;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.common.util.FakePlayer;

public class ItemTeleCompass extends Item {
	private final CompassMaterial material;

	public ItemTeleCompass(Item.Properties properties, CompassMaterial material) {
		super(properties.stacksTo(1).tab(TeleGroup.TELEPASS).stacksTo(1).setNoRepair());
		this.material = material;
	}

	@Override
	public int getMaxDamage(ItemStack stack) {
		return material.getMaxUses();
	}

	@Override
	public ActionResult<ItemStack> use(World worldIn, PlayerEntity playerIn, Hand handIn) {
		ItemStack itemstack = playerIn.getItemInHand(handIn);
		
		if(!worldIn.isClientSide && itemstack.hasTag() && itemstack.getTag().contains(Reference.OWNER_TAG)) {
			String ownerName = itemstack.getTag().getString(Reference.OWNER_TAG);
			if(ownerName.equalsIgnoreCase(playerIn.getName().getContents())) {
        		playerIn.sendMessage(new TranslationTextComponent("item.telepass.self"), Util.NIL_UUID);
        		return new ActionResult<>(ActionResultType.SUCCESS, itemstack);
			}
			boolean isOnline = false;

			for(PlayerEntity player : worldIn.players()) {
				if(player.getName().getContents().equalsIgnoreCase(ownerName)) {
					isOnline = true;
					break;
				}
			}

			if(isOnline) {
				PlayerEntity owner = PlayerUtil.getPlayerEntityByName(worldIn, ownerName);
				if(owner != null && owner.getCommandSenderWorld().dimension().getRegistryName() != playerIn.getCommandSenderWorld().dimension().getRegistryName()) {
					playerIn.sendMessage(new TranslationTextComponent("item.telepass.dimension", TextFormatting.RED + ownerName), Util.NIL_UUID);
					return new ActionResult<>(ActionResultType.SUCCESS, itemstack);
				}

				if (!playerIn.abilities.instabuild) {
					itemstack.hurtAndBreak(1, playerIn, (p_219998_1_) -> p_219998_1_.broadcastBreakEvent(handIn));
				}

				if(!(playerIn instanceof FakePlayer) && owner != null) {
					worldIn.playSound((PlayerEntity)null, playerIn.getX(), playerIn.getY(), playerIn.getZ(), SoundEvents.CHORUS_FRUIT_TELEPORT, SoundCategory.NEUTRAL, 0.5F, 0.4F / (random.nextFloat() * 0.4F + 0.8F));
					playerIn.getCooldowns().addCooldown(this, 20);

					playerIn.teleportTo(owner.getX(), owner.getY(), owner.getZ());
				}
			} else {
				playerIn.sendMessage(new TranslationTextComponent("item.telepass.offline", TextFormatting.RED + ownerName), Util.NIL_UUID);
			}
			return new ActionResult<>(ActionResultType.SUCCESS, itemstack);
		} else {
			return new ActionResult<>(ActionResultType.FAIL, itemstack);
		}
	}

	@Override
	public void inventoryTick(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
		if(!worldIn.isClientSide) {
			if((stack.hasTag() && stack.getTag() != null && !stack.getTag().contains(Reference.OWNER_TAG)) || !stack.hasTag() || stack.getTag() == null) {
				CompoundNBT tag = stack.getTag() == null ? new CompoundNBT() : stack.getTag();
				if(entityIn instanceof PlayerEntity && !(entityIn instanceof FakePlayer)) {
					PlayerEntity player = (PlayerEntity) entityIn;
					tag.putString(Reference.OWNER_TAG, player.getName().getContents());

					stack.setTag(tag);
				}
			}
		}
		super.inventoryTick(stack, worldIn, entityIn, itemSlot, isSelected);
	}

	@Override
	public ITextComponent getName(ItemStack stack) {
		if(stack.hasTag() && stack.getTag() != null && stack.getTag().contains(Reference.OWNER_TAG)) {
			CompoundNBT tag = stack.getTag();
			String owner = tag.getString(Reference.OWNER_TAG);
			return new StringTextComponent(owner + "'s ").append(new TranslationTextComponent(this.getDescriptionId(stack)));
		} else {
			return super.getName(stack);
		}
	}
}
