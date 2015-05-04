package me.abrarsyed.animatedoctomeme.representation.types.generics;

import com.google.gson.annotations.SerializedName;

public enum GenericArgumentVariant
{
    @SerializedName("0")
    EXTENDS,
    @SerializedName("1")
    EXACT,
    @SerializedName("2")
    SUPER
}
