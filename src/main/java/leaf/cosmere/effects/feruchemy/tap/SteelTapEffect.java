/*
 * File created ~ 24 - 4 - 2021 ~ Leaf
 */

package leaf.cosmere.effects.feruchemy.tap;

import leaf.cosmere.constants.Metals;
import leaf.cosmere.effects.feruchemy.FeruchemyEffectBase;
import net.minecraft.block.material.Material;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.potion.EffectType;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraftforge.client.event.FOVUpdateEvent;
import net.minecraftforge.common.MinecraftForge;


public class SteelTapEffect extends FeruchemyEffectBase
{
    public SteelTapEffect(Metals.MetalType type, EffectType effectType)
    {
        super(type, effectType);

        this.addAttributesModifier(
                Attributes.ATTACK_SPEED,
                "0191c754-d7a2-4c78-9e14-896ecc7ed0e2",
                (double) 0.1F,
                AttributeModifier.Operation.MULTIPLY_TOTAL);

        this.addAttributesModifier(
                Attributes.MOVEMENT_SPEED,
                "ede32ebb-1e66-4d26-b414-c14467885e7a",
                (double) 0.2F,
                AttributeModifier.Operation.MULTIPLY_TOTAL);

        MinecraftForge.EVENT_BUS.addListener(this::onFOVUpdate);

    }

    public void onFOVUpdate(FOVUpdateEvent event)
    {
        //todo remember to make this better? clamp isn't necessarily the best way to stop it going over the top
        event.setNewfov(MathHelper.clamp(event.getNewfov(), 0.8f, 1.2f));
    }

    @Override
    public boolean isReady(int duration, int amplifier)
    {
        return amplifier > 2;
    }

    final Vector3d vec = new Vector3d(1.02d, 0d, 1.02d);

    @Override
    public void performEffect(LivingEntity entityLivingBaseIn, int amplifier)
    {
        //code for checking block under player
        //https://stackoverflow.com/a/62026168
        if (entityLivingBaseIn.world.getBlockState(entityLivingBaseIn.getPosition().down()).getMaterial() == Material.WATER)
        {

            Vector3d motion = entityLivingBaseIn.getMotion().mul(vec);
            entityLivingBaseIn.setMotion(motion);
        }
    }
}
