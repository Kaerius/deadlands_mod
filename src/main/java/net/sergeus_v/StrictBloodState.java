package net.sergeus_v;

import net.sergeus_v.fluid.BloodFluid;
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
            return false; // there is no "blood" tag currently, so always returns false
        } else {
            return owner.matches(tag);
        }
    }
}
