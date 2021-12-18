package ru.cft.focusstart.task6.client.model;

import ru.cft.focusstart.task6.client.controller.ListenerView;
import ru.cft.focusstart.task6.client.view.ListenerModel;

public interface ModelSigner {
    void subscribe(ListenerModel listener);
}
