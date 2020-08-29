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
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.common.util.FakePlayer;

public class ItemTeleCompass extends Item {
	private final CompassMaterial material;
	public ItemTeleCompass(Item.Properties properties, CompassMaterial material) {
		super(properties.maxStackSize(1).group(TeleGroup.TELEPASS).maxStackSize(1).setNoRepair());
		this.material = material;
	}

	@Override
	public int getMaxDamage(ItemStack stack) {
		return material.getMaxUses();
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
		ItemStack itemstack = playerIn.getHeldItem(handIn);
		
		if(!worldIn.isRemote && itemstack.hasTag() && itemstack.getTag() != null && itemstack.getTag().contains(Reference.OWNER_TAG)) {
			String ownerName = itemstack.getTag().getString(Reference.OWNER_TAG);
//			if(ownerName.equalsIgnoreCase(playerIn.getName().getUnformattedComponentText())) {
//        		playerIn.sendMessage(new TranslationTextComponent("item.telepass.self"));
//        		return new ActionResult<>(ActionResultType.SUCCESS, itemstack);
//			}

			boolean isOnline = false;

			for(PlayerEntity player : worldIn.getPlayers()) {
				if(player.getName().getUnformattedComponentText().equalsIgnoreCase(ownerName)) {
					isOnline = true;
					break;
				}
			}

			if(isOnline) {
				PlayerEntity owner = PlayerUtil.getPlayerEntityByName(worldIn, ownerName);
				if(owner != null && owner.dimension != playerIn.dimension) {
					playerIn.sendMessage(new TranslationTextComponent("item.telepass.dimension", TextFormatting.RED + ownerName));
					return new ActionResult<>(ActionResultType.SUCCESS, itemstack);
				}

				if (!playerIn.abilities.isCreativeMode) {
					itemstack.damageItem(1, playerIn, (p_219998_1_) -> p_219998_1_.sendBreakAnimation(handIn));
				}

				if(!(playerIn instanceof FakePlayer) && owner != null) {
					worldIn.playSound((PlayerEntity)null, playerIn.getPosX(), playerIn.getPosY(), playerIn.getPosZ(), SoundEvents.ITEM_CHORUS_FRUIT_TELEPORT, SoundCategory.NEUTRAL, 0.5F, 0.4F / (random.nextFloat() * 0.4F + 0.8F));
					playerIn.getCooldownTracker().setCooldown(this, 20);

					playerIn.setPositionAndUpdate(owner.getPosX(), owner.getPosY(), owner.getPosZ());
				}

			} else {
				playerIn.sendMessage(new TranslationTextComponent("item.telepass.offline", TextFormatting.RED + ownerName));
			}
			return new ActionResult<>(ActionResultType.SUCCESS, itemstack);
		} else {
			return new ActionResult<>(ActionResultType.FAIL, itemstack);
		}
	}

	@Override
	public void inventoryTick(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
		if(!worldIn.isRemote) {
 			if(stack.getChildTag(Reference.OWNER_TAG) == null) {
				CompoundNBT tag = stack.getTag() == null ? new CompoundNBT() : stack.getTag();
				if(!tag.contains(Reference.OWNER_TAG) && entityIn instanceof PlayerEntity && !(entityIn instanceof FakePlayer)) {
					PlayerEntity player = (PlayerEntity) entityIn;
					tag.putString(Reference.OWNER_TAG, player.getName().getUnformattedComponentText());

					stack.setTag(tag);
				}
			}
		}
		super.inventoryTick(stack, worldIn, entityIn, itemSlot, isSelected);
	}

	@Override
	public ITextComponent getDisplayName(ItemStack stack) {
		if(stack.hasTag() && stack.getTag() != null && stack.getTag().contains(Reference.OWNER_TAG)) {
			CompoundNBT tag = stack.getTag();
			String owner = tag.getString(Reference.OWNER_TAG);

			return new StringTextComponent(owner + "'s ").appendSibling(new TranslationTextComponent(this.getTranslationKey(stack)));
		} else {
			return super.getDisplayName(stack);
		}
	}
}
