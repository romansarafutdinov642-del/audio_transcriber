package com.company.aviation.view.flight;

import com.company.aviation.entity.Flight;
import com.company.aviation.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.EditedEntityContainer;
import io.jmix.flowui.view.StandardDetailView;
import io.jmix.flowui.view.ViewController;
import io.jmix.flowui.view.ViewDescriptor;

@Route(value = "flights/:id", layout = MainView.class)
@ViewController(id = "aviation_Flight.detail")
@ViewDescriptor(path = "flight-detail-view.xml")
@EditedEntityContainer("flightDc")
public class FlightDetailView extends StandardDetailView<Flight> {
}
