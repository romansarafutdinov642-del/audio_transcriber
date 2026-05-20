package com.company.aviation.view.airport;

import com.company.aviation.entity.Airport;
import com.company.aviation.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.DialogMode;
import io.jmix.flowui.view.LookupComponent;
import io.jmix.flowui.view.StandardListView;
import io.jmix.flowui.view.ViewController;
import io.jmix.flowui.view.ViewDescriptor;

@Route(value = "airports", layout = MainView.class)
@ViewController(id = "aviation_Airport.list")
@ViewDescriptor(path = "airport-list-view.xml")
@LookupComponent("airportsDataGrid")
@DialogMode(width = "64em")
public class AirportListView extends StandardListView<Airport> {
}
