package com.github.perryvaldez.seebooks.forms;

import java.util.Map;

public interface FormPersistable {
    public Map<String, String> getSerializedOrigValues();
    public void setSerializedOrigValues(Map<String, String> serializedOrigValues);
}
