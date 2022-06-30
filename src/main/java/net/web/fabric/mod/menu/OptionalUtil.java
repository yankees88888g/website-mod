package net.web.fabric.mod.menu;

import java.util.Optional;

public class OptionalUtil {
    public static boolean isPresentAndTrue(Optional<Boolean> optional) {
        return optional.isPresent() && optional.get();
    }
}
