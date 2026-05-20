package com.company.aviation.view.airport;

import com.company.aviation.entity.Airport;
import com.company.aviation.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.EditedEntityContainer;
import io.jmix.flowui.view.StandardDetailView;
import io.jmix.flowui.view.ViewController;
import io.jmix.flowui.view.ViewDescriptor;

@Route(value = "airports/:id", layout = MainView.class)
@ViewController(id = "aviation_Airport.detail")
@ViewDescriptor(path = "airport-detail-view.xml")
@EditedEntityContainer("airportDc")
public class AirportDetailView extends StandardDetailView<Airport> {
}
