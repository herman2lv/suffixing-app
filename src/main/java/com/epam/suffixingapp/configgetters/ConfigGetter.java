package com.epam.suffixingapp.configgetters;

import com.epam.suffixingapp.beans.RenamingConfigs;

public interface ConfigGetter {
    RenamingConfigs getConfigs(String path);
}
