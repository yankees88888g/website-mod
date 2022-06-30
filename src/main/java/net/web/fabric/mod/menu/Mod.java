package net.web.fabric.mod.menu;

import net.minecraft.text.Text;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

    public interface Mod {
        @NotNull String getType();

        @NotNull
        String getId();

        @NotNull
        String getName();

        //@NotNull
        //NativeImageBackedTexture getIcon(FabricIconHandler iconHandler, int i);

        @NotNull
        String getSummary();

        @NotNull
        String getDescription();

        @NotNull
        String getVersion();

        @NotNull
        String getPrefixedVersion();

        @NotNull
        List<String> getAuthors();

        @NotNull
        List<String> getContributors();

        @NotNull
        List<String> getCredits();

        @Nullable
        String getWebsite();

        @Nullable
        String getIssueTracker();

        @Nullable
        String getSource();

        @Nullable
        String getParent();

        @NotNull
        Set<String> getLicense();

        @NotNull
        Map<String, String> getLinks();

    }