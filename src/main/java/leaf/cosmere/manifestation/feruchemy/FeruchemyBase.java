/*
 * File created ~ 24 - 4 - 2021 ~ Leaf
 */

package leaf.cosmere.manifestation.feruchemy;

import leaf.cosmere.cap.entity.ISpiritweb;
import leaf.cosmere.charge.MetalmindChargeHelper;
import leaf.cosmere.constants.Manifestations;
import leaf.cosmere.constants.Metals;
import leaf.cosmere.utils.helpers.EffectsHelper;
import leaf.cosmere.items.IHasMetalType;
import leaf.cosmere.manifestation.ManifestationBase;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;

public class FeruchemyBase extends ManifestationBase implements IHasMetalType
{
    protected final Metals.MetalType metalType;

    public FeruchemyBase(Metals.MetalType metalType)
    {
        super(Manifestations.ManifestationTypes.FERUCHEMY, metalType.getColorValue());
        this.metalType = metalType;
    }


    @Override
    public int getPowerID()
    {
        return metalType.getID();
    }

    @Override
    public Metals.MetalType getMetalType()
    {
        return this.metalType;
    }

    @Override
    public boolean modeWraps(ISpiritweb data)
    {
        return false;
    }

    @Override
    public int modeMax(ISpiritweb data)
    {
        return 3;
    }

    @Override
    public int modeMin(ISpiritweb data)
    {
        return -10;
    }

    @Override
    public void tick(ISpiritweb data)
    {
        //don't check every tick.
        LivingEntity livingEntity = data.getLiving();

        if (livingEntity.ticksExisted % 20 != 0)
        {
            return;
        }

        int mode = data.getMode(manifestationType, metalType.getID());

        int cost;

        Effect effect = getEffect(mode);

        // if we are tapping
        //check if there is charges to tap
        if (mode < 0)
        {
            //wanting to tap
            //get cost
            cost = mode <= -3 ? -(mode * mode) : mode;

        }
        //if we are storing
        //check if there is space to store
        else if (mode > 0)
        {
            cost = mode;
        }
        //can't store or tap any more
        else
        {
            //remove active effects.
            //let the current effect run out.
            return;
        }

        if (MetalmindChargeHelper.adjustMetalmindChargeExact(data, metalType, -cost, true, true))
        {
            EffectInstance currentEffect = EffectsHelper.getNewEffect(effect, Math.abs(mode) - 1);

            if (effect == null)
            {
                return;
            }

            livingEntity.addPotionEffect(currentEffect);
        }

    }

    protected Effect getEffect(int mode)
    {
        if (mode == 0)
        {
            return null;
        }
        else if (mode < 0)
        {
            return metalType.getTappingEffect();
        }
        else
        {
            return metalType.getStoringEffect();
        }

    }
}
