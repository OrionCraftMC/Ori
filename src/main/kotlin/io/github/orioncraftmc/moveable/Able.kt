package io.github.orioncraftmc.moveable

import io.github.orioncraftmc.components.Component
import io.github.orioncraftmc.moveable.data.MouseEventData

interface Able {
    fun onMouseDown(component: Component, data: MouseEventData) {}

    fun onMouseMove(component: Component, data: MouseEventData) {}

    fun onMouseUp(component: Component, data: MouseEventData) {}
}