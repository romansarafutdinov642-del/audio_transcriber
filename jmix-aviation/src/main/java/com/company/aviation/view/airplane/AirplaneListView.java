package com.company.aviation.view.airplane;

import com.company.aviation.entity.Airplane;
import com.company.aviation.entity.Airport;
import com.company.aviation.entity.Flight;
import com.company.aviation.service.AviationService;
import com.company.aviation.view.main.MainView;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.router.Route;
import io.jmix.core.Messages;
import io.jmix.flowui.Notifications;
import io.jmix.flowui.component.grid.DataGrid;
import io.jmix.flowui.kit.action.ActionPerformedEvent;
import io.jmix.flowui.view.DialogMode;
import io.jmix.flowui.view.LookupComponent;
import io.jmix.flowui.view.StandardListView;
import io.jmix.flowui.view.Subscribe;
import io.jmix.flowui.view.ViewComponent;
import io.jmix.flowui.view.ViewController;
import io.jmix.flowui.view.ViewDescriptor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Route(value = "airplanes", layout = MainView.class)
@ViewController(id = "aviation_Airplane.list")
@ViewDescriptor(path = "airplane-list-view.xml")
@LookupComponent("airplanesDataGrid")
@DialogMode(width = "80em")
public class AirplaneListView extends StandardListView<Airplane> {

    @Autowired
    private AviationService aviationService;
    @Autowired
    private Notifications notifications;
    @Autowired
    private Messages messages;

    @ViewComponent
    private DataGrid<Airplane> airplanesDataGrid;

    @Subscribe("avgPayloadAction")
    public void onAvgPayloadAction(final ActionPerformedEvent event) {
        Double average = aviationService.getAveragePayloadCapacityByJpql();
        String text = average != null
                ? messages.formatMessage(getClass(), "avgPayload.result", average)
                : messages.getMessage(getClass(), "avgPayload.empty");
        notifications.create(text)
                .withType(Notifications.Type.DEFAULT)
                .withPosition(Notification.Position.TOP_END)
                .show();
    }

    @Subscribe("maxDurationAction")
    public void onMaxDurationAction(final ActionPerformedEvent event) {
        Double maxDuration = aviationService.getMaxFlightDurationExplicit();
        String text = maxDuration != null
                ? messages.formatMessage(getClass(), "maxDuration.result", maxDuration)
                : messages.getMessage(getClass(), "maxDuration.empty");
        notifications.create(text)
                .withType(Notifications.Type.DEFAULT)
                .withPosition(Notification.Position.TOP_END)
                .show();
    }

    @Subscribe("findAirplaneAction")
    public void onFindAirplaneAction(final ActionPerformedEvent event) {
        Airplane selected = airplanesDataGrid.getSingleSelectedItem();
        if (selected == null) {
            notifications.create(messages.getMessage(getClass(), "findAirplane.selectRow"))
                    .withType(Notifications.Type.WARNING)
                    .withPosition(Notification.Position.TOP_END)
                    .show();
            return;
        }
        UUID id = selected.getId();
        Optional<Airplane> airplane = aviationService.getAirplaneById(id);
        String text = airplane.map(a -> messages.formatMessage(getClass(), "findAirplane.result",
                        a.getBoardNumber(), a.getManufacturer()))
                .orElse(messages.getMessage(getClass(), "findAirplane.notFound"));
        notifications.create(text)
                .withType(Notifications.Type.DEFAULT)
                .withPosition(Notification.Position.TOP_END)
                .show();
    }

    @Subscribe("findFlightsAction")
    public void onFindFlightsAction(final ActionPerformedEvent event) {
        Airplane selected = airplanesDataGrid.getSingleSelectedItem();
        if (selected == null || selected.getAirport() == null) {
            notifications.create(messages.getMessage(getClass(), "findFlights.selectRow"))
                    .withType(Notifications.Type.WARNING)
                    .withPosition(Notification.Position.TOP_END)
                    .show();
            return;
        }
        Airport airport = selected.getAirport();
        var flights = aviationService.getFlightsByAirport(airport.getId());
        String flightNames = flights.stream()
                .map(Flight::getArrivalPoint)
                .collect(Collectors.joining(", "));
        if (flightNames.isEmpty()) {
            flightNames = messages.getMessage(getClass(), "findFlights.empty");
        }
        String text = messages.formatMessage(getClass(), "findFlights.result",
                airport.getName(), flightNames);
        notifications.create(text)
                .withType(Notifications.Type.DEFAULT)
                .withPosition(Notification.Position.TOP_END)
                .show();
    }
}
