package net.sergeus_v.fluid;

import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.FluidStateImpl;
import net.minecraft.tag.Tag;

public class StrictBloodState extends FluidStateImpl {
    public StrictBloodState(FluidState state) {
        super(state.getFluid(), state.getEntries());
    }

    @Override
    public boolean matches(Tag<Fluid> tag) {
        if(owner instanceof BloodFluid){
            return false;
        } else {
            return owner.matches(tag);
        }
    }
}