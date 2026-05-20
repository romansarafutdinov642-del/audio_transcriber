package com.company.aviation.view.flight;

import com.company.aviation.entity.Flight;
import com.company.aviation.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.DialogMode;
import io.jmix.flowui.view.LookupComponent;
import io.jmix.flowui.view.StandardListView;
import io.jmix.flowui.view.ViewController;
import io.jmix.flowui.view.ViewDescriptor;

@Route(value = "flights", layout = MainView.class)
@ViewController(id = "aviation_Flight.list")
@ViewDescriptor(path = "flight-list-view.xml")
@LookupComponent("flightsDataGrid")
@DialogMode(width = "64em")
public class FlightListView extends StandardListView<Flight> {
}
